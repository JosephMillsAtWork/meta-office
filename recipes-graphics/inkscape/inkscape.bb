SUMMARY = "Inkscape is a Free and open source vector graphics editor"
HOMEPAGE = "https://inkscape.org/"
LICENSE = "GPL-3.0-or-later"
LIC_FILES_CHKSUM = "file://COPYING;md5=46f815712c095f667139ef42f2270d57"

SRC_URI = "gitsm://gitlab.com/inkscape/inkscape.git;protocol=https;branch=1.4.x"
SRCREV = "ebf0e940d050842d608d57f6a04e6daaa5c65f37"
S = "${WORKDIR}/git"
PV = "1.4.2"

DEPENDS = " \
    bdwgc \
    cairo \
    glib-2.0-native \
    gsl \
    gspell \
    gtkmm3 \
    harfbuzz \
    lcms \
    libsoup-2.4 \
    libxslt \
    pango \
    poppler \
    \
    double-conversion \
    lib2geom \
    libcdr \
    librevenge \
    libvisio \
    libwpg \
    potrace \
"

inherit cmake pkgconfig gettext gtk-icon-cache bash-completion mime-xdg

FILES:${PN} += "${datadir}/metainfo"
