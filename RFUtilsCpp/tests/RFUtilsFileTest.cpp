/* 
 * File:   RFUtilsFileTest.cpp
 * Author: diego
 *
 * Created on 28 de agosto de 2019, 18:08
 */

#include <stdlib.h>
#include <iostream>
#include "../files/RFUtilsFiles.h"

/*
 * Simple C++ Test Suite
 */

void readTxtFile() {
    std::cout << "RFUtilsFileTest readTxtFile" << std::endl;
    char* pathFile = "test.txt";
    std::cout << RFUTILSFILES::readTxtFile(pathFile) << std::endl;
}
int main(int argc, char** argv) {
    std::cout << "%SUITE_STARTING% RFUtilsFileTest" << std::endl;
    
    std::cout << "%SUITE_STARTED%" << std::endl;
    std::cout << "%TEST_STARTED% readTxtFile (RFUtilsFileTest)" << std::endl;
    readTxtFile();
    std::cout << "%TEST_FINISHED% time=0 readTxtFile (RFUtilsFileTest)" << std::endl;
    
    return (EXIT_SUCCESS);
}

