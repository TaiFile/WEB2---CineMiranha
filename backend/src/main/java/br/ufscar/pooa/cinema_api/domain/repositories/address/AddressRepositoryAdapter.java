package br.ufscar.pooa.cinema_api.domain.repositories.address;

import br.ufscar.pooa.cinema_api.features._shared.gateways.repository.IAddressRepository;
import br.ufscar.pooa.cinema_api.domain.entities.Address;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class AddressRepositoryAdapter implements IAddressRepository {
    private final AddressJpaRepository addressJpaRepository;

    public AddressRepositoryAdapter(AddressJpaRepository addressJpaRepository) {
        this.addressJpaRepository = addressJpaRepository;
    }

    @Override
    public Address save(Address address) {
        return addressJpaRepository.save(address);
    }

    @Override
    public Optional<Address> findById(Long id) {
        return addressJpaRepository.findById(id);
    }

    @Override
    public Optional<Address> findByStreetAndNumber(String street, String number) {
        // This method is not implemented in the original adapter, so I will leave it as is.
        return Optional.empty();
    }

    @Override
    public void delete(Address address) {
        addressJpaRepository.delete(address);
    }

    @Override
    public List<Address> findAll() {
        return addressJpaRepository.findAll();
    }

}
