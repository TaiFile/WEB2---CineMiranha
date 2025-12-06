package br.ufscar.pooa.cinema_api.features._shared.gateways.repository;

import br.ufscar.pooa.cinema_api.domain.entities.Client;

import java.util.List;
import java.util.Optional;

public interface IClientRepository {
    Optional<Client> findById(Long id);

    Optional<Client> findByCpf(String cpf);

    List<Client> findAll();

    Client save(Client client);

    void delete(Client client);
}
