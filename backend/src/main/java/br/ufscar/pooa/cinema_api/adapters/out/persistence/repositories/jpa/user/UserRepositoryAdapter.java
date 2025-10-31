package br.ufscar.pooa.cinema_api.adapters.out.persistence.repositories.jpa.user;

import br.ufscar.pooa.cinema_api.adapters.out.persistence.entities.UserEntity;
import br.ufscar.pooa.cinema_api.application.ports.out.mapper.IObjectMapper;
import br.ufscar.pooa.cinema_api.application.ports.out.repository.IUserRepository;
import br.ufscar.pooa.cinema_api.domain.User;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Profile("jpa")
@Repository
public class UserRepositoryAdapter implements IUserRepository {
    private final UserJpaRepository userJpaRepository;
    private final IObjectMapper objectMapper;

    public UserRepositoryAdapter(UserJpaRepository userJpaRepository, IObjectMapper objectMapper) {
        this.userJpaRepository = userJpaRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    public User save(User user) {
        UserEntity entity = objectMapper.parseObject(user, UserEntity.class);

        return objectMapper.parseObject(userJpaRepository.save(entity), User.class);
    }

    @Override
    public Optional<User> findById(Long id) {
        if (id == null) return Optional.empty();

        Optional<UserEntity> entity = userJpaRepository.findById(id);

        return entity.map(userEntity -> objectMapper.parseObject(entity, User.class));
    }

    @Override
    public Optional<User> findByEmail(String email) {
        if (email == null) return Optional.empty();

        Optional<UserEntity> entity = userJpaRepository.findByEmail(email);

        return entity.map(userEntity -> objectMapper.parseObject(entity, User.class));
    }

    @Override
    public void delete(User usuario) {
        userJpaRepository.delete(objectMapper.parseObject(usuario, UserEntity.class));
    }
}
