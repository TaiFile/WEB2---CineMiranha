package br.ufscar.pooa.cinema_api.adapters.out.database.repositories.genre;

import br.ufscar.pooa.cinema_api.application.ports.out.repository.IGenreRepository;
import br.ufscar.pooa.cinema_api.domain.Genre;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public class GenreRepositoryAdapter implements IGenreRepository {

    private final GenreJpaRepository genreJpaRepository;

    public GenreRepositoryAdapter(GenreJpaRepository genreJpaRepository) {
        this.genreJpaRepository = genreJpaRepository;
    }

    @Override
    public void saveAll(Iterable<Genre> genres) {
        genreJpaRepository.saveAll(genres);
    }

    @Override
    public Genre save(Genre genre) {
        return genreJpaRepository.save(genre);
    }

    @Override
    public Optional<Genre> findById(Long id) {
        return genreJpaRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        genreJpaRepository.deleteById(id);
    }
}