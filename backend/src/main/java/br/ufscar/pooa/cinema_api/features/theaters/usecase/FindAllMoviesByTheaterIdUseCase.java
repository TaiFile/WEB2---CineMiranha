package br.ufscar.pooa.cinema_api.features.theaters.usecase;

import br.ufscar.pooa.cinema_api.domain.entities.Movie;
import br.ufscar.pooa.cinema_api.domain.repositories.movie.IMovieRepository;
import br.ufscar.pooa.cinema_api.features.movies.dto.MovieResponseDTO;
import br.ufscar.pooa.cinema_api.features.movies.mapper.IMovieMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FindAllMoviesByTheaterIdUseCase {

    private final IMovieRepository movieRepository;
    private final IMovieMapper movieMapper;

    public FindAllMoviesByTheaterIdUseCase(IMovieRepository movieRepository, IMovieMapper movieMapper) {
        this.movieRepository = movieRepository;
        this.movieMapper = movieMapper;
    }

    @Transactional(readOnly = true)
    public List<MovieResponseDTO> execute(Long theaterId) {
        List<Movie> movies = movieRepository.findAllByTheaterId(theaterId);
        return movies.stream()
                .map(movieMapper::toMovieResponseDTO)
                .collect(Collectors.toList());
    }
}

