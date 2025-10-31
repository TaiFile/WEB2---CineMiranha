package br.ufscar.pooa.cinema_api.adapters.out.mapper.mapstruct;

import br.ufscar.pooa.cinema_api.adapters.out.persistence.entities.TicketEntity;
import br.ufscar.pooa.cinema_api.domain.Ticket;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring",
    uses = {ClientMapper.class})
public interface TicketMapper {

    @Mapping(target = "session", ignore = true)
    @Mapping(target = "seat", ignore = true)
    Ticket toDomain(TicketEntity entity);
}