package br.ufscar.pooa.cinema_api.features._shared.gateways.repository;

import br.ufscar.pooa.cinema_api.domain.entities.Manager;

import java.util.Optional;

public interface IManagerRepository {
    Optional<Manager> findById(Long id);

    Optional<Manager> findByCpf(String cpf);

    Manager save(Manager manager);

    void delete(Manager manager);
}
