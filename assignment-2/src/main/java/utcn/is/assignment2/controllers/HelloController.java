package utcn.is.assignment2.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

// Specify that class is made to handle http requests
@Controller
@ResponseBody
@RequestMapping("hello") // anything that should begin with /hello
public class HelloController {

    // Handles request at path /hello
//    @GetMapping("hello")
//    @ResponseBody
//    public String hello() {
//        return "Hello, Spring!";
//    }

    //Handles request of the form /hello?name=xyz
    //@GetMapping("hello")
    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST}, value="hello")
    public String helloWithQueryParam(@RequestParam String name) {
        return "Hello, " + name;
    }

    //Handles request of the form /hello/xyz
    @GetMapping("hello/{name}")
    public String helloWithpathParam(@PathVariable String name) {
        return "Hello, " + name;
    }

    @GetMapping("form")
    public String helloForm(){
        return "<html>" +
                "<body>" +
                "<form action='hello' method='post'>" + //Submit a request to /hello
                "<input type='text' name='name'>" +
                "<input type='submit' value='Greet me!'>" +
                "<form>" +
                "<body>" +
                "<html>";
    }

}
