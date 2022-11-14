package com.example.springreactiveredis.Service;

import com.example.springreactiveredis.Model.user;
import org.redisson.api.RMapReactive;
import org.redisson.api.RedissonReactiveClient;
import org.redisson.codec.TypedJsonJacksonCodec;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class userService {


    RMapReactive<String, user> userDb;

    public userService(RedissonReactiveClient client){
        this.userDb = client.getMap("user", new TypedJsonJacksonCodec(String.class, user.class));
    }

    public Mono<user> getUserDetails(String id){
       return this.userDb.get(id);
    }

    public  Mono<user> enterUserInDb(String id, Mono<user> u){
        return  u.flatMap(i -> this.userDb.fastPut(id, i).thenReturn(i));
    }

    public Mono<user> updateUserInDb(String id, Mono<user> u){
        return u.flatMap(i -> this.userDb.fastPut(id, i).thenReturn(i));
    }

//    public Mono<user> deleteUserInDb(String id, Mono<user> u){
//        return this.userDb.delete(id);
//    }
}
