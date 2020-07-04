package myapp.appconfig;

import com.sun.org.apache.xerces.internal.parsers.SecurityConfiguration;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{ApplicationConfig.class};
    }


    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{SecurityConfiguration.class};
    }


    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
}
