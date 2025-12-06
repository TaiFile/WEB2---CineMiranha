package br.ufscar.pooa.cinema_api.features.theaters;

import br.ufscar.pooa.cinema_api.domain.entities.Address;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IAddressMapper {
    Address toAddress(AddressDTO addressDTO);
}

