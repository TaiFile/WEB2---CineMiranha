package br.ufscar.pooa.cinema_api.adapters.out.persistence.repositories.jpa.manager;

import br.ufscar.pooa.cinema_api.adapters.out.persistence.entities.ManagerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ManagerJpaRepository extends JpaRepository<ManagerEntity, Long> {
    Optional<ManagerEntity> findByCpf(String cpf);
}
