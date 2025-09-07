SUMMARY = "Scribus: Open source desktop publishing"
HOMEPAGE = "https://www.scribus.net/"
LICENSE = "GPL-2.0-or-later & LGPL-2.0-or-later & BSD-3-Clause & MIT"
LIC_FILES_CHKSUM = "file://COPYING;md5=300e791cb9c6b02206963fb25dfa9aa5"

inherit cmake_qt5 office_cmake pkgconfig python3native gtk-icon-cache mime mime-xdg

DEPENDS += " \
    qttools-native \
    freetype \
    cairo harfbuzz \
    boost \
    tiff libjpeg-turbo libpng \
    lcms \
    hunspell \
    poppler \
    libxml2 \
    podofo cups \
    boost \
    librevenge \
    fontconfig \
    ghostscript \
    libfreehand \
    libcdr \
    libmspub \
    libvisio \
    python3-native \
    pkgconfg-native \
"

# remove once we don't have patches any more
inherit dos2unix

SRC_URI = " \
    https://github.com/scribusproject/scribus.git;branch=Version16x;protocol=https \
"
SRCREV = "74a2559a554d78f6ae713723ad3e67c7597eb356"
EXTRA_OECMAKE:append = " -DWANT_CPP17=ON"

# python-imaging for the font sampler preview (only in py2 ?)
# GraphicksMagick++ needs recipe
