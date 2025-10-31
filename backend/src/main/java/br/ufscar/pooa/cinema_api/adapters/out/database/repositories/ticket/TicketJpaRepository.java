package br.ufscar.pooa.cinema_api.adapters.out.database.repositories.ticket;

import br.ufscar.pooa.cinema_api.domain.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketJpaRepository extends JpaRepository<Ticket, Long> {
}