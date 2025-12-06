package br.ufscar.pooa.cinema_api.domain.repositories.genre;

import br.ufscar.pooa.cinema_api.features._shared.gateways.repository.IGenreRepository;
import br.ufscar.pooa.cinema_api.domain.entities.Genre;
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
