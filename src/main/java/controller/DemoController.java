package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class DemoController
{
    @RequestMapping("/demo")
    public @ResponseBody Map<String, String> getDemoMessage()
    {
        Map<String,String> messageMap = new HashMap<String,String>();
        messageMap.put("message", "hello demo user");
        return messageMap;

    }
}
