package br.ufscar.pooa.cinema_api.features.movies.usecase;

import br.ufscar.pooa.cinema_api.domain.entities.Movie;
import br.ufscar.pooa.cinema_api.domain.repositories.movie.IMovieRepository;
import br.ufscar.pooa.cinema_api.features._shared.exceptions.ResourceNotFoundException;
import br.ufscar.pooa.cinema_api.features.movies.dto.MovieResponseDTO;
import br.ufscar.pooa.cinema_api.features.movies.mapper.IMovieMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class FindMovieByIdUseCase {

    private final IMovieRepository movieRepository;
    private final IMovieMapper movieMapper;

    public FindMovieByIdUseCase(IMovieRepository movieRepository, IMovieMapper movieMapper) {
        this.movieRepository = movieRepository;
        this.movieMapper = movieMapper;
    }

    @Transactional(readOnly = true)
    public MovieResponseDTO execute(Long id) {
        Optional<Movie> movie = movieRepository.findById(id);
        Movie foundMovie = movie.orElseThrow(() -> new ResourceNotFoundException("Movie", "id", id.toString()));
        return movieMapper.toMovieResponseDTO(foundMovie);
    }
}

