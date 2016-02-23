package checkbooks.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Error {

    @RequestMapping(value = "error")
    public String Error(){
        return "error";
    }

}
