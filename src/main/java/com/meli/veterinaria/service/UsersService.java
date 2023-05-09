package com.meli.veterinaria.service;

import com.meli.veterinaria.dto.UsersDto;
import com.meli.veterinaria.dto.UsersSaveDto;

import java.util.List;

public interface UsersService {
    UsersDto saveUsers(UsersSaveDto usersSaveDto);

    UsersDto updateUsers(UsersDto usersDto);

    List<UsersDto> usersDtoList();

    UsersDto getUserById(Long id);
}
