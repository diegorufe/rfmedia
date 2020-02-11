/* 
 * File:   RFUtilsCollections.h
 * Author: diego
 *
 * Created on 26 de agosto de 2019, 18:11
 */
#include <iostream>
#include <stdlib.h>
#include "../out/RFUtilsHOut.h"

#ifndef RFUTILSCOLLECTIONS_H
#define RFUTILSCOLLECTIONS_H

/**
 * Function to get the size of array
 */
#define RF_ARRAY_SIZE(x)(sizeof (x) / sizeof (*x))

namespace RFUTILSCOLLECTIONS {
    // One function works for all data types. 
    // This would work even for user defined types 
    // if operator '>' is overloaded 
}

#endif /* RFUTILSCOLLECTIONS_H */

