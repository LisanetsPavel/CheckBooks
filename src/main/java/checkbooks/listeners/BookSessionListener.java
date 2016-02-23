package checkbooks.listeners;

import checkbooks.service.BookService;
import checkbooks.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class BookSessionListener implements HttpSessionListener, ServletContextListener{

    @Autowired
    private BookService bookService;

    @Autowired
    private FileService fileService;

    public void contextInitialized(ServletContextEvent sce) {
        WebApplicationContextUtils
                .getRequiredWebApplicationContext(sce.getServletContext())
                .getAutowireCapableBeanFactory()
                .autowireBean(this);
        fileService.deleteAllFiles();
    }


    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        fileService.deleteAllFiles();
    }

    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {

          }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {

        String bookId = (String) httpSessionEvent.getSession().getAttribute("bookId");
        if (bookId != null) {
            bookService.setProcessing(Integer.parseInt(bookId), false);
            fileService.deleteFiles(Integer.parseInt(bookId));
        }
    }


}
