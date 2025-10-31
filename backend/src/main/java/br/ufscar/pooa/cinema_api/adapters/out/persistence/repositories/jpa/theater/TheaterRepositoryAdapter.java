package br.ufscar.pooa.cinema_api.adapters.out.persistence.repositories.jpa.theater;

import br.ufscar.pooa.cinema_api.adapters.out.persistence.entities.ManagerEntity;
import br.ufscar.pooa.cinema_api.adapters.out.persistence.entities.TheaterEntity;
import br.ufscar.pooa.cinema_api.adapters.out.persistence.repositories.jpa.manager.ManagerJpaRepository;
import br.ufscar.pooa.cinema_api.application.ports.out.mapper.IObjectMapper;
import br.ufscar.pooa.cinema_api.application.ports.out.repository.ITheaterRepository;
import br.ufscar.pooa.cinema_api.domain.Theater;
import br.ufscar.pooa.cinema_api.domain.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class TheaterRepositoryAdapter implements ITheaterRepository {
    private final TheaterJpaRepository theaterJpaRepository;
    private final ManagerJpaRepository managerJpaRepository;
    private final IObjectMapper objectMapper;

    public TheaterRepositoryAdapter(TheaterJpaRepository theaterJpaRepository,
                                    ManagerJpaRepository managerJpaRepository,
                                    IObjectMapper objectMapper) {
        this.theaterJpaRepository = theaterJpaRepository;
        this.managerJpaRepository = managerJpaRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    public Theater save(Theater theater) {
        if (theater == null) {
            throw new IllegalArgumentException("Theater cannot be null");
        }
        if (theater.getId() != null) {
            throw new IllegalArgumentException("Theater ID must be null for a new theater");
        }

        TheaterEntity theaterEntity = objectMapper.parseObject(theater, TheaterEntity.class);

        // Re-hydrate the managers to ensure they are managed entities
        if (theater.getManagers() != null && !theater.getManagers().isEmpty()) {
            List<Long> managerIds = theater.getManagers().stream()
                    .map(User::getId)
                    .toList();
            List<ManagerEntity> managedManagers = new ArrayList<>(managerJpaRepository.findAllById(managerIds));
            theaterEntity.setManagers(managedManagers);
        }

        TheaterEntity savedEntity = theaterJpaRepository.save(theaterEntity);

        return objectMapper.parseObject(savedEntity, Theater.class);
    }

    @Override
    public Optional<Theater> findById(Long id) {
        Optional<TheaterEntity> entityOptional = theaterJpaRepository.findById(id);
        return entityOptional.map(entity -> objectMapper.parseObject(entity, Theater.class));
    }

    @Override
    public Optional<Theater> findByName(String name) {
        Optional<TheaterEntity> entityOptional = theaterJpaRepository.findByName(name);
        return entityOptional.map(entity -> objectMapper.parseObject(entity, Theater.class));
    }

    @Override
    public void deleteById(Long id) {
        TheaterEntity entity = theaterJpaRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Theater not found"));
        theaterJpaRepository.delete(entity);
    }

    @Override
    public Long count() {
        return theaterJpaRepository.count();
    }
}