package com.theironyard;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;

@Controller
public class MicroblogSpringController {

    ArrayList<Message> messages = new ArrayList<>();

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String home(Model model, HttpSession session) {

        model.addAttribute("name", session.getAttribute("userName"));
        model.addAttribute("messages", session.getAttribute("messages"));

        return ("home");
    }

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public String login(HttpSession session, String userName) {

        session.setAttribute("userName", userName);

        return "redirect:/";
    }

    @RequestMapping(path = "/add-message", method = RequestMethod.POST)
    public String message(HttpSession session, String message) {

        messages.add(new Message((messages.size() + 1), message));

        session.setAttribute("messages", messages);

        return "redirect:/";
    }

    @RequestMapping(path = "/delete-message", method = RequestMethod.POST)
    public String delete(HttpSession session, Integer id) {

        session.setAttribute("id", id);

        for (int i = 0; i < messages.size(); i++) {
            if (messages.get(i).getId() == id) {
                messages.remove(i);
            }
        }

        // cool loop Jenni added to reset id's after a message is deleted
        for (int i = 0; i < messages.size(); i++) {
            if (messages.get(i).getId() > id) {
                messages.get(i).setId(messages.get(i).getId() - 1);
            }
        }

        session.setAttribute("messages", messages);

        return "redirect:/";

    }
}
