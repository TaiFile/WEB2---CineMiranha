package br.ufscar.pooa.cinema_api.adapters.out.database.repositories.theater;

import br.ufscar.pooa.cinema_api.domain.Theater;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TheaterJpaRepository extends JpaRepository<Theater, Long> {
    Optional<Theater> findByName(String name);

}