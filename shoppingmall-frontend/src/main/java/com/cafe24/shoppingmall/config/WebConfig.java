package com.cafe24.shoppingmall.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;

import com.cafe24.shoppingmall.config.web.FileUploadConfig;
import com.cafe24.shoppingmall.config.web.MVCConfig;
import com.cafe24.shoppingmall.config.web.TracingConfig;

@Configuration
@EnableAspectJAutoProxy
@ComponentScan({"com.cafe24.shoppingmall.controller", "com.cafe24.shoppingmall.exception"})
@Import({ MVCConfig.class, FileUploadConfig.class })
public class WebConfig {
}
