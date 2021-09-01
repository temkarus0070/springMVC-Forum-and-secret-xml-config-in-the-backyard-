package org.temkarus0070.MvcApp.config;

import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
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

    @Override
    protected void customizeRegistration(ServletRegistration.Dynamic registration) {
        registration.setMultipartConfig(
                new MultipartConfigElement("/tmp")
        );
    }

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        super.onStartup(servletContext);
          registerServletFilter(servletContext);
       var servlet= servletContext.addServlet("multipartServlet", new DispatcherServlet(
                new AnnotationConfigWebApplicationContext()));
       servlet.setLoadOnStartup(1);
       servlet.addMapping("/");
        servlet.setMultipartConfig(new MultipartConfigElement("/tmp",50000000,500000,0));
    }


    private void registerServletFilter(ServletContext servletContext) {
        servletContext.addFilter("hiddenHttpMethodFilter",
                new HiddenHttpMethodFilter()).addMappingForUrlPatterns(
                null,true,"/*"
        );


    }
}
