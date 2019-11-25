package ua.deti.plant_aware;

import javax.servlet.ServletContext;
import javax.servlet.http.*;

import org.thymeleaf.TemplateEngine;

public interface IController {

    public void process(
            HttpServletRequest request, HttpServletResponse response,
            ServletContext servletContext, TemplateEngine templateEngine);    
    
}