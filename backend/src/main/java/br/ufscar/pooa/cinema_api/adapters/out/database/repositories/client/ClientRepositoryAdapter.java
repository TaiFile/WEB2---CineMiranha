package br.ufscar.pooa.cinema_api.adapters.out.database.repositories.client;

import br.ufscar.pooa.cinema_api.application.ports.out.repository.IClientRepository;
import br.ufscar.pooa.cinema_api.domain.Client;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ClientRepositoryAdapter implements IClientRepository {
    private final ClientJpaRepository clientJpaRepository;

    public ClientRepositoryAdapter(ClientJpaRepository clientJpaRepository) {
        this.clientJpaRepository = clientJpaRepository;
    }

    @Override
    public Client save(Client client) {
        return clientJpaRepository.save(client);
    }

    @Override
    public void delete(Client client) {
        if (client != null && client.getId() != null) {
            clientJpaRepository.deleteById(client.getId());
        } else {
            throw new IllegalArgumentException("Client or Client ID cannot be null");
        }
    }

    @Override
    public Optional<Client> findById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID cannot be null");
        }
        return clientJpaRepository.findById(id);
    }

    @Override
    public Optional<Client> findByCpf(String cpf) {
        return clientJpaRepository.findByCpf(cpf);
    }

    @Override
    public List<Client> findAll() {
        return clientJpaRepository.findAll();
    }
}
