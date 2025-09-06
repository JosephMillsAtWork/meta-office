SUMMARY = "2Geom: easy 2D graphics library"
HOMEPAGE = "https://gitlab.com/inkscape/lib2geom"

LICENSE = "LGPL-2.1-only"
LIC_FILES_CHKSUM = "file://COPYING-LGPL-2.1;md5=fad9b3332be894bab9bc501572864b29"

inherit office_cmake features_check

DEPENDS = " \
    boost \
    double-conversion \
    glib-2.0 \
    pkgconfig-native \
    gsl \
"

SRC_URI = " \
    git://gitlab.com/inkscape/lib2geom.git;branch=1.4.x;protocol=https \
"
SRCREV = "23316118755def8156c268f767894c145130e07d"
PV = "1.4.0"
S = "${WORKDIR}/git"

REQUIRED_DISTRO_FEATURES = "opengl"
EXTRA_OECMAKE = " \
    -D2GEOM_TOYS=OFF \
"

PACKAGECONFIG ?= " cairo shared gpl standalone "
PACKAGECONFIG[cairo] = ", , cairo"
PACKAGECONFIG[shared]  = "2GEOM_BUILD_SHARED=ON, -D2GEOM_BUILD_SHARED=OFF, "
PACKAGECONFIG[gpl]  = "-D2GEOM_USE_GPL_CODE=ON, -D2GEOM_USE_GPL_CODE=OFF, "
PACKAGECONFIG[standalone] = "-D2GEOM_STANDALONE=ON, -D2GEOM_STANDALONE=OFF, "
PACKAGECONFIG[testing] = " -D2GEOM_TESTING=ON, -D2GEOM_TESTING=OFF, googletest-native" 
PACKAGECONFIG[profile]  = "-DWITH_PROFILING=ON, -DWITH_PROFILING=OFF, "
PACKAGECONFIG[coverage] =  "-DWITH_COVERAGE=ON, -DWITH_COVERAGE=OFF, "
PACKAGECONFIG[performance-tests] = "-D2GEOM_PERFORMANCE_TESTS=ON, -D2GEOM_PERFORMANCE_TESTS=OFF, "

# Blockers 
# * 1 toys -> ragel & colm , 
#PACKAGECONFIG[toys]  "-D2GEOM_TOYS=ON, -D2GEOM_TOYS=OFF, ragel-native gtk3"
#PACKAGECONFIG[toys-lpe]  "-D2GEOM_TOYS_LPE=ON, 2GEOM_TOYS_LPE=OFF, ragel-native gtk3"

# * 2 py2geom wants boost-py py-cario
#PACKAGECONFIG[cython]  " 
#    -D2GEOM_CYTHON_BUILD_SHARED=ON -D2GEOM_CYTHON_BINDINGS=ON,  
#    -D2GEOM_CYTHON_BUILD_SHARED=OFF -D2GEOM_CYTHON_BINDINGS=OFF, 
#    python3-cython-native python-native python3-boost-native, python3-cario-native 
#"
