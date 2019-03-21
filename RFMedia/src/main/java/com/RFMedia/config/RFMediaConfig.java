package com.RFMedia.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.RFMedia.constants.IConstantsRFMedia;

@Configuration
@ComponentScan(basePackages = { IConstantsRFMedia.PACKAGES_SCAN_COMPONENTS })
public class RFMediaConfig {

}
