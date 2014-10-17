package config;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

public class web implements WebApplicationInitializer
{
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException
    {
        AnnotationConfigWebApplicationContext appContext =
            new AnnotationConfigWebApplicationContext();
        appContext.register(AppContext.class);
        appContext.register(WebAppContext.class);
        servletContext.addListener(new ContextLoaderListener(appContext));
        ServletRegistration.Dynamic dispatcher =
            servletContext.addServlet(
                "DispatcherServlet",
                new DispatcherServlet(appContext)
            )
        ;
        dispatcher.addMapping("/*");
    }
}
