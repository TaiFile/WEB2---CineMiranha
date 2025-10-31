package br.ufscar.pooa.cinema_api.application.ports.out.repository;

import br.ufscar.pooa.cinema_api.domain.Manager;

import java.util.Optional;

public interface IManagerRepository {
    Optional<Manager> findById(Long id);

    Optional<Manager> findByCpf(String cpf);

    Manager save(Manager manager);

    void delete(Manager manager);
}
