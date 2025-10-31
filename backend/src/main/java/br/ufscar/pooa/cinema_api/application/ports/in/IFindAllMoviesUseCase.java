package br.ufscar.pooa.cinema_api.application.ports.in;

import br.ufscar.pooa.cinema_api.domain.Movie;

import java.util.List;

public interface IFindAllMoviesUseCase {
    List<Movie> execute();
}
