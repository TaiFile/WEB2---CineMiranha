package br.ufscar.pooa.cinema_api.adapters.out.persistence.repositories.jpa.ticket;

import br.ufscar.pooa.cinema_api.adapters.out.persistence.entities.TicketEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketJpaRepository extends JpaRepository<TicketEntity, Long> {
}