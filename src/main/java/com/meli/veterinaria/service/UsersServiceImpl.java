package com.meli.veterinaria.service;

import com.meli.veterinaria.constant.DocumentType;
import com.meli.veterinaria.constant.SexType;
import com.meli.veterinaria.constant.StateType;
import com.meli.veterinaria.dto.UsersDto;
import com.meli.veterinaria.dto.UsersSaveDto;
import com.meli.veterinaria.entity.Users;
import com.meli.veterinaria.exception.DuplicateRecordException;
import com.meli.veterinaria.exception.NotFoundException;
import com.meli.veterinaria.mapper.UsersMapper;
import com.meli.veterinaria.repository.UsersRepository;
import com.meli.veterinaria.util.ValidationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsersServiceImpl implements UsersService{

    private final UsersRepository usersRepository;

    private final UsersMapper usersMapper;
    @Autowired
    public UsersServiceImpl(UsersRepository usersRepository, UsersMapper usersMapper) {
        this.usersRepository = usersRepository;
        this.usersMapper = usersMapper;
    }

    @Override
    public UsersDto saveUsers(UsersSaveDto usersSaveDto){
        validateData(usersSaveDto.getName(), usersSaveDto.getLastName(), usersSaveDto.getDocumentType(), usersSaveDto.getDocumentNumber(), usersSaveDto.getState(), usersSaveDto.getSexType());
        checkIfUserExists(usersSaveDto.getDocumentNumber(), usersSaveDto.getDocumentType(), null);
        return  usersMapper.toDto(usersRepository.save(usersMapper.toEntityUsersSaveDto(usersSaveDto)));
    }

    @Override
    public UsersDto updateUsers(UsersDto usersDto){
        validateData(usersDto.getName(), usersDto.getLastName(), usersDto.getDocumentType(), usersDto.getDocumentNumber(), usersDto.getState(), usersDto.getSexType());
        Optional<Users> existingUser = getUsers(usersDto.getDocumentNumber(), usersDto.getDocumentType());
        if (existingUser.isEmpty()) {
            throw new NotFoundException("No se pudo encontrar el usuario con el número de documento y tipo de documento especificados");
        }
        checkIfUserExists(usersDto.getDocumentNumber(), usersDto.getDocumentType(), existingUser.get().getId());
        Users updatedUser = usersMapper.toEntity(usersDto);
        updatedUser.setId(existingUser.get().getId());
        return  usersMapper.toDto(usersRepository.save(updatedUser));
    }

    @Override
    public List<UsersDto> usersDtoList(){
        return usersRepository.findAll().stream().map(usersMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public UsersDto getUserById(Long id) {
        Optional<Users> user = usersRepository.findById(id);
        if (user.isPresent()) {
            return usersMapper.toDto(user.get());
        } else {
            throw new NotFoundException("User not found");
        }
    }


    private void validateData(String name, String lastName, DocumentType documentType, String documentNumber, StateType state, SexType sexType) {
        ValidationUtils.validateRequiredField(name, "nombre");
        ValidationUtils.validateRequiredField(lastName, "apellidos");
        ValidationUtils.validateEnumValue(documentType, DocumentType.class, "tipo de documento");
        ValidationUtils.validateRequiredField(documentNumber, "numero documento");
        ValidationUtils.validateEnumValue(state, StateType.class, "Estado");
        ValidationUtils.validateEnumValue(sexType, SexType.class, "tipo de sexo");
    }


    private void checkIfUserExists(String documentNumber, DocumentType documentType, Long excludeUserId) {
        Optional<Users> existingUser = getUsers(documentNumber, documentType);
        if (existingUser.isPresent()) {
            if (excludeUserId == null || !existingUser.get().getId().equals(excludeUserId)) {
                throw new DuplicateRecordException("El usuario ya existe");
            }
        }
    }

    private Optional<Users> getUsers(String documentNumber, DocumentType documentType) {
        return usersRepository.findByDocumentNumberAndDocumentType(documentNumber, documentType);
    }


}
