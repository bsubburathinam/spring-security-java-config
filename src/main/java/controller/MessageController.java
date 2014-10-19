package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class MessageController
{
    @RequestMapping("/message")
    @ResponseBody
    public Map<String,String> getMessage()
    {
        Map<String,String> messageMap = new HashMap<String,String>();
        messageMap.put("message", "hello");
        return messageMap;
    }

}
