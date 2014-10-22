package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

@Controller
public class IndexController
{
    @RequestMapping("/message")
    @ResponseBody
    public Map<String,String> getMessage(Principal principal)
    {
        Map<String,String> messageMap = new HashMap<String,String>();
        if (null == principal) {
            messageMap.put("message", "hello");
        }
        else
        {
            messageMap.put("message", "hello " + principal.getName());
        }
        return messageMap;
    }

}
