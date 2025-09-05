SUMMARY = "Efficient binary-decimal and decimal-binary conversion routines"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://COPYING;md5=1ea35644f0ec0d9767897115667e901f"

SRC_URI = "git://github.com/google/double-conversion.git;branch=master;protocol=https"
SRCREV = "ae0dbfeb9744efd216c95b30555049d75d47116a"
PV = "3.3.1"
S = "${WORKDIR}/git"

inherit cmake
## FIXME add packgeconfig for all this
EXTRA_OECMAKE += "-DBUILD_SHARED_LIBS=ON"
