package com.nutrihub.config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// import com.ace.skream.user.commons.interceptor.SessionInterceptor;

@Configuration
// Spring의 설정 인 것을 명시

@EnableWebMvc
//Spring Mvc 활성화 Spring Mvc 설정 구상

@EnableScheduling
// 스프링의 스케줄링 기능 활성화 주기적으러 작업을 실행

public class AppConfig implements WebMvcConfigurer{

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 아래 3개 폴더 생성
        // /static/public/js
        // /static/public/css
        // /static/public/image


        registry.addResourceHandler("/public/**")
                .addResourceLocations("classpath:/static/public/")
                .setCachePeriod(3600)
        ;
        // .setCachePeriod(60 * 60 * 24 * 365);
        // /public/ 경로에 있는 정적 리소스 파일은 static/public  경로에서 찾아야한다고 설정
        // 정적 리소스 파일은 css, js, 이미지를 의미한다.

        // 업로드 외부 파일
        // 업로드 외부파일 설정은 c:/uploadFiles에 저장한다.
        registry.addResourceHandler("/uploadFiles/**")
                .addResourceLocations("file://C:/uploadFiles/")
        // .setCachePeriod(60 * 60 * 24 * 365);
        ;
    }

}