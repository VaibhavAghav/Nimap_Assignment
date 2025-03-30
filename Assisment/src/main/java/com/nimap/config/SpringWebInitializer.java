package com.nimap.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;


public class SpringWebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer{

	@Override
	protected Class<?>[] getRootConfigClasses() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[] {MVCConfiguration.class};
	}

	@Override
	protected String[] getServletMappings() {
		String[] allowedUrl = {"/"};
		return allowedUrl;
	}

}
