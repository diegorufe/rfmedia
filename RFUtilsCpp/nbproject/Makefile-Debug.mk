#
# Generated Makefile - do not edit!
#
# Edit the Makefile in the project folder instead (../Makefile). Each target
# has a -pre and a -post target defined where you can add customized code.
#
# This makefile implements configuration specific macros and targets.


# Environment
MKDIR=mkdir
CP=cp
GREP=grep
NM=nm
CCADMIN=CCadmin
RANLIB=ranlib
CC=gcc
CCC=g++
CXX=g++
FC=gfortran
AS=as

# Macros
CND_PLATFORM=MinGW-Windows
CND_DLIB_EXT=dll
CND_CONF=Debug
CND_DISTDIR=dist
CND_BUILDDIR=build

# Include project Makefile
include Makefile

# Object Directory
OBJECTDIR=${CND_BUILDDIR}/${CND_CONF}/${CND_PLATFORM}

# Object Files
OBJECTFILES= \
	${OBJECTDIR}/files/RFUtilsFiles.o \
	${OBJECTDIR}/math/RFUtilsFinancial.o

# Test Directory
TESTDIR=${CND_BUILDDIR}/${CND_CONF}/${CND_PLATFORM}/tests

# Test Files
TESTFILES= \
	${TESTDIR}/TestFiles/f2 \
	${TESTDIR}/TestFiles/f1

# Test Object Files
TESTOBJECTFILES= \
	${TESTDIR}/tests/RFUtilsFileTest.o \
	${TESTDIR}/tests/RFUtilsFinancialTest.o

# C Compiler Flags
CFLAGS=

# CC Compiler Flags
CCFLAGS=
CXXFLAGS=

# Fortran Compiler Flags
FFLAGS=

# Assembler Flags
ASFLAGS=

# Link Libraries and Options
LDLIBSOPTIONS=

# Build Targets
.build-conf: ${BUILD_SUBPROJECTS}
	"${MAKE}"  -f nbproject/Makefile-${CND_CONF}.mk ${CND_DISTDIR}/${CND_CONF}/${CND_PLATFORM}/libRFUtilsCpp.${CND_DLIB_EXT}

${CND_DISTDIR}/${CND_CONF}/${CND_PLATFORM}/libRFUtilsCpp.${CND_DLIB_EXT}: ${OBJECTFILES}
	${MKDIR} -p ${CND_DISTDIR}/${CND_CONF}/${CND_PLATFORM}
	${LINK.cc} -o ${CND_DISTDIR}/${CND_CONF}/${CND_PLATFORM}/libRFUtilsCpp.${CND_DLIB_EXT} ${OBJECTFILES} ${LDLIBSOPTIONS} -shared

${OBJECTDIR}/files/RFUtilsFiles.o: files/RFUtilsFiles.cpp
	${MKDIR} -p ${OBJECTDIR}/files
	${RM} "$@.d"
	$(COMPILE.cc) -g  -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/files/RFUtilsFiles.o files/RFUtilsFiles.cpp

${OBJECTDIR}/math/RFUtilsFinancial.o: math/RFUtilsFinancial.cpp
	${MKDIR} -p ${OBJECTDIR}/math
	${RM} "$@.d"
	$(COMPILE.cc) -g  -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/math/RFUtilsFinancial.o math/RFUtilsFinancial.cpp

# Subprojects
.build-subprojects:

# Build Test Targets
.build-tests-conf: .build-tests-subprojects .build-conf ${TESTFILES}
.build-tests-subprojects:

${TESTDIR}/TestFiles/f2: ${TESTDIR}/tests/RFUtilsFileTest.o ${OBJECTFILES:%.o=%_nomain.o}
	${MKDIR} -p ${TESTDIR}/TestFiles
	${LINK.cc} -o ${TESTDIR}/TestFiles/f2 $^ ${LDLIBSOPTIONS}   

${TESTDIR}/TestFiles/f1: ${TESTDIR}/tests/RFUtilsFinancialTest.o ${OBJECTFILES:%.o=%_nomain.o}
	${MKDIR} -p ${TESTDIR}/TestFiles
	${LINK.cc} -o ${TESTDIR}/TestFiles/f1 $^ ${LDLIBSOPTIONS}   


${TESTDIR}/tests/RFUtilsFileTest.o: tests/RFUtilsFileTest.cpp 
	${MKDIR} -p ${TESTDIR}/tests
	${RM} "$@.d"
	$(COMPILE.cc) -g -I. -MMD -MP -MF "$@.d" -o ${TESTDIR}/tests/RFUtilsFileTest.o tests/RFUtilsFileTest.cpp


${TESTDIR}/tests/RFUtilsFinancialTest.o: tests/RFUtilsFinancialTest.cpp 
	${MKDIR} -p ${TESTDIR}/tests
	${RM} "$@.d"
	$(COMPILE.cc) -g -I. -MMD -MP -MF "$@.d" -o ${TESTDIR}/tests/RFUtilsFinancialTest.o tests/RFUtilsFinancialTest.cpp


${OBJECTDIR}/files/RFUtilsFiles_nomain.o: ${OBJECTDIR}/files/RFUtilsFiles.o files/RFUtilsFiles.cpp 
	${MKDIR} -p ${OBJECTDIR}/files
	@NMOUTPUT=`${NM} ${OBJECTDIR}/files/RFUtilsFiles.o`; \
	if (echo "$$NMOUTPUT" | ${GREP} '|main$$') || \
	   (echo "$$NMOUTPUT" | ${GREP} 'T main$$') || \
	   (echo "$$NMOUTPUT" | ${GREP} 'T _main$$'); \
	then  \
	    ${RM} "$@.d";\
	    $(COMPILE.cc) -g  -Dmain=__nomain -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/files/RFUtilsFiles_nomain.o files/RFUtilsFiles.cpp;\
	else  \
	    ${CP} ${OBJECTDIR}/files/RFUtilsFiles.o ${OBJECTDIR}/files/RFUtilsFiles_nomain.o;\
	fi

${OBJECTDIR}/math/RFUtilsFinancial_nomain.o: ${OBJECTDIR}/math/RFUtilsFinancial.o math/RFUtilsFinancial.cpp 
	${MKDIR} -p ${OBJECTDIR}/math
	@NMOUTPUT=`${NM} ${OBJECTDIR}/math/RFUtilsFinancial.o`; \
	if (echo "$$NMOUTPUT" | ${GREP} '|main$$') || \
	   (echo "$$NMOUTPUT" | ${GREP} 'T main$$') || \
	   (echo "$$NMOUTPUT" | ${GREP} 'T _main$$'); \
	then  \
	    ${RM} "$@.d";\
	    $(COMPILE.cc) -g  -Dmain=__nomain -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/math/RFUtilsFinancial_nomain.o math/RFUtilsFinancial.cpp;\
	else  \
	    ${CP} ${OBJECTDIR}/math/RFUtilsFinancial.o ${OBJECTDIR}/math/RFUtilsFinancial_nomain.o;\
	fi

# Run Test Targets
.test-conf:
	@if [ "${TEST}" = "" ]; \
	then  \
	    ${TESTDIR}/TestFiles/f2 || true; \
	    ${TESTDIR}/TestFiles/f1 || true; \
	else  \
	    ./${TEST} || true; \
	fi

# Clean Targets
.clean-conf: ${CLEAN_SUBPROJECTS}
	${RM} -r ${CND_BUILDDIR}/${CND_CONF}

# Subprojects
.clean-subprojects:

# Enable dependency checking
.dep.inc: .depcheck-impl

include .dep.inc
