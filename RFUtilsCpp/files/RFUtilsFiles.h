/* 
 * File:   RFUtilsFiles.h
 * Author: diego
 *
 * Created on 28 de agosto de 2019, 17:58
 */
#include <fstream>

#ifndef RFUTILSFILES_H
#define RFUTILSFILES_H


namespace RFUTILSFILES {
    
    /**
     * Method to read txt file
     * @param pathFile for read 
     * @return a array of data for file
     */
    char* readTxtFile(char pathFile[]);
}


#endif /* RFUTILSFILES_H */

