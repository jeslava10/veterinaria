package com.meli.veterinaria.repository;

import com.meli.veterinaria.entity.Users;
import com.meli.veterinaria.factories.UsersFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UsersRepositoryTests {

    private Users usersFactory1;

    private Users usersFactory2;

    @Autowired
    private UsersRepository usersRepository;


    @BeforeEach
    void setUp() {
        usersFactory1 = new UsersFactory().setId(null).newInstance();
        usersFactory2 = new UsersFactory().setId(null).setDocumentNumber("123456789").setName("Pedro").setLastName("marquez").newInstance();
    }


    @Test
    public void should_find_no_users_if_repository_is_empty() {
        List<Users> usersList = usersRepository.findAll();
        assertThat(usersList).isEmpty();
    }

    @Test
    public void should_save_user() {
        Users savedUsers = usersRepository.save(usersFactory1);
        Optional<Users> retrievedUsers = usersRepository.findById(savedUsers.getId());
        assertThat(retrievedUsers.isPresent()).isTrue();
        assertThat(retrievedUsers.get().getName()).isEqualTo(usersFactory1.getName());
        assertThat(retrievedUsers.get().getLastName()).isEqualTo(usersFactory1.getLastName());
        assertThat(retrievedUsers.get().getDocumentType()).isEqualTo(usersFactory1.getDocumentType());
        assertThat(retrievedUsers.get().getDocumentNumber()).isEqualTo(usersFactory1.getDocumentNumber());
        assertThat(retrievedUsers.get().getState()).isEqualTo(usersFactory1.getState());
        assertThat(retrievedUsers.get().getSexType()).isEqualTo(usersFactory1.getSexType());
    }

    @Test
    void should_update_user() {
        Users savedUser = usersRepository.save(usersFactory1);
        savedUser.setName("Pedro");
         usersRepository.save(savedUser);
        Optional<Users> foundUser = usersRepository.findById(savedUser.getId());
        assertTrue(foundUser.isPresent());
        assertEquals("Pedro", foundUser.get().getName());
    }

    @Test
    void should_find_all_users() {
        Users savedUser1 = usersFactory1;
        Users savedUser2 = usersFactory2;
        usersRepository.save(savedUser1);
        usersRepository.save(savedUser2);
        List<Users> users = usersRepository.findAll();
        assertThat(users).containsExactlyInAnyOrder(savedUser1, savedUser2);
    }

    @Test
    public void should_find_users_by_id() {
        Users savedUser = usersRepository.save(usersFactory1);
        Optional<Users> foundUser = usersRepository.findById(savedUser.getId());
        assertTrue(foundUser.isPresent());
        assertThat(foundUser.get()).isEqualTo(savedUser);
    }


}
