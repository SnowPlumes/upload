package me.lv;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.servlet.MultipartConfigElement;

/**
 * @author lzw
 * @date 2018/6/13
 */
@Configuration
public class WebAppConfig extends WebMvcConfigurerAdapter{
    /**
     * 在配置文件中配置的文件保存路径
     */
    @Value("${upload.location}")
    private String location;
    @Value("${upload.maxFileSize}")
    private String maxFileSize;
    @Value("${upload.maxRequestSize}")
    private String maxRequestSize;

    @Bean
    public MultipartConfigElement multipartConfigElement() {

        MultipartConfigFactory factory = new MultipartConfigFactory();
        //文件最大KB,MB
        factory.setMaxFileSize(maxFileSize);
        //设置总上传数据总大小
        factory.setMaxRequestSize(maxRequestSize);
        factory.setLocation(location);
        return factory.createMultipartConfig();
    }
}
