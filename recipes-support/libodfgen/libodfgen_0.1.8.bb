SUMMARY = "An ODF generator library"
HOMEPAGE = "https://sourceforge.net/p/libwpd/wiki/libodfgen"
LICENSE = "MPL-2.0"
LIC_FILES_CHKSUM = " \
    file://COPYING.MPL;md5=815ca599c9df247a0c7f619bab123dad \
"

SRC_URI = "https://sourceforge.net/projects/libwpd/files/libodfgen/libodfgen-0.1.8/libodfgen-0.1.8.tar.xz"
SRC_URI[md5sum] = "08c95c4b18c490a60394cc92fe9cb303"
SRC_URI[sha256sum] = "323e491f956c8ca2abb12c998e350670930a32317bf9662b0615dd4b3922b831"

inherit autotools pkgconfig perlnative

DEPENDS = "librevenge"
