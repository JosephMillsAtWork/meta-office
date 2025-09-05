SUMMARY = "2Geom: easy 2D graphics library"
HOMEPAGE = "https://gitlab.com/inkscape/lib2geom"

LICENSE = "LGPL-2.1-only"
LIC_FILES_CHKSUM = "file://COPYING-LGPL-2.1;md5=fad9b3332be894bab9bc501572864b29"

inherit office_cmake features_check


DEPENDS = " \
    boost \
    double-conversion \
    glib-2.0 
    cairo \
    gsl \    
"

SRC_URI = " \
    git://gitlab.com/inkscape/lib2geom.git;protocol=https;branch=1.4.x \
"

PV = "1.4.0"
SRCREV = "f060790083d0224da928d7c42c9fbfdc495aad854e2d"
S = "${WORKDIR}/git"
REQUIRED_DISTRO_FEATURES = "opengl"



# C++20  ragel Cython gtk4
## BASE 
# 2GEOM_BUILD_SHARED=ON 
# 2GEOM_USE_GPL_CODE=ON 
# 2GEOM_BOOST_PYTHON=ON"
# 2GEOM_STANDALONE
# WITH_PROFILING, 
# WITH_COVERAGE 

## 2geom (wants regal fun )

## src/perfpormance
# 2GEOM_PERFORMANCE_TESTS
# 2GEOM_PERFORMANCE_TESTS

## Cython backend
# 2GEOM_CYTHON_BUILD_SHARED=ON
# 2GEOM_CYTHON_BINDINGS=ON

## py2geom(wants boost-py py-cario)
# BUILD_BOOST_PYTHON_STATIC
# 2GEOM_BOOST_PYTHON

## src/toys
# 2GEOM_TOYS_LPE (live path)
# 2GEOM_TOYS
# 


## test want gtests	


EXTRA_OECMAKE = " \
	-D2GEOM_TESTING=FALSE \
	-D2GEOM_STANDALONE=TRUE \
	-D2GEOM_BUILD_SHARED=TRUE \
	-D2GEOM_USE_GPL_CODE=TRUE \
"
