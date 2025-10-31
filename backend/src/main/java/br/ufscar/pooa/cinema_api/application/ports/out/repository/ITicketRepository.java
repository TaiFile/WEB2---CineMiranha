package br.ufscar.pooa.cinema_api.application.ports.out.repository;

import br.ufscar.pooa.cinema_api.domain.Ticket;

import java.util.Optional;

public interface ITicketRepository {
    Ticket save(Ticket ticket);
    Optional<Ticket> findById(Long id);
    void delete(Long id);
}
