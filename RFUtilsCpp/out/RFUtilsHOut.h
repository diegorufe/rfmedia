/* 
 * File:   RFUtilsOut.h
 * Author: diego
 *
 * Created on 28 de agosto de 2019, 6:15
 */
#include <iostream>
#include <stdlib.h>

#ifndef RFUTILSOUT_H
#define RFUTILSOUT_H

namespace RFUTILSOUT {

    template <typename T>
    void outScreen(T data) {
        std::cout << data << std::endl;
    }
}


#endif /* RFUTILSOUT_H */

