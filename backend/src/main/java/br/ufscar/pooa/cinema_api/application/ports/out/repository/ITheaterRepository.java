package br.ufscar.pooa.cinema_api.application.ports.out.repository;

import br.ufscar.pooa.cinema_api.domain.Theater;

import java.util.List;
import java.util.Optional;

public interface ITheaterRepository {
    Theater save(Theater theater);

    List<Theater> findAll();

    Optional<Theater> findById(Long id);

    Optional<Theater> findByName(String name);

    void deleteById(Long id);

    Long count();
}
