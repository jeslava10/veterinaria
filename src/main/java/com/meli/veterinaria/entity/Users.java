package com.meli.veterinaria.entity;


import com.meli.veterinaria.constant.DocumentType;
import com.meli.veterinaria.constant.SexType;
import com.meli.veterinaria.constant.StateType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "usuario")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="nombre")
    private  String name;

    @Column(name="apellido")
    private  String lastName;

    @Column(name="tipo_documento")
    @Enumerated(EnumType.STRING)
    private DocumentType documentType;

    @Column(name="documento_identificacion")
    private  String  documentNumber;

    @Column(name="estado")
    @Enumerated(EnumType.STRING)
    private StateType state;

    @Column(name="sexo")
    @Enumerated(EnumType.STRING)
    private SexType sexType;


}
