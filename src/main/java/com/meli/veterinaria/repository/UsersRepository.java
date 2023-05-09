package com.meli.veterinaria.repository;

import com.meli.veterinaria.constant.DocumentType;
import com.meli.veterinaria.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {

    Optional<Users> findByDocumentNumberAndDocumentType(String  documentNumber , DocumentType documentType );
}