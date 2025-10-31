package br.ufscar.pooa.cinema_api.adapters.out.mapper.mapstruct;

import br.ufscar.pooa.cinema_api.adapters.out.persistence.entities.AddressEntity;
import br.ufscar.pooa.cinema_api.domain.Address;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AddressMapper {

    Address toDomain(AddressEntity entity);
}
