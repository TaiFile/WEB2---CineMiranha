package br.ufscar.pooa.cinema_api.features.rooms.usecase;

import br.ufscar.pooa.cinema_api.domain.entities.Address;
import br.ufscar.pooa.cinema_api.features.theaters.dto.AddressDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IAddressMapper {

    Address toAddress(AddressDTO addressDTO);
}

