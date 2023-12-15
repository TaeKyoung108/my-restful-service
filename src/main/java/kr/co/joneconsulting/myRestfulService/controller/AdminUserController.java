package kr.co.joneconsulting.myRestfulService.controller;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import jakarta.validation.Valid;
import kr.co.joneconsulting.myRestfulService.bean.AdminUser;
import kr.co.joneconsulting.myRestfulService.bean.AdminUserV2;
import kr.co.joneconsulting.myRestfulService.bean.User;
import kr.co.joneconsulting.myRestfulService.dao.userDaoService;
import kr.co.joneconsulting.myRestfulService.exception.UserNotCorrespondException;
import kr.co.joneconsulting.myRestfulService.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminUserController {
    private final userDaoService service;

    @GetMapping("/users")
    public MappingJacksonValue retrieveUser4Admin(){
        List<User> users = service.findAll();

        List<AdminUser> adminUsers = new ArrayList<>();
        AdminUser adminUser = null;

        if(users==null){
            throw new UserNotFoundException("user not found");

        }else {
            for(User user : users){
                adminUser = new AdminUser();
                BeanUtils.copyProperties(user, adminUser);
                adminUsers.add(adminUser);
            }

        }

        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("id","name","joinDate", "ssn");

        FilterProvider filters = new SimpleFilterProvider().addFilter("UserInfo", filter);

        MappingJacksonValue mapping = new MappingJacksonValue(adminUsers);
        mapping.setFilters(filters);

        return mapping;
    }

    @GetMapping("/v1/users/{id}")
    public MappingJacksonValue retrieveUser4Admin(@PathVariable int id){
        User user = service.findOne(id);

        AdminUser adminUser = new AdminUser();

        if(user==null){
            throw new UserNotFoundException(String.format("ID[%d] not fount", id));

        }else {
            // user 데이터를 adminUser에 그대로 넣어줌
            BeanUtils.copyProperties(user, adminUser);
        }

        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("id","name","joinDate", "ssn");

        FilterProvider filters = new SimpleFilterProvider().addFilter("UserInfo", filter);

        MappingJacksonValue mapping = new MappingJacksonValue(adminUser);
        mapping.setFilters(filters);

        return mapping;
    }

    @GetMapping("/v2/users/{id}")
    public MappingJacksonValue retrieveUserV2(@PathVariable int id){
        User user = service.findOne(id);

        AdminUserV2 adminUser = new AdminUserV2();

        if(user==null){
            throw new UserNotFoundException(String.format("ID[%d] not fount", id));

        }else {
            // user 데이터를 adminUser에 그대로 넣어줌
            BeanUtils.copyProperties(user, adminUser);
            adminUser.setGrade("VIP");
        }

        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("id","name","joinDate", "grade");

        FilterProvider filters = new SimpleFilterProvider().addFilter("UserInfoV2", filter);

        MappingJacksonValue mapping = new MappingJacksonValue(adminUser);
        mapping.setFilters(filters);

        return mapping;
    }

    @GetMapping(value = "/users/{id}", params = "version=3")
    public MappingJacksonValue retrieveUserV3(@PathVariable int id){
        User user = service.findOne(id);

        AdminUserV2 adminUser = new AdminUserV2();

        if(user==null){
            throw new UserNotFoundException(String.format("ID[%d] not fount", id));

        }else {
            // user 데이터를 adminUser에 그대로 넣어줌
            BeanUtils.copyProperties(user, adminUser);
            adminUser.setGrade("SVIP");
        }

        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("id","name","joinDate", "grade");

        FilterProvider filters = new SimpleFilterProvider().addFilter("UserInfoV2", filter);

        MappingJacksonValue mapping = new MappingJacksonValue(adminUser);
        mapping.setFilters(filters);

        return mapping;
    }

    @GetMapping(value = "/users/{id}", headers = "X-API-VERSION=4")
    public MappingJacksonValue retrieveUserV4(@PathVariable int id){
        User user = service.findOne(id);

        AdminUserV2 adminUser = new AdminUserV2();

        if(user==null){
            throw new UserNotFoundException(String.format("ID[%d] not fount", id));

        }else {
            // user 데이터를 adminUser에 그대로 넣어줌
            BeanUtils.copyProperties(user, adminUser);
            adminUser.setGrade("VVIP");
        }

        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("id","name","joinDate", "grade");

        FilterProvider filters = new SimpleFilterProvider().addFilter("UserInfoV2", filter);

        MappingJacksonValue mapping = new MappingJacksonValue(adminUser);
        mapping.setFilters(filters);

        return mapping;
    }

    @GetMapping(value = "/users/{id}", produces = "application/vnd.company.appv5+json")
    public MappingJacksonValue retrieveUserV5(@PathVariable int id){
        User user = service.findOne(id);

        AdminUserV2 adminUser = new AdminUserV2();

        if(user==null){
            throw new UserNotFoundException(String.format("ID[%d] not fount", id));

        }else {
            // user 데이터를 adminUser에 그대로 넣어줌
            BeanUtils.copyProperties(user, adminUser);
            adminUser.setGrade("VVVIP");
        }

        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("id","name","joinDate", "grade");

        FilterProvider filters = new SimpleFilterProvider().addFilter("UserInfoV2", filter);

        MappingJacksonValue mapping = new MappingJacksonValue(adminUser);
        mapping.setFilters(filters);

        return mapping;
    }
}
