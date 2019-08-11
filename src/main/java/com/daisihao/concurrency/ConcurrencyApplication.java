package com.daisihao.concurrency;

import com.daisihao.concurrency.threadlocal.HttpFilter;
import com.daisihao.concurrency.threadlocal.HttpInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class ConcurrencyApplication implements WebMvcConfigurer {


    public static void main(String[] args) {
        SpringApplication.run(ConcurrencyApplication.class, args);
    }

    @Bean
    public FilterRegistrationBean httpFilter(){
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        //指定具体的拦截器
        registrationBean.setFilter(new HttpFilter());
        //指定拦截路径
        registrationBean.addUrlPatterns("/threadlocal/*");
        return registrationBean;
    }

}
