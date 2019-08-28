/* 
 * File:   RFUtilsFiles.h
 * Author: diego
 *
 * Created on 28 de agosto de 2019, 17:58
 */
#include "./RFUtilsFiles.h"
#include "../out/RFUtilsHOut.h"

char * RFUTILSFILES::readTxtFile(char pathFile[]) {
    FILE *f = fopen(pathFile, "rb");
    
    fseek(f, 0, SEEK_END);
    long fsize = ftell(f);
    fseek(f, 0, SEEK_SET);
    
    char *dataFile = (char *) malloc(fsize + 1);
    
    fread(dataFile, fsize, 1, f);
    fclose(f);
    
    f = NULL;
    delete f;
    
    dataFile[fsize] = 0;
    return dataFile;
}