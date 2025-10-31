package br.ufscar.pooa.cinema_api.adapters.out.persistence.repositories.jpa.manager;

import br.ufscar.pooa.cinema_api.adapters.out.mapper.modelmapper.ObjectMapper;
import br.ufscar.pooa.cinema_api.adapters.out.persistence.entities.ManagerEntity;
import br.ufscar.pooa.cinema_api.application.ports.out.mapper.IObjectMapper;
import br.ufscar.pooa.cinema_api.application.ports.out.repository.IManagerRepository;
import br.ufscar.pooa.cinema_api.domain.Manager;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class ManagerRepositoryAdapter implements IManagerRepository {
    private final ManagerJpaRepository managerJpaRepository;
    private final IObjectMapper objectMapper;

    public ManagerRepositoryAdapter(ManagerJpaRepository managerJpaRepository, ObjectMapper objectMapper) {
        this.managerJpaRepository = managerJpaRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    public Manager save(Manager manager) {
        ManagerEntity entityToSave = objectMapper.parseObject(manager, ManagerEntity.class);
        ManagerEntity savedEntity = managerJpaRepository.save(entityToSave);
        return objectMapper.parseObject(savedEntity, Manager.class);
    }

    @Override
    public void delete(Manager manager) {
        if (manager != null && manager.getId() != null) {
            managerJpaRepository.deleteById(manager.getId());
        } else {
            throw new IllegalArgumentException("Manager or Manager ID cannot be null");
        }
    }

    @Override
    public Optional<Manager> findById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID cannot be null");
        }
        return managerJpaRepository.findById(id)
                .map(entity -> objectMapper.parseObject(entity, Manager.class));
    }

    @Override
    public Optional<Manager> findByCpf(String cpf) {
        return managerJpaRepository.findByCpf(cpf)
                .map(entity -> objectMapper.parseObject(entity, Manager.class));
    }
}
