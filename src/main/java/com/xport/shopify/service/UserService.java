package com.xport.shopify.service;

import com.xport.shopify.dto.DeleteUserDto;
import com.xport.shopify.dto.UpdatedUserDto;
import com.xport.shopify.dto.UserDto;
import com.xport.shopify.mapper.UserMapper;
import com.xport.shopify.model.User;
import com.xport.shopify.repository.UserRepository;
import com.xport.shopify.service.interfaces.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.sql.Timestamp;

import java.time.LocalDateTime;
import java.util.*;

@Service
@AllArgsConstructor
public class UserService implements IUserService {

    private UserRepository userRepository;

    @Override
    public UserDto createUser(UserDto userdto){
        UserDto response = new UserDto();
        try{
            User user = UserMapper.mapToUser(userdto);
            User savedUser = userRepository.save(user);
            response = UserMapper.mapToUserDto(savedUser);
        }catch(Exception ex){
            System.out.println("Exception occurred: " + ex.getMessage());
        }

        return response;
    }

    @Override
    public UserDto getUser(UUID userId) {
        UserDto userDto = new UserDto();
        try{
            User user = userRepository.findById(userId).orElseThrow(() ->
                    new RuntimeException("User doesn't exists: " + userId));
            userDto = UserMapper.mapToUserDto(user);
        }catch(Exception ex){
            System.out.println("Exception occurred: " + ex.getMessage());
        }

        return userDto;
    }

    @Override
    public UserDto getUserQ(UUID userId) {
        UserDto userDto = new UserDto();
        try{
            Map<String, Object> getUser = userRepository.getUserQ(userId);
            Timestamp timestamp = (Timestamp) getUser.get("created_at");
            userDto.setUserId((UUID) getUser.get("user_id"));
            userDto.setUserName((String) getUser.get("user_name"));
            userDto.setUserEmail((String) getUser.get("user_email"));
            userDto.setUserPassword((String) getUser.get("user_password"));
            userDto.setUserMobile((String) getUser.get("user_mobile"));
            userDto.setCreatedAt(timestamp.toLocalDateTime());
        }catch(Exception ex){
            System.out.println("Exception occurred: " + ex.getMessage());
        }

        return userDto;
    }

    @Override
    public List<UserDto> getUsersQ() {
        List<UserDto> usersRS = new ArrayList<>();
        try{
            List<Map<String, Object>> users = userRepository.getUsersQ();
            for(Map<String, Object> user: users){
                UserDto userDto = new UserDto();
                Timestamp timestamp = (Timestamp) user.get("created_at");
                userDto.setUserId((UUID) user.get("user_id"));
                userDto.setUserName((String) user.get("user_name"));
                userDto.setUserEmail((String) user.get("user_email"));
                userDto.setUserPassword((String) user.get("user_password"));
                userDto.setUserMobile((String) user.get("user_mobile"));
                userDto.setCreatedAt(timestamp.toLocalDateTime());

                usersRS.add(userDto);
            }
        }catch(Exception ex){
            System.out.println("Exception occurred: " + ex.getMessage());
        }
        return usersRS;
    }

    @Override
    public UpdatedUserDto updateUserQ(UserDto userdto) {
        UpdatedUserDto updatedUserDto = new UpdatedUserDto();
        try{
            int rowsAffected = userRepository.updateUserQ(userdto.getUserName(), userdto.getUserEmail(),
                    userdto.getUserPassword(), userdto.getUserMobile(), userdto.getUserId());
            if(rowsAffected > 0){
                userdto = getUser(userdto.getUserId());
                updatedUserDto.setStatus(true);
                updatedUserDto.setStatusMessage("Updated successfully");
                updatedUserDto.setUserDto(userdto);
            }else{
                updatedUserDto.setStatus(false);
                updatedUserDto.setStatusMessage("Exception occurred");
            }
        }catch(Exception ex){
            System.out.println("Exception occurred: " + ex.getMessage());
        }

        return updatedUserDto;
    }

    @Override
    public DeleteUserDto deleteUserQ(UUID userId) {
        DeleteUserDto deleteUserDtoQ = new DeleteUserDto();
        try{
            UserDto userDto = getUser(userId);
            int rowsAffected = userRepository.deleteUserQ(userId);
            if(rowsAffected > 0){
                deleteUserDtoQ.setStatus(true);
                deleteUserDtoQ.setStatusMessage("Successfully deleted");
                deleteUserDtoQ.setUserDto(userDto);
            }else{
                deleteUserDtoQ.setStatus(false);
                deleteUserDtoQ.setStatusMessage("Exception occurred");
            }
        }catch(Exception ex){
            System.out.println("Exception occurred: " + ex.getMessage());
        }
        return deleteUserDtoQ;
    }
}
