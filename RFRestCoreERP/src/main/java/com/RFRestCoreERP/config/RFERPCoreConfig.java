package com.RFRestCoreERP.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.RFCore.constants.ICoreConstants;

@Configuration
@ComponentScan(basePackages = { ICoreConstants.PACKAGES_SCAN_COMPONENTS })
public class RFERPCoreConfig {

}
