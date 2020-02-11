/* 
 * File:   RFUtilsFinancial.cpp
 * Author: diego
 *
 * Created on 26 de agosto de 2019, 17:41
 */

#ifndef RFUTILSFINANCIAL_H
#define RFUTILSFINANCIAL_H

namespace RFUTILSFINANCIAL {
    /**
     * Method to calculate VAN
     * @param initialOutlay
     * @param netFlows
     * @param sizeNetFlows
     * @param interests
     * @return 
     */
    float calculateVAN(double initialOutlay, double netFlows[], int sizeNetFlows, double interests);
}

#endif /* RFUTILSFINANCIAL_H */

