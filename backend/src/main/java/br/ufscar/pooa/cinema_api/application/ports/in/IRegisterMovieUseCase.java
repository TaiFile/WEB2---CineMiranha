package br.ufscar.pooa.cinema_api.application.ports.in;

import br.ufscar.pooa.cinema_api.application.dtos.movie.RegisterMovieRequestDTO;
import br.ufscar.pooa.cinema_api.application.dtos.movie.MovieResponseDTO;

public interface IRegisterMovieUseCase {
    MovieResponseDTO execute(RegisterMovieRequestDTO requestDTO);
}
