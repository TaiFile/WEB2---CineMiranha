package br.ufscar.pooa.cinema_api.adapters.out.persistence.repositories.jpa.genre;

import br.ufscar.pooa.cinema_api.adapters.out.persistence.entities.GenreEntity;
import br.ufscar.pooa.cinema_api.application.ports.out.mapper.IObjectMapper;
import br.ufscar.pooa.cinema_api.application.ports.out.repository.IGenreRepository;
import br.ufscar.pooa.cinema_api.domain.Genre;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class GenreRepositoryAdapter implements IGenreRepository {
    private final IObjectMapper objectMapper;
    private final GenreJpaRepository genreJpaRepository;

    public GenreRepositoryAdapter(IObjectMapper objectMapper, GenreJpaRepository genreJpaRepository) {
        this.objectMapper = objectMapper;
        this.genreJpaRepository = genreJpaRepository;
    }

    @Override
    public Genre save(Genre genre) {
        GenreEntity entityToSave = objectMapper.parseObject(genre, GenreEntity.class);

        GenreEntity savedEntity = genreJpaRepository.save(entityToSave);
        return objectMapper.parseObject(savedEntity, Genre.class);
    }

    @Override
    public Optional<Genre> findById(Long id) {
        Optional<GenreEntity> entityOptional = genreJpaRepository.findById(id);
        return entityOptional.map(entity -> objectMapper.parseObject(entity, Genre.class));
    }

    @Override
    public void delete(Long id) {
        genreJpaRepository.deleteById(id);
    }
}
