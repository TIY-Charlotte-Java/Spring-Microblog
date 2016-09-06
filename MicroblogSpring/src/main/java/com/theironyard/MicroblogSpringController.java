package com.theironyard;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;

/**
 * Created by mfahrner on 9/2/16.
 */

@Controller
public class MicroblogSpringController {

    ArrayList<Message> messageList = new ArrayList<>();

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String home(Model model, HttpSession session) {
        model.addAttribute("name", session.getAttribute("userName"));
        model.addAttribute("messageList", messageList);
        return "home";
    }

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public String login(HttpSession session, String userName) {
        session.setAttribute("userName", userName);
        return "redirect:/";
    }

    @RequestMapping(path = "/add-message", method = RequestMethod.POST)
    public String addMessage(String message) {
        Message m = new Message(messageList.size()+ 1, message);
        messageList.add(m);
        return "redirect:/";
    }

    @RequestMapping(path = "/delete-message", method = RequestMethod.POST)
    public String deleteMessage(Integer id) {
        for (int i = 0; i < messageList.size(); i++) {
            if (messageList.get(i).id == id) {
                messageList.remove(i);
            }
        }
        return "redirect:/";
    }
}
