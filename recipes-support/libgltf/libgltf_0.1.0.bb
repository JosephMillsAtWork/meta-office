SUMMARY = "A library for rendering glTF models"
HOMEPAGE = "https://wiki.documentfoundation.org/Development/libgltf"
LICENSE = "MPL-2.0"
LIC_FILES_CHKSUM = " \
    file://COPYING;md5=9741c346eef56131163e13b9db1241b3 \
"

SRC_URI = " \
    git://git.libreoffice.org/libgltf;branch=main;protocol=https \
"
SRCREV = "ef07151346918b04a2c029af3d0c674cb301fc6b"
inherit autotools pkgconfig features_check

REQUIRED_DISTRO_FEATURES = "opengl"

DEPENDS = " \
    glew glm \
    boost \
    libepoxy \
"
## TODO ADD RUNTIME OPENGL
