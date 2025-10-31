package br.ufscar.pooa.cinema_api.adapters.out.persistence.repositories.jpa.client;

import br.ufscar.pooa.cinema_api.adapters.out.mapper.modelmapper.ObjectMapper;
import br.ufscar.pooa.cinema_api.adapters.out.persistence.entities.ClientEntity;
import br.ufscar.pooa.cinema_api.application.ports.out.mapper.IObjectMapper;
import br.ufscar.pooa.cinema_api.application.ports.out.repository.IClientRepository;
import br.ufscar.pooa.cinema_api.domain.Client;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class ClientRepositoryAdapter implements IClientRepository {
    private final ClientJpaRepository clientJpaRepository;
    private final IObjectMapper objectMapper;

    public ClientRepositoryAdapter(ClientJpaRepository clientJpaRepository, ObjectMapper objectMapper) {
        this.clientJpaRepository = clientJpaRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    public Client save(Client client) {
        ClientEntity entityToSave = objectMapper.parseObject(client, ClientEntity.class);
        ClientEntity savedEntity = clientJpaRepository.save(entityToSave);
        return objectMapper.parseObject(savedEntity, Client.class);
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
        return clientJpaRepository.findById(id)
                .map(entity -> objectMapper.parseObject(entity, Client.class));
    }

    @Override
    public Optional<Client> findByCpf(String cpf) {
        return clientJpaRepository.findByCpf(cpf)
                .map(entity -> objectMapper.parseObject(entity, Client.class));
    }

    @Override
    public List<Client> findAll() {
        return clientJpaRepository.findAll().stream()
                .map(entity -> objectMapper.parseObject(entity, Client.class))
                .collect(Collectors.toList());
    }
}