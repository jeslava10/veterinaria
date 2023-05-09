package com.meli.veterinaria.factories;

import com.meli.veterinaria.constant.DocumentType;
import com.meli.veterinaria.constant.SexType;
import com.meli.veterinaria.constant.StateType;
import com.meli.veterinaria.dto.UsersDto;
import com.meli.veterinaria.entity.Users;

public class UsersFactory {

    private  Long id;
    private  String name;
    private  String lastName;
    private  DocumentType documentType;
    private  String documentNumber;
    private final StateType state;
    private final SexType sexType;

    public UsersFactory() {
        // Initialize the factory with default values
        this.id = 1L;
        this.name = "John";
        this.lastName = "Doe";
        this.documentType = DocumentType.CEDULA;
        this.documentNumber = "12345678";
        this.state = StateType.ACTIVO;
        this.sexType = SexType.HOMBRE;

    }

    public Users newInstance() {
        Users existingUser = new Users();
        existingUser.setId(this.id);
        existingUser.setName(this.name);
        existingUser.setLastName(this.lastName);
        existingUser.setDocumentType(this.documentType);
        existingUser.setDocumentNumber(this.documentNumber);
        existingUser.setState(this.state);
        existingUser.setSexType(this.sexType);
        return existingUser;
    }


    public UsersDto newInstanceDto() {
        UsersDto usersDto = new UsersDto();
        usersDto.setId(this.id);
        usersDto.setName(this.name);
        usersDto.setLastName(this.lastName);
        usersDto.setDocumentType(this.documentType);
        usersDto.setDocumentNumber(this.documentNumber);
        usersDto.setState(this.state);
        usersDto.setSexType(this.sexType);
        return usersDto;
    }

    public UsersFactory setId(Long id) {
        this.id = id;
        return this;
    }

    public UsersFactory setName(String name) {
        this.name = name;
        return this;
    }

    public UsersFactory setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public UsersFactory setDocumentType(DocumentType documentType) {
        this.documentType = documentType;
        return this;
    }

    public UsersFactory setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
        return this;
    }


}
