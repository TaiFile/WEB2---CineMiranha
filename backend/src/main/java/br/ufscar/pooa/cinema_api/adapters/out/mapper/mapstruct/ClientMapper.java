package br.ufscar.pooa.cinema_api.adapters.out.mapper.mapstruct;

import br.ufscar.pooa.cinema_api.adapters.out.persistence.entities.ClientEntity;
import br.ufscar.pooa.cinema_api.domain.Client;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface ClientMapper {

    @Mapping(target = "tickets", ignore = true)
    Client toDomain(ClientEntity entity);
}
