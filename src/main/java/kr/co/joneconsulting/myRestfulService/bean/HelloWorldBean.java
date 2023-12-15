package kr.co.joneconsulting.myRestfulService.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
public class HelloWorldBean {
    private final String message;

//    public HelloWorldBean(String message) {
//        this.message = message;
//    }
}
