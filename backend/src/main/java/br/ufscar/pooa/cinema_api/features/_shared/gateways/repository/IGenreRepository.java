package br.ufscar.pooa.cinema_api.features._shared.gateways.repository;

import br.ufscar.pooa.cinema_api.domain.entities.Genre;

import java.util.Optional;

public interface IGenreRepository {
    void saveAll(Iterable<Genre> genres);

    Genre save(Genre genre);

    Optional<Genre> findById(Long id);

    void delete(Long id);
}
