package br.ufscar.pooa.cinema_api.application.mappers;

import br.ufscar.pooa.cinema_api.application.dtos.theater.AddressDTO;
import br.ufscar.pooa.cinema_api.domain.Address;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IAddressMapper {
    AddressDTO toAddressDTO(Address address);
    Address toAddress(AddressDTO addressDTO);
}
