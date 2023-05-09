package com.meli.veterinaria.mapper;

import com.meli.veterinaria.dto.UsersDto;
import com.meli.veterinaria.dto.UsersSaveDto;
import com.meli.veterinaria.entity.Users;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface UsersMapper {
    Users toEntity(UsersDto usersDto);

    UsersDto toDto(Users users);

    Users toEntityUsersSaveDto(UsersSaveDto usersSaveDto);

}