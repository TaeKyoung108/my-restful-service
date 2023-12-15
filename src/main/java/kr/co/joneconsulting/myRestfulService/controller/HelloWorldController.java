package kr.co.joneconsulting.myRestfulService.controller;


import kr.co.joneconsulting.myRestfulService.bean.HelloWorldBean;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

// restful 서비스에서는 화면 없으니 restController 로 감
@RestController
@AllArgsConstructor
public class HelloWorldController {

    private MessageSource messageSource;

//    public HelloWorldController(MessageSource messageSource) {
//        this.messageSource = messageSource;
//    }

    //  GET
    //  URI - /hello-world
    //  @RequestMapping(method=RequestMethod.GET, path="/hello-world")
    @GetMapping("/hello-world")
    public String helloWorld(){
        return "Hello World";
    }


    @GetMapping("/hello-world-bean")
    public HelloWorldBean helloWorldBean(){
        return new HelloWorldBean("hello-world!!");
    }

    @GetMapping("/hello-world-bean/path-variable/{name}")
    public HelloWorldBean helloWorldBeanPathVariable(@PathVariable String name){
        return new HelloWorldBean(String.format("Hello World, %s", name));
    }

    @GetMapping("/hello-world-internationalized")
    public String helloworldInternalized(
            @RequestHeader(name = "Accept-Language", required = false)Locale locale
            ){
        return messageSource.getMessage("greeting.message", null,locale);
    }
}
