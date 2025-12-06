package br.ufscar.pooa.cinema_api.domain.repositories.ticket;

import br.ufscar.pooa.cinema_api.features._shared.gateways.repository.ITicketRepository;
import br.ufscar.pooa.cinema_api.domain.entities.Ticket;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class TicketRepositoryAdapter implements ITicketRepository {

    private final TicketJpaRepository ticketJpaRepository;

    public TicketRepositoryAdapter(TicketJpaRepository ticketJpaRepository) {
        this.ticketJpaRepository = ticketJpaRepository;
    }

    @Override
    public Ticket save(Ticket ticket) {
        if (ticket == null) {
            throw new IllegalArgumentException("Ticket cannot be null");
        }
        if (ticket.getId() != null) {
            throw new IllegalArgumentException("Ticket ID must be null");
        }

        return ticketJpaRepository.save(ticket);
    }

    @Override
    public Optional<Ticket> findById(Long id) {
        return ticketJpaRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID cannot be null");
        }
        ticketJpaRepository.deleteById(id);
    }
}
