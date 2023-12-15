package kr.co.joneconsulting.myRestfulService.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import kr.co.joneconsulting.myRestfulService.bean.User;
import kr.co.joneconsulting.myRestfulService.dao.userDaoService;
import kr.co.joneconsulting.myRestfulService.exception.UserNotCorrespondException;
import kr.co.joneconsulting.myRestfulService.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequiredArgsConstructor
@Tag(name = "user-controller", description = "일반 사용자를 위한 컨트롤러")
public class UserController {
    private final userDaoService service;


    /**
     * 모든 유저 조회
     * @return
     */
    @GetMapping("/users")
    public List<User> retrieveAllUsers(){
        return service.findAll();
    }

    /**
     * 유저 조회
     * @param id
     * @return
     */
    @Operation(summary = "사용자 정보 조회 API",description = "사용자 ID를 이용해서 사용자 상세 정보 조회를 합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK !!"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST !!"),
            @ApiResponse(responseCode = "404", description = "User Not Found !!"),
            @ApiResponse(responseCode = "500", description = "Internal SERVER ERROR"),
        }
    )
    @GetMapping("/users/{id}")
    public EntityModel<User> retriveUser(
            @Parameter(description = "사용자 ID", required = true, example = "1") @PathVariable int id){
        User user = service.findOne(id);

        if(user == null){
            throw new UserNotFoundException(String.format("ID [%s] not found", id));

        }

        EntityModel entityModel = EntityModel.of(user);

        WebMvcLinkBuilder linTo = linkTo(methodOn(this.getClass()).retrieveAllUsers());
        entityModel.add(linTo.withRel("all-users")); // all-users ->  http://localhost:8080/users


        return entityModel;
    }

    /**
     * 유저 생성
     * @param user
     * @return
     */
    @PostMapping("/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user){
        User savedUser = service.save(user);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();
//        try {
//            service.save(user);
//        }catch (Exception e){
//            e.printStackTrace();
//            return null;
//        }
        return ResponseEntity.created(location).build();
    }

    /**
     * 유저 수정
     * @param user
     * @return
     */
    @PostMapping("/users/{id}")
    public ResponseEntity<User> changeUser(
            @PathVariable int id,
            @RequestBody User user){
        if(user.getId() != id){
            throw new UserNotCorrespondException(String.format("ID [%s] is not correspond", id));
        }

        User changedUser = service.change(user);

        if (changedUser == null){
            throw new UserNotFoundException(String.format("ID[%s] not found", id));
        }
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();


        return ResponseEntity.ok().body(changedUser);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity deleteUser(@PathVariable int id){
        User user = service.deleteById(id);

        if(user == null){
            throw new UserNotFoundException(String.format("ID[%s] not found", id));
        }

        // 여기선 지웠을때 해당 컨텐츠가 없다는 의미로 써본거
        return ResponseEntity.noContent().build();
    }
}
