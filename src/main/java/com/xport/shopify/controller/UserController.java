package com.xport.shopify.controller;

import com.xport.shopify.dto.DeleteUserDto;
import com.xport.shopify.dto.UpdatedUserDto;
import com.xport.shopify.dto.UserDto;
import com.xport.shopify.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;
import java.util.UUID;


@RestController()
@RequestMapping("/user")
@Validated
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/saveUser")
    public ResponseEntity<UserDto> saveUser(@Valid @RequestBody UserDto userdto){
        UserDto savedUserDto = userService.createUser(userdto);
        return new ResponseEntity<>(savedUserDto, HttpStatus.CREATED);
    }

    @GetMapping("/getUser")
    public ResponseEntity<UserDto> getUser(@RequestParam("userId") UUID id){
        UserDto getUserDto = userService.getUserQ(id);
        return ResponseEntity.ok(getUserDto);
    }

    @GetMapping("/getUsers")
    public ResponseEntity<List<UserDto>> getUsers(){
        return ResponseEntity.ok(userService.getUsersQ());
    }

    @PutMapping("/updateUser")
    public ResponseEntity<UpdatedUserDto> updateUser(@Valid @RequestBody UserDto userDto){
        return ResponseEntity.ok(userService.updateUserQ(userDto));
    }

    @DeleteMapping("/deleteUser")
    public ResponseEntity<DeleteUserDto> deleteUser(@RequestParam("userId") UUID id){
        return ResponseEntity.ok(userService.deleteUserQ(id));
    }
}
