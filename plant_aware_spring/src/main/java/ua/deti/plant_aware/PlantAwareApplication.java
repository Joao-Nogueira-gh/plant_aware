package ua.deti.plant_aware;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

@SpringBootApplication
public class PlantAwareApplication {

	public static void main(String[] args) {
		SpringApplication.run(PlantAwareApplication.class, args);
	}

	private static TemplateEngine templateEngine;
    
    
    static {
        initializeTemplateEngine();
    }
    
    
    private static void initializeTemplateEngine() {
        
        ServletContextTemplateResolver templateResolver = 
            new ServletContextTemplateResolver(null);
        templateResolver.setTemplateMode("HTML");
        // This will convert "home" to "/WEB-INF/templates/home.html"
        templateResolver.setPrefix("/WEB-INF/templates/");
        templateResolver.setSuffix(".html");
        // Template cache TTL=1h. If not set, entries would be cached until expelled by LRU
        templateResolver.setCacheTTLMs(3600000L);
        
        templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);
        
    }

    private boolean process(HttpServletRequest request, HttpServletResponse response)
            throws ServletException {
            
        try {
                
            /*
            * Query controller/URL mapping and obtain the controller
            * that will process the request. If no controller is available,
            * return false and let other filters/servlets process the request.
            */
            IController controller = PlantAwareApplication.resolveControllerForRequest(request);
            if (controller == null) {
                return false;
            }
            /*
            * Obtain the TemplateEngine instance.
            */
            TemplateEngine templateEngine = PlantAwareApplication.getTemplateEngine();
                
            /*
            * Write the response headers
            */
            response.setContentType("text/html;charset=UTF-8");
            response.setHeader("Pragma", "no-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expires", 0);

            /*
            * Execute the controller and process view template,
            * writing the results to the response writer.
            */
            controller.process(
                    request, response, this.servletContext, templateEngine);

            return true;
                
        } catch (Exception e) {
            throw new ServletException(e);
        }
            
    }
}
