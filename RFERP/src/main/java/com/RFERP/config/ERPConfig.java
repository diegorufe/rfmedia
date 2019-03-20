package com.RFERP.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.RFERP.constants.IConstantsERP;

@Configuration
@ComponentScan(basePackages = { IConstantsERP.PACKAGES_SCAN_COMPONENTS })
public class ERPConfig {

}
