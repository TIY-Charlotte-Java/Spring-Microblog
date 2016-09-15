package com.theironyard;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

@Controller
public class MicroblogSpringController {

    @RequestMapping (path = "/", method = RequestMethod.GET)
    public String home (Model model, HttpSession session) {

        model.addAttribute("name", session.getAttribute("userName"));

        return ("home");
    }

    @RequestMapping (path = "/login", method = RequestMethod.POST)
    public String login(HttpSession session, String userName) {

        session.setAttribute("userName", userName);

        return "redirect:/";
    }

    
}
