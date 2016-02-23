package checkbooks.web;

import checkbooks.service.FileService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@Controller
public class ImagesController {

    @Autowired
    private FileService fileService;

    @RequestMapping("/img_{bookId}")
    public void getImage(HttpServletResponse response,
                         @PathVariable("bookId") int bookId,
                         @RequestParam("pageId") String pageId) throws IOException {
        try (InputStream is = new FileInputStream(fileService.getPage(bookId, pageId))) {
            IOUtils.copy(is, response.getOutputStream());
        }
    }
}