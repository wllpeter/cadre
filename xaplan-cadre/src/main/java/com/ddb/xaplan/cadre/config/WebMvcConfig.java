package com.ddb.xaplan.cadre.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.io.File;

/**
 * spring webMvc相关的设置
 * Created by 王凯斌 on 2017/4/25.
 */
@Configuration
@EnableAutoConfiguration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    @Value("${excel.output.path}")
    private String excelOutputPath;

    @Value("${excel.http.url}")
    private String excelHttpUrl;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        File file = new File(excelOutputPath);
        if (!file.exists()) {
            file.mkdirs();
        }
        registry
                .addResourceHandler(excelHttpUrl)
                .addResourceLocations("file:" + excelOutputPath);
    }
}
