package com.meli.veterinaria.dto;

import com.meli.veterinaria.constant.DocumentType;
import com.meli.veterinaria.constant.SexType;
import com.meli.veterinaria.constant.StateType;
import com.meli.veterinaria.entity.Users;
import lombok.Data;

import java.io.Serializable;

/**
 * A DTO for the {@link Users} entity
 */
@Data
public class UsersSaveDto implements Serializable {

    private  String name;
    private  String lastName;
    private  DocumentType documentType;
    private  String documentNumber;
    private  StateType state;
    private  SexType sexType;

}