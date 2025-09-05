SUMMARY = "Font rendering capabilities for complex non-Roman writing systems"
HOMEPAGE = "https://github.com/tfussell/xlnt"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE.md;md5=4a1677d641addb7678c5500246411979"

inherit cmake pkgconfig

PACKAGE_CONFIG ?= ""
PACKAGE_CONFIG[static] = "-DSTATIC=ON, -DSTATIC=OFF, "
PACKAGE_CONFIG[tests] = "-DTESTS=ON, -DTESTS=OFF, "
PACKAGE_CONFIG[samples] = "-DSAMPLES=ON, -DSAMPLES=OFF, "
PACKAGE_CONFIG[benchmarks] = "-DBENCHMARKS=ON, -DBENCHMARKS=OFF, "
PACKAGE_CONFIG[docs] = "-DDOCUMENTATION=ON, -DDOCUMENTATION=OFF, "

SRC_URI = "\
    gitsm://github.com/xlnt-community/xlnt.git;branch=master;protocol=https \
"
SRCREV = "3b25b084eab473bb6009b8e3d03ba5ff10bb4891"

S = "${WORKDIR}/git"

PV = "1.6.1+git${SRCPV}"
