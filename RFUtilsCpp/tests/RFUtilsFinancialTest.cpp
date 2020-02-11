/* 
 * File:   RFUtilsFinancialTest.cpp
 * Author: diego
 *
 * Created on 26 de agosto de 2019, 20:24
 */
#include <iostream>
#include <stdlib.h>

#include "../math/RFUtilsFinancial.h"
#include "../collections/RFUtilsHCollections.h"

/*
 * Simple C++ Test Suite
 */

void testVAN() {
    double initialOutlay = 70;
    double netFlows[3] = {15, 60};
    double interests = 0.05;
    std::cout << "RFUtilsFinancialTest testVAN" << std::endl;
    std::cout << RFUTILSFINANCIAL::calculateVAN(initialOutlay, netFlows, RF_ARRAY_SIZE(netFlows), interests) << std::endl;
}

int main(int argc, char** argv) {
    std::cout << "%SUITE_STARTING% RFUtilsFinancialTest" << std::endl;
    std::cout << "%SUITE_STARTED%" << std::endl;

    std::cout << "%TEST_STARTED% testVAN (RFUtilsFinancialTest)" << std::endl;
    testVAN();
    std::cout << "%TEST_FINISHED% time=0 testVAN (RFUtilsFinancialTest)" << std::endl;

    return (EXIT_SUCCESS);
}

