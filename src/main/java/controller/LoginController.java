package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController
{

    @RequestMapping("/renderLoginPage")
    public String renderLoginPage()
    {
        return "loginPage";
    }
}
