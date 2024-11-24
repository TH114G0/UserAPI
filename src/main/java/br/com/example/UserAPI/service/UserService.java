package br.com.example.UserAPI.service;

import br.com.example.UserAPI.entity.UserEntity;
import br.com.example.UserAPI.exception.UserException;
import br.com.example.UserAPI.repository.UserRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserEntity createUSer(UserEntity userEntity) {
        try {
            userRepository.save(userEntity);
            return userEntity;
        } catch (Exception e) {
            throw new UserException("Erro ao salvar no banco");
        }
    }

    public List<UserEntity> listUser() {
        try {
            Sort sort = Sort.by("name").ascending();
            return userRepository.findAll(sort);
        } catch (Exception e) {
            throw new UserException("Erro ao listar usuários");
        }
    }

    public UserEntity updateUser(UserEntity userEntity) {
        Optional<UserEntity> optionalUser = userRepository.findById(userEntity.getId());
        if (optionalUser.isPresent()) {
            userRepository.save(userEntity);
            return userEntity;
        }else {
            throw new UserException("Erro ao modificar usuário");
        }
    }

    public void deleteUser(Long id) {
        Optional<UserEntity> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            userRepository.deleteById(id);
        }else {
            throw new UserException("Erro ao modificar usuário");
        }
    }
}