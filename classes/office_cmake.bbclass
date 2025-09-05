inherit cmake

CMAKE_OFFICE_ENABLE            ?= "0"
CMAKE_OFFICE_SCRUB_BINCONFIG   ?= "0"
CMAKE_OFFICE_SCRUB_EXTRA_GLOBS ?= ""
CMAKE_OFFICE_VERBOSE           ?= "1"

EXTRA_OECMAKE:append = " \
  -DCMAKE_INSTALL_PREFIX=${prefix} \
  -DCMAKE_INSTALL_LIBDIR=${baselib} \
  -DCMAKE_INSTALL_BINDIR=${bindir} \
  -DCMAKE_INSTALL_INCLUDEDIR=${includedir} \
"

do_install:prepend:class-native(){ 
    no_staging_check=true; 
}

do_install:prepend:class-nativesdk(){ 
    no_staging_check=true; 
}

do_install:append() {
    [ "${CMAKE_OFFICE_ENABLE}" = "1" ] || exit 0

    # sanity: 
    if ( cd ${D} 2>/dev/null && grep -qr 'usr//usr' ); then
        bbfatal 'usr//usr found - check CMAKE_INSTALL_PREFIX!'
    fi

    # scrub "posix-ish" friendly 
    default_preds="-name '*.cmake' -o -name '*Targets*.cmake' -o -name '*Config*.cmake' -o -name '*.pc'"
    extra_preds=""
    for g in ${CMAKE_OFFICE_SCRUB_EXTRA_GLOBS}; do
        extra_preds="$extra_preds -o -name '$g'"
    done

    # walk files 
    find "${D}" -type f \( ${default_preds} ${extra_preds} \) -print0 2>/dev/null | \
    while IFS= read -r -d '' f; do
        [ "${CMAKE_OFFICE_VERBOSE}" = "1" ] && bbnote "cmake-office: scrubbing $f"
        # Map DESTDIR/sysroot+prefix -> ${_IMPORT_PREFIX}${prefix} when present,
        # then drop any remaining build/sysroot paths.
        sed -i \
          -e "s#${D}${prefix}#\${_IMPORT_PREFIX}${prefix}#g" \
          -e "s#${RECIPE_SYSROOT}${prefix}#\${_IMPORT_PREFIX}${prefix}#g" \
          -e "s#${RECIPE_SYSROOT_NATIVE}${prefix}#\${_IMPORT_PREFIX}${prefix}#g" \
          -e "s#${D}##g" \
          -e "s#${TMPDIR}##g" \
          -e "s#${RECIPE_SYSROOT_NATIVE}##g" \
          -e "s#${RECIPE_SYSROOT}##g" \
          -e "s#${B}##g" \
          -e "s#${S}##g" \
          "$f" || true
    done

    # legacy *-config helpers
    if [ "${CMAKE_OFFICE_SCRUB_BINCONFIG}" = "1" ] && [ -d "${D}${bindir}" ]; then
        find "${D}${bindir}" -maxdepth 1 -type f -name '*-config' -print0 2>/dev/null | \
        while IFS= read -r -d '' c; do
            [ "${CMAKE_OFFICE_VERBOSE}" = "1" ] && bbnote "cmake-office: binconfig scrub $c"
            sed -i \
              -e "s#${D}##g" \
              -e "s#${TMPDIR}##g" \
              -e "s#${RECIPE_SYSROOT_NATIVE}##g" \
              -e "s#${RECIPE_SYSROOT}##g" \
              -e 's#^prefix=.*#prefix=/usr#g' \
              -e 's#^exec_prefix=.*#exec_prefix=/usr#g' \
              "$c" || true
        done
    fi

   
    if [ "x" = "x$no_staging_check" ]; then
        error=
        if [ -d "${B}" ]; then
            find "${B}" -type f \( -name '*.h' -o -name '*.hpp' -o -name '*.hh' -o -name '*.c' -o -name '*.cc' -o -name '*.cpp' \) -print0 2>/dev/null | \
            while IFS= read -r -d '' s; do
                if grep -q 'recipe-sysroot' "$s"; then
                    bbwarn "$s contains links to build sysroot!"
                    error=true
                fi
            done
        fi

        find "${D}" -type f -name '*.cmake' -print0 2>/dev/null | \
        while IFS= read -r -d '' k; do
            if grep -q 'recipe-sysroot' "$k"; then
                bbwarn "$k contains links to build host sysroot!"
                error=true
            fi
        done

        if [ "x" != "x$error" ]; then
            bbfatal "One or more files contain links to build host sysroot ${STAGING_DIR_HOST}(-native)"
        fi
    fi
}


# post 
do_populate_sysroot[postfuncs] += "do_sysroot_cmake_sanity "
do_sysroot_cmake_sanity() {
    [ "${CMAKE_OFFICE_ENABLE}" = "1" ] || return 0
    error=
    if [ -f "${CMAKEINSTALLED}" ]; then
        while IFS= read -r f; do
            [ -f "$f" ] || continue
            if grep -qE "${TMPDIR}|${RECIPE_SYSROOT}|${RECIPE_SYSROOT_NATIVE}" "$f"; then
                bbwarn "$f contains build/sysroot paths"
                error=true
            fi
            if grep -q ';${libdir}' "$f"; then
                bbwarn "$f contains links to \${libdir}!"
                error=true
            fi
            if grep -q '"${libdir}/lib' "$f"; then
                bbwarn "$f contains links to \${libdir}!"
                error=true
            fi
            if grep -q ';${includedir}' "$f"; then
                bbwarn "$f contains links to \${includedir}!"
                error=true
            fi
            if grep -q '"${includedir}' "$f"; then
                bbwarn "$f contains links to \${includedir}!"
                error=true
            fi
        done < "${CMAKEINSTALLED}"
    fi
    if [ "x" != "x$error" ]; then
        bbfatal "One or more files in sysroot contain non-relocatable references (see warnings above)."
    fi
}
