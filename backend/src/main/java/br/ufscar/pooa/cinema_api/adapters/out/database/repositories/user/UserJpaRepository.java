package br.ufscar.pooa.cinema_api.adapters.out.database.repositories.user;

import br.ufscar.pooa.cinema_api.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserJpaRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
