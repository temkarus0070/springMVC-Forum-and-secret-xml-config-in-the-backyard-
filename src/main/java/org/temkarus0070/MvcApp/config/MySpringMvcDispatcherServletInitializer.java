package org.temkarus0070.MvcApp.config;

import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import org.temkarus0070.MvcApp.Filters.SimpleCORSFilter;
import org.temkarus0070.MvcApp.config.secure.SecureConfig;

import javax.servlet.*;

public class MySpringMvcDispatcherServletInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{SecureConfig.class,SpringConfig.class};
    }


    @Override
    protected Class<?>[] getServletConfigClasses() {
        return null;
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }





    private void registerServletFilter(ServletContext servletContext) {
        servletContext.addFilter("hiddenHttpMethodFilter",
                new HiddenHttpMethodFilter()).addMappingForUrlPatterns(
                null,true,"/*"
        );
        servletContext.addFilter("corsFilter",new SimpleCORSFilter()).addMappingForUrlPatterns(null,true,"/*","/**","/register");


    }
}
