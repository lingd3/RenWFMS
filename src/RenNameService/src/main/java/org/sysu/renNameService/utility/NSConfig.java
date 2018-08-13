package org.sysu.renNameService.utility;

import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

public class NSConfig extends WebMvcConfigurerAdapter {

	public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new NSInterceptor()).addPathPatterns("/**");
    }
	
}
