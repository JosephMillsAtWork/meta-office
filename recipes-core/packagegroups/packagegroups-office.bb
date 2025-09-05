SUMMARY = "Office stack (apps, filters & runtime support) for Yocto"
DESCRIPTION = "Meta-package that pulls in document apps, a wide set of import/export filters, \
and common runtime support libraries used by office/document-processing tools."
LICENSE = "MIT"
PR = "r1"

inherit packagegroup allarch

ALLOW_EMPTY:${PN} = "1"

PACKAGES = " \
    ${PN} \
    ${PN}-filter \
    ${PN}-document processing \
"

RRECOMMENDS:${PN} = "\
    ${PN}-filter \
"

SUMMARY:${PN}-filter = "Import/export filters for many office and legacy formats"
DESCRIPTION:${PN}-filters = "Libraries that add support for reading/writing formats such as AbiWord, Corel, ePub, iWork, FreeHand, GLTF assets, MS Publisher, MacWrite, ODF generators, PageMaker, Visio, WordPerfect, WPG and WPS."
RRECOMMENDS:${PN}-filter = " \
    libabw \
    libcdr \
    libe-book \
    libetonyek \
    libfreehand \
    libgltf \
    libmspub \
    libmwaw \
    libodfgen \
    libpagemaker \
    libvisio \
    libwpd \
    libwpg \
    libwps \
"    
    
SUMMARY:${PN}-document-processing = "Common runtime support libraries for document processing"
DESCRIPTION:${PN}-support = "Helper libs used by the office stack: search/indexing, number conversion, shaping, math view, hyphenation, language tagging, text categorization, RDF, optimization, vectorization, etc."
RRECOMMENDS:${PN}-document-processing = " \
    clucene-core \
    double-conversion \
    graphite2 \
    gtkmathview \
    hyphen \
    libcmis \
    libexttextcat
    liblangtag \
    librevenge \
    lpsolve
    libmdds \
    mythes \
    potrace \
    rasqal redland \
    t1lib \
    wvware-tools \
    xlnt \
    ebook-tools \
"


