package checkbooks.web;

import checkbooks.entity.Book;
import checkbooks.entity.ClientPageData;
import checkbooks.service.BookService;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.List;

/**
 * Created by pavel on 24.06.15.
 */


@Controller
public class RestController {

    @Autowired
    private BookService bookService;

    @ResponseBody
    @RequestMapping(value = "/rest")
    public String viewGood(@RequestParam(value = "bookId", required = true) Integer bookId,
    @RequestParam(value = "page", required = true) Integer page) throws IOException {

        ClientPageData clientPageData = bookService.getClientPageData(bookId, page);
        System.out.println(clientPageData);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", clientPageData.getId());
        jsonObject.put("pageCount", clientPageData.getPageCount());
        jsonObject.put("imageBase64", clientPageData.getImageBase64());


        return jsonObject.toString();
    }

    @ResponseBody
    @RequestMapping(value="/listBook",  produces={"application/json; charset=UTF-8"})
    public String getListBook(){
        JSONObject responseJson = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        List<Book> listBook = bookService.getListBook(30);

        for(Book book : listBook){
            JSONObject formDetailsJson = new JSONObject();
            formDetailsJson.put("id", String.valueOf(book.getId()));
            formDetailsJson.put("name", book.getName());
            formDetailsJson.put("year", book.getYear());
            formDetailsJson.put("author", book.getAuthor());
            formDetailsJson.put("pageCount", book.getPageCount());
            jsonArray.add(formDetailsJson);
        }
         responseJson.put("listBook", jsonArray);
        return responseJson.toString();

    }



}
