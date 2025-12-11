package br.ufscar.pooa.cinema_api.features.movies.usecase;

import br.ufscar.pooa.cinema_api.domain.entities.Movie;
import br.ufscar.pooa.cinema_api.domain.repositories.movie.IMovieRepository;
import br.ufscar.pooa.cinema_api.features.movies.dto.MovieResponseDTO;
import br.ufscar.pooa.cinema_api.features.movies.mapper.IMovieMapper;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FindAllMoviesUseCase {

    private final IMovieRepository movieRepository;
    private final IMovieMapper movieMapper;

    public FindAllMoviesUseCase(IMovieRepository movieRepository, IMovieMapper movieMapper) {
        this.movieRepository = movieRepository;
        this.movieMapper = movieMapper;
    }

    @Transactional(readOnly = true)
    public List<MovieResponseDTO> execute() {
        List<Movie> movies = movieRepository.findAll();
        return movies.stream()
                .map(movieMapper::toMovieResponseDTO)
                .collect(Collectors.toList());
    }
}

