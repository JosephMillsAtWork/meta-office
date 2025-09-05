SUMMARY = "Import filter library for MS Works"
HOMEPAGE = "https://sourceforge.net/projects/libwps/"
LICENSE = "MPL-2.0"
LIC_FILES_CHKSUM = " \
    file://COPYING.MPL;md5=815ca599c9df247a0c7f619bab123dad \
"

SRC_URI = " \
    https://sourceforge.net/projects/libwps/files/libwps/libwps-0.4.14/libwps-0.4.14.tar.xz \
    file://0001-avoid-hide-base-overload-wkssubdoc.patch \
"
SRC_URI[sha256sum] = "13beb0c733bb1544a542b6ab1d9d205f218e9a2202d1d4cac056f79f6db74922"

inherit autotools-brokensep pkgconfig

DEPENDS = "librevenge"
