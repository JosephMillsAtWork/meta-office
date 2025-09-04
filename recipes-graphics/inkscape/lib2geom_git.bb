SUMMARY = "2Geom: easy 2D graphics library"
HOMEPAGE = "https://gitlab.com/inkscape/lib2geom"

LICENSE = "LGPL-2.1-only"
LIC_FILES_CHKSUM = "file://COPYING-LGPL-2.1;md5=fad9b3332be894bab9bc501572864b29"

inherit cmake pkgconfig

DEPENDS = "boost double-conversion gtk+3 glib-2.0 cairo gsl"

SRC_URI = " \
    git://gitlab.com/inkscape/lib2geom.git;protocol=https;branch=1.4.x \
"

PV = "1.4.0"
SRCREV = "23316118755def8156c268f767894c145130e07d"
S = "${WORKDIR}/git"

EXTRA_OECMAKE = " \
	-D2GEOM_TESTING=FALSE \
	-D2GEOM_STANDALONE=TRUE \
	-D2GEOM_BUILD_SHARED=TRUE \
	-D2GEOM_USE_GPL_CODE=TRUE \
"
