package br.ufscar.pooa.cinema_api.domain.repositories.manager;

import br.ufscar.pooa.cinema_api.features._shared.gateways.repository.IManagerRepository;
import br.ufscar.pooa.cinema_api.domain.entities.Manager;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class ManagerRepositoryAdapter implements IManagerRepository {
    private final ManagerJpaRepository managerJpaRepository;

    public ManagerRepositoryAdapter(ManagerJpaRepository managerJpaRepository) {
        this.managerJpaRepository = managerJpaRepository;
    }

    @Override
    public Manager save(Manager manager) {
        return managerJpaRepository.save(manager);
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
        return managerJpaRepository.findById(id);
    }

    @Override
    public Optional<Manager> findByCpf(String cpf) {
        return managerJpaRepository.findByCpf(cpf);
    }
}
