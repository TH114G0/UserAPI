package br.com.example.UserAPI.entity;

import br.com.example.UserAPI.exception.UserException;
import br.com.example.UserAPI.repository.UserRepository;
import br.com.example.UserAPI.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Sort;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class UserEntityTest {
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private UserService userService;

    public UserEntityTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createUserSuccess() {
        UserEntity userEntity = new UserEntity("Thiago", "thiago@example.com");
        when(userRepository.save(userEntity)).thenReturn(userEntity);

        UserEntity result = userService.createUSer(userEntity);

        assertEquals(userEntity.getName(), result.getName());
        assertEquals(userEntity.getEmail(), result.getEmail());
        verify(userRepository, times(1)).save(userEntity);
    }

    @Test
    void createUserFailure() {
        UserEntity userEntity = new UserEntity("Thiago", "thiago@example.com");
        when(userRepository.save(userEntity)).thenThrow(new RuntimeException("Erro ao salvar no banco"));

        RuntimeException runtimeException = assertThrows(RuntimeException.class, () -> {
            userService.createUSer(userEntity);
        });

        assertEquals("Erro ao salvar no banco", runtimeException.getMessage());
        verify(userRepository, times(1)).save(userEntity);
    }

    @Test
    void listUserSuccess() {
        List<UserEntity> mockUsers = Arrays.asList(
                new UserEntity("Alice", "alice@example.com"),
                new UserEntity("Bob", "bob@example.com")
        );

        when(userRepository.findAll(Sort.by("name").ascending())).thenReturn(mockUsers);
        List<UserEntity> result = userService.listUser();
        assertEquals(2, result.size());
        assertEquals("Alice", result.get(0).getName());
        assertEquals("Bob", result.get(1).getName());
        verify(userRepository, times(1)).findAll(Sort.by("name").ascending());
    }

    @Test
    void listUserFailure() {
        when(userRepository.findAll(Sort.by("name").ascending()))
                .thenThrow(new RuntimeException("Erro ao acessar o banco"));

        UserException exception = assertThrows(UserException.class, () -> {
            userService.listUser();
        });

        assertEquals("Erro ao listar usuários", exception.getMessage());

        verify(userRepository, times(1)).findAll(Sort.by("name").ascending());
    }

    @Test
    void updateUserSuccess() {
        UserEntity userEntity = new UserEntity("Thiago", "thiago@example.com");
        userEntity.setId(1L);

        when(userRepository.findById(userEntity.getId())).thenReturn(Optional.of(userEntity));
        when(userRepository.save(userEntity)).thenReturn(userEntity);

        UserEntity result = userService.updateUser(userEntity);

        assertEquals(userEntity.getName(), result.getName());
        assertEquals(userEntity.getEmail(), result.getEmail());

        verify(userRepository, times(1)).findById(userEntity.getId());
        verify(userRepository, times(1)).save(userEntity);
    }

    @Test
    void updateUserFailure() {
        UserEntity userEntity = new UserEntity("Thiago", "thiago@example.com");
        userEntity.setId(1L);

        when(userRepository.findById(userEntity.getId())).thenReturn(Optional.empty());

        UserException exception = assertThrows(UserException.class, () -> {
            userService.updateUser(userEntity);
        });

        assertEquals("Erro ao modificar usuário", exception.getMessage());

        verify(userRepository, times(1)).findById(userEntity.getId());
        verify(userRepository, times(0)).save(userEntity);
    }

    @Test
    void deleteUserSuccess() {
        UserEntity userEntity = new UserEntity("Thiago", "thiago@example.com");
        userEntity.setId(1L);

        when(userRepository.findById(userEntity.getId())).thenReturn(Optional.of(userEntity));

        userService.deleteUser(userEntity.getId());

        verify(userRepository, times(1)).deleteById(userEntity.getId());
    }

    @Test
    void deleteUserFailure() {

        UserEntity userEntity = new UserEntity("Thiago", "thiago@example.com");
        userEntity.setId(1L);

        when(userRepository.findById(userEntity.getId())).thenReturn(Optional.empty());

        UserException exception = assertThrows(UserException.class, () -> {
            userService.deleteUser(userEntity.getId());
        });

        assertEquals("Erro ao modificar usuário", exception.getMessage());

        verify(userRepository, times(0)).deleteById(userEntity.getId());
    }

}
