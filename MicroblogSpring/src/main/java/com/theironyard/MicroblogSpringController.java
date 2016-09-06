package com.theironyard;

import com.theironyard.Message; // since different packge, need to import message from model package
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;


@Controller
public class MicroblogSpringController {

    static ArrayList<Message> messageList = new ArrayList<>(); // create an arraylist for messages

    @RequestMapping(path = "/", method = RequestMethod.GET) // request mapping is / (homepage)
    public String home(Model model, HttpSession session) { // method that returns a string. uses model and session
        model.addAttribute("name", session.getAttribute("userName"));// we are going to add the attribute name, and the value for that is the attributes userName.
        //if never set username until now then it will be null
        model.addAttribute(messageList); //pass in arraylist into model
        return "home"; //name of template that we return
    }

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public String login(HttpSession session, String userName) { //uses session and username
        session.setAttribute("userName", userName); // give username that was passed in
        return "redirect:/"; //sees redirect: , it's not going to look up template it's going to redirect to that map (back to homepage)
    }

    @RequestMapping(path = "/add-message", method = RequestMethod.POST)
    public String addMessage(HttpSession session, String message) {
        session.setAttribute("text", message);// set the attribute "text" to the message that is passed in
        Message m = new Message(messageList.size() + 1, message); // create new message object, with id as the size of arraylist + 1
        messageList.add(m); // add object to arraylist
        return "redirect:/";
    }

    @RequestMapping(path = "/delete-message", method = RequestMethod.POST)
    public String deleteMessage(HttpSession session, Integer messageId) {
        session.setAttribute("messageId", messageId); // get the messageId
        //messageList.remove(messageId - 1); // this causes a bug b/c if you delete anything in the middle, numbers will shift
        // below tries to fix the bug...seems to work?
        for (int i = 0; i < messageList.size(); i++) {
            if (messageList.get(i).getid() == messageId) {
                messageList.remove(i);
            } //if id equals message id, then remove
        }
        for (int i = 0; i < messageList.size(); i++) {
            if (messageList.get(i).getid() > messageId) {
                messageList.get(i).setid(messageList.get(i).getid() - 1);
            } //if id is greater than message id, then minus 1

        }

        return "redirect:/";
    }

}
