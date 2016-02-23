package checkbooks.web;

import checkbooks.entity.Book;
import checkbooks.service.BookService;
import it.sauronsoftware.ftp4j.FTPException;
import it.sauronsoftware.ftp4j.FTPIllegalReplyException;
import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.charset.Charset;

/**
 * Created by pc8 on 16.09.15.
 */
@Controller
public class AdminController {

    @Autowired
    private BookService bookService;

    private static Log log = LogFactory.getLog(AdminController.class.getName());

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ModelAndView registration(@RequestParam(value = "username", required = true)
                                     String username,
                                     @RequestParam(value = "pasword", required = true)
                                     String password,
                                     @RequestParam(value = "roles", required = true)
                                     String role) {

        ModelAndView model = new ModelAndView();
        model.addObject("title", "Spring Security + Hibernate Example");
        model.addObject("message", role + " is registered. Username:" + username + ", Password:" + password);
        model.setViewName("admin");

        return model;

    }

    @RequestMapping("/downloadGoodBooks")
    public void downloadGoodBooks(HttpServletResponse response) {
        response.setHeader("Content-disposition", "attachment; filename=goodBooks.xls");
        System.out.println(bookService.getBooksGood());
        StringBuilder stringBuilder = new StringBuilder();
        for (Book book : bookService.getBooksGood()) {
            stringBuilder.append(book.toStringForReport());
            stringBuilder.append("\n");
        }
        try {
            OutputStream outputStream = response.getOutputStream();
            outputStream.write(stringBuilder.toString().getBytes(Charset.forName("UTF-8")));

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    @RequestMapping("/downloadFailBooks")
    public void downloadFailBooks(HttpServletResponse response) {
        response.setHeader("Content-disposition", "attachment; filename=failBooks.xls");

        StringBuilder stringBuilder = new StringBuilder();
        for (Book book : bookService.getDescBooksFail()) {
            stringBuilder.append(book.toStringForReport());
            stringBuilder.append("\n");
        }
        try {
            OutputStream outputStream = response.getOutputStream();
            outputStream.write(stringBuilder.toString().getBytes(Charset.forName("UTF-8")));

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @RequestMapping("/moveGoodBooks")
    public ModelAndView moveGoodBooks(){
        ModelAndView model = new ModelAndView();
        try {
            bookService.changeStateGoodBook();
        } catch (Exception e) {
            model.addObject("message","Операция завершилась неудачей, читайте лог");
            log.error(e);
        }
      model.addObject("message", "Операция прошла успешно");
      model.setViewName("work");
     return model;
    }

}
