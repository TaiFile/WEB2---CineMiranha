package br.ufscar.pooa.cinema_api.domain.repositories.user;

import br.ufscar.pooa.cinema_api.features._shared.gateways.repository.IUserRepository;
import br.ufscar.pooa.cinema_api.domain.entities.User;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Profile("jpa")
@Repository
public class UserRepositoryAdapter implements IUserRepository {
    private final UserJpaRepository userJpaRepository;

    public UserRepositoryAdapter(UserJpaRepository userJpaRepository) {
        this.userJpaRepository = userJpaRepository;
    }

    @Override
    public User save(User user) {
        return userJpaRepository.save(user);
    }

    @Override
    public Optional<User> findById(Long id) {
        if (id == null) return Optional.empty();

        return userJpaRepository.findById(id);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        if (email == null) return Optional.empty();

        return userJpaRepository.findByEmail(email);
    }

    @Override
    public void delete(User user) {
        userJpaRepository.delete(user);
    }
}
