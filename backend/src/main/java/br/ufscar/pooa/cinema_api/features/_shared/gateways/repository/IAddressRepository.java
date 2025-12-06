package br.ufscar.pooa.cinema_api.features._shared.gateways.repository;

import br.ufscar.pooa.cinema_api.domain.entities.Address;

import java.util.List;
import java.util.Optional;

public interface IAddressRepository {
    Address save(Address address);
    Optional<Address> findById(Long id);
    Optional<Address> findByStreetAndNumber(String street, String number);
    void delete(Address address);
    List<Address> findAll();
}
