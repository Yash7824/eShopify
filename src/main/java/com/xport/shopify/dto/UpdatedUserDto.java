package com.xport.shopify.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdatedUserDto {
    private boolean Status;
    private String StatusMessage;
    private UserDto UserDto;
}
