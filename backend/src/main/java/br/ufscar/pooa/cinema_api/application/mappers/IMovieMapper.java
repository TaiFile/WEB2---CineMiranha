package br.ufscar.pooa.cinema_api.application.mappers;

import br.ufscar.pooa.cinema_api.application.dtos.movie.MovieResponseDTO;
import br.ufscar.pooa.cinema_api.application.dtos.movie.RegisterMovieRequestDTO;
import br.ufscar.pooa.cinema_api.domain.Movie;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {IGenreMapper.class})
public interface IMovieMapper {
    MovieResponseDTO toMovieResponseDTO(Movie movie);
    Movie toMovie(RegisterMovieRequestDTO registerMovieRequestDTO);
}