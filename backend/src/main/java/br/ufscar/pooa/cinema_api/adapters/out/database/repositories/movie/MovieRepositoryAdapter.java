package br.ufscar.pooa.cinema_api.adapters.out.database.repositories.movie;

import br.ufscar.pooa.cinema_api.application.ports.out.repository.IMovieRepository;
import br.ufscar.pooa.cinema_api.domain.Genre;
import br.ufscar.pooa.cinema_api.domain.Movie;
import br.ufscar.pooa.cinema_api.adapters.out.database.repositories.genre.GenreJpaRepository;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
public class MovieRepositoryAdapter implements IMovieRepository {
    private final MovieJpaRepository movieJpaRepository;
    private final GenreJpaRepository genreJpaRepository;

    public MovieRepositoryAdapter(MovieJpaRepository movieJpaRepository, GenreJpaRepository genreJpaRepository) {
        this.movieJpaRepository = movieJpaRepository;
        this.genreJpaRepository = genreJpaRepository;
    }

    @Override
    public Movie save(Movie movie) {
        if (movie == null) {
            throw new IllegalArgumentException("Movie cannot be null");
        }
        if (movie.getId() != null) {
            throw new IllegalArgumentException("Movie ID must be null for a new movie");
        }

        // Re-hydrate the genres to ensure they are managed entities
        if (movie.getGenres() != null && !movie.getGenres().isEmpty()) {
            List<Genre> managedGenres = movie.getGenres().stream()
                    .map(genre -> genreJpaRepository.findById(genre.getId())
                            .orElseThrow(() -> new EntityNotFoundException("Genre not found with id: " + genre.getId())))
                    .toList();
            movie.setGenres(managedGenres);
        }

        return movieJpaRepository.save(movie);
    }

    @Override
    public Optional<Movie> findById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID cannot be null");
        }
        return movieJpaRepository.findById(id);
    }

    @Override
    public Optional<Movie> findByTitle(String title) {
        return movieJpaRepository.findByTitle(title);
    }

    @Override
    public void delete(long id) {
        if (id <= 0) {
            throw new IllegalArgumentException("ID must be a positive number");
        }
        movieJpaRepository.deleteById(id);
    }
}