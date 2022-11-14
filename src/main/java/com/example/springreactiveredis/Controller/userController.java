package com.example.springreactiveredis.Controller;

import com.example.springreactiveredis.Model.user;
import com.example.springreactiveredis.Service.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.Map;

@RestController
@RequestMapping("/send")
public class userController {

    @Autowired
    userService userService;

    @PostMapping("/user/{id}")
    Mono<user> enterUserInDb(@PathVariable String id, @RequestBody Mono<user> userMono){
        return this.userService.enterUserInDb(id, userMono);
    }

    @GetMapping("/{id}")
    Mono<user> getUserInfo(@PathVariable String  id){
        return this.userService.getUserDetails(id);
    }

    @PutMapping("/{id}")
    Mono<user> updateUserInfo(@PathVariable String id, @RequestBody Mono<user> userMono){
        return this.userService.updateUserInDb(id, userMono);
    }

}
