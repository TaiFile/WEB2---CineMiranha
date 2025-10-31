package br.ufscar.pooa.cinema_api.adapters.out.persistence.repositories.jpa.ticket;

import br.ufscar.pooa.cinema_api.adapters.out.persistence.entities.TicketEntity;
import br.ufscar.pooa.cinema_api.application.ports.out.mapper.IObjectMapper;
import br.ufscar.pooa.cinema_api.application.ports.out.repository.ITicketRepository;
import br.ufscar.pooa.cinema_api.domain.Ticket;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class TicketRepositoryAdapter implements ITicketRepository {

    private final TicketJpaRepository ticketJpaRepository;
    private final IObjectMapper objectMapper;

    public TicketRepositoryAdapter(TicketJpaRepository ticketJpaRepository, IObjectMapper objectMapper) {
        this.ticketJpaRepository = ticketJpaRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    public Ticket save(Ticket ticket) {
        if (ticket == null) {
            throw new IllegalArgumentException("Ticket cannot be null");
        }
        if (ticket.getId() != null) {
            throw new IllegalArgumentException("Ticket ID must be null");
        }

        TicketEntity ticketEntity = objectMapper.parseObject(ticket, TicketEntity.class);
        TicketEntity savedEntity = ticketJpaRepository.save(ticketEntity);
        return objectMapper.parseObject(savedEntity, Ticket.class);
    }

    @Override
    public Optional<Ticket> findById(Long id) {
        return ticketJpaRepository.findById(id)
                .map(entity -> objectMapper.parseObject(entity, Ticket.class));
    }

    @Override
    public void delete(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID cannot be null");
        }
        ticketJpaRepository.deleteById(id);
    }
}