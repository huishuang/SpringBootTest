package ccut.qinrushi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class LoginController{

    @RequestMapping("/hello")
    public String say(){
        return "crud";
    }
}
