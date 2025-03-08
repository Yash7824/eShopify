package com.xport.shopify.service.interfaces;

import com.xport.shopify.dto.DeleteUserDto;
import com.xport.shopify.dto.UpdatedUserDto;
import com.xport.shopify.dto.UserDto;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface IUserService {
    UserDto createUser(UserDto userdto);
    UserDto getUser(UUID userId);
    UserDto getUserQ(UUID userId);
    List<UserDto> getUsersQ();
    UpdatedUserDto updateUserQ(UserDto userdto);
    DeleteUserDto deleteUserQ(UUID userId);
}
