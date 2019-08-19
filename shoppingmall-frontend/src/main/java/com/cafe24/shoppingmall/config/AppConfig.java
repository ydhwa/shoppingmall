package com.cafe24.shoppingmall.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;

import com.cafe24.shoppingmall.config.app.AppSecurityConfig;
import com.cafe24.shoppingmall.config.app.OAuth2ClientConfig;
import com.cafe24.shoppingmall.config.web.MVCConfig;
import com.cafe24.shoppingmall.config.web.TracingConfig;

@Configuration
@EnableAspectJAutoProxy
@ComponentScan({ "com.cafe24.shoppingmall.security", "com.cafe24.shoppingmall.service", "com.cafe24.shoppingmall.repository", "com.cafe24.shoppingmall.aspect" })
@Import({ AppSecurityConfig.class, OAuth2ClientConfig.class, TracingConfig.class, MVCConfig.class })
public class AppConfig {
}
