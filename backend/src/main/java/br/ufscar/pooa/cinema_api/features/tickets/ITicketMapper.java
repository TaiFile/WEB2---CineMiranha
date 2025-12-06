package br.ufscar.pooa.cinema_api.features.tickets;

import br.ufscar.pooa.cinema_api.features.clients.IClientMapper;
import br.ufscar.pooa.cinema_api.features.tickets.TicketResponseDTO;
import br.ufscar.pooa.cinema_api.domain.entities.Ticket;
import br.ufscar.pooa.cinema_api.features.sessions.ISessionMapper;
import br.ufscar.pooa.cinema_api.features.theaters.ISeatMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {ISessionMapper.class, IClientMapper.class, ISeatMapper.class})
public interface ITicketMapper {

    @Mapping(source = "session.id", target = "sessionId")
    @Mapping(source = "client.id", target = "clientId")
    @Mapping(source = "seat.id", target = "seatId")
    TicketResponseDTO toTicketResponseDTO(Ticket ticket);
}
