package com.digital.controller.tchat;

import com.digital.model.SessionModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GreetingController {

    @Autowired SessionModel session;

    /*    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Greeting greeting(HelloMessage message) throws Exception {
        //Thread.sleep(1000); // simulated delay
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
      */
    /*  Assert.state(requestAttributes != null, "Could not find current request via RequestContextHolder");
        Assert.isInstanceOf(ServletRequestAttributes.class, requestAttributes);*/
    /*
        HttpServletRequest servletRequest = ((ServletRequestAttributes) requestAttributes).getRequest();
//        Assert.state(servletRequest != null, "Could not find current HttpServletRequest");
        //servletRequest.getSession().getId();
        System.out.println("session id => "+servletRequest.getSession().getId());
        //return new Greeting("Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!");
        return new Greeting("11204146",message.getName());
    }*/

    @GetMapping("/tchat")
    public String tchat() {
        return "tchat";
    }

}
