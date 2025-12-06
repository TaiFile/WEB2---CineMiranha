package br.ufscar.pooa.cinema_api.features.movies;

import br.ufscar.pooa.cinema_api.features.movies.MovieResponseDTO;
import br.ufscar.pooa.cinema_api.features.movies.RegisterMovieRequestDTO;
import br.ufscar.pooa.cinema_api.domain.entities.Movie;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {IGenreMapper.class})
public interface IMovieMapper {
    MovieResponseDTO toMovieResponseDTO(Movie movie);
    Movie toMovie(RegisterMovieRequestDTO registerMovieRequestDTO);
}
