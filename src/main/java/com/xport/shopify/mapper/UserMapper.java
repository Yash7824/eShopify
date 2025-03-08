package com.xport.shopify.mapper;

import com.xport.shopify.dto.UserDto;
import com.xport.shopify.model.User;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

public class UserMapper {
    public static User mapToUser(UserDto userDto){
        return new User(
                userDto.getUserId(),
                userDto.getUserName(),
                userDto.getUserEmail(),
                userDto.getUserPassword(),
                userDto.getUserMobile(),
                LocalDateTime.now()
        );
    }

    public static UserDto mapToUserDto(User user){
        return new UserDto(
                user.getUserId(),
                user.getUserName(),
                user.getUserEmail(),
                user.getUserPassword(),
                user.getUserMobile(),
                user.getCreatedAt()
        );
    }
}
