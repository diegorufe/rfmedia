/* 
 * File:   RFUtilsFinancial.h
 * Author: diego
 *
 * Created on 26 de agosto de 2019, 17:41
 */
#include "./RFUtilsFinancial.h"
#include <math.h> 
#include "../collections/RFUtilsHCollections.h"
#include "../out/RFUtilsHOut.h"
#include <iostream>
#include <stdlib.h>

float RFUTILSFINANCIAL::calculateVAN(double initialOutlay, double netFlows[], int sizeNetFlows, double interests) {
    double result = 0;

    if (sizeNetFlows > 0 && netFlows != NULL) {
        for (int i = 0; i < sizeNetFlows; i++) {
            result += netFlows[i] / pow(1 + interests, i + 1);
        }
    }

    return (-initialOutlay) +result;
}

