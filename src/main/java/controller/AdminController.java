package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class AdminController
{

    @RequestMapping("/admin/addUser")
    public @ResponseBody Map<String,String> addUser(String username)
    {
        Map<String,String> usermap = new HashMap<String, String>(1);
        usermap.put("username", username);
        return usermap;
    }
}
