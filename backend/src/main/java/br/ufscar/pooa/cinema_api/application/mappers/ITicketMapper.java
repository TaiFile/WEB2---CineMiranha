package br.ufscar.pooa.cinema_api.application.mappers;

import br.ufscar.pooa.cinema_api.application.dtos.ticket.TicketResponseDTO;
import br.ufscar.pooa.cinema_api.domain.Ticket;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {ISessionMapper.class, IClientMapper.class, ISeatMapper.class})
public interface ITicketMapper {

    @Mapping(source = "session.id", target = "sessionId")
    @Mapping(source = "client.id", target = "clientId")
    @Mapping(source = "seat.id", target = "seatId")
    TicketResponseDTO toTicketResponseDTO(Ticket ticket);
}
