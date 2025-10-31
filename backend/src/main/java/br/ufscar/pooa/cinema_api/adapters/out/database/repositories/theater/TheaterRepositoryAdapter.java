package br.ufscar.pooa.cinema_api.adapters.out.database.repositories.theater;

import br.ufscar.pooa.cinema_api.application.ports.out.repository.ITheaterRepository;
import br.ufscar.pooa.cinema_api.domain.Manager;
import br.ufscar.pooa.cinema_api.domain.Theater;
import br.ufscar.pooa.cinema_api.adapters.out.database.repositories.manager.ManagerJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class TheaterRepositoryAdapter implements ITheaterRepository {
    private final TheaterJpaRepository theaterJpaRepository;
    private final ManagerJpaRepository managerJpaRepository;

    public TheaterRepositoryAdapter(TheaterJpaRepository theaterJpaRepository,
                                    ManagerJpaRepository managerJpaRepository) {
        this.theaterJpaRepository = theaterJpaRepository;
        this.managerJpaRepository = managerJpaRepository;
    }

    @Override
    public Theater save(Theater theater) {
        if (theater == null) {
            throw new IllegalArgumentException("Theater cannot be null");
        }
        if (theater.getId() != null) {
            throw new IllegalArgumentException("Theater ID must be null for a new theater");
        }

        // Re-hydrate the managers to ensure they are managed entities
        if (theater.getManagers() != null && !theater.getManagers().isEmpty()) {
            List<Long> managerIds = theater.getManagers().stream()
                    .map(Manager::getId)
                    .toList();
            List<Manager> managedManagers = new ArrayList<>(managerJpaRepository.findAllById(managerIds));
            theater.setManagers(managedManagers);
        }

        return theaterJpaRepository.save(theater);
    }

    @Override
    public Optional<Theater> findById(Long id) {
        return theaterJpaRepository.findById(id);
    }

    @Override
    public Optional<Theater> findByName(String name) {
        return theaterJpaRepository.findByName(name);
    }

    @Override
    public void deleteById(Long id) {
        Theater entity = theaterJpaRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Theater not found"));
        theaterJpaRepository.delete(entity);
    }

    @Override
    public Long count() {
        return theaterJpaRepository.count();
    }
}
