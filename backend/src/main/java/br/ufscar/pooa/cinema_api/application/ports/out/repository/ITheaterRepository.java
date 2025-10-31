package br.ufscar.pooa.cinema_api.application.ports.out.repository;

import br.ufscar.pooa.cinema_api.domain.Theater;

import java.util.Optional;

public interface ITheaterRepository {
    Theater save(Theater theater);

    Optional<Theater> findById(Long id);

    Optional<Theater> findByName(String name);

    void deleteById(Long id);

    Long count();
}
