package br.ufscar.pooa.cinema_api.adapters.out.database.repositories.manager;

import br.ufscar.pooa.cinema_api.domain.Manager;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ManagerJpaRepository extends JpaRepository<Manager, Long> {
    Optional<Manager> findByCpf(String cpf);
}
