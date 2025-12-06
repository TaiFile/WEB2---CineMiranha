package br.ufscar.pooa.cinema_api.features._shared.gateways.repository;

import br.ufscar.pooa.cinema_api.domain.entities.Ticket;

import java.util.Optional;

public interface ITicketRepository {
    Ticket save(Ticket ticket);
    Optional<Ticket> findById(Long id);
    void delete(Long id);
}
