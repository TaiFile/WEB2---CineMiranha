package br.ufscar.pooa.cinema_api.application.ports.out.repository;

import br.ufscar.pooa.cinema_api.domain.Genre;
import br.ufscar.pooa.cinema_api.domain.enums.Gender;

import java.util.Optional;

public interface IGenreRepository {
    void saveAll(Iterable<Genre> genres);

    Genre save(Genre genre);

    Optional<Genre> findById(Long id);

    void delete(Long id);
}
