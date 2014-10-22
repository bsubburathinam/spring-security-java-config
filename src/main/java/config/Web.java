package config;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

public class Web implements WebApplicationInitializer
{
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException
    {
        AnnotationConfigWebApplicationContext appContext =
            new AnnotationConfigWebApplicationContext();
        appContext.register(WebAppContextConfig.class);
        servletContext.addListener(new ContextLoaderListener(appContext));
        FilterRegistration.Dynamic filterDispatcher =
        servletContext.addFilter("springSecurityFilterChain", org.springframework.web.filter.DelegatingFilterProxy.class);
        ServletRegistration.Dynamic dispatcher =
            servletContext.addServlet(
                "DispatcherServlet",
                new DispatcherServlet(appContext)
            )
        ;
        dispatcher.addMapping("/*");
        filterDispatcher.addMappingForUrlPatterns(null, false, "/*");
    }
}
