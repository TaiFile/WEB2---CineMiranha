package br.ufscar.pooa.cinema_api.adapters.out.persistence.repositories.jpa.address;

import br.ufscar.pooa.cinema_api.adapters.out.persistence.entities.AddressEntity;
import br.ufscar.pooa.cinema_api.application.ports.out.mapper.IObjectMapper;
import br.ufscar.pooa.cinema_api.application.ports.out.repository.IAddressRepository;
import br.ufscar.pooa.cinema_api.domain.Address;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class AddressRepositoryAdapter implements IAddressRepository {
    private final AddressJpaRepository addressJpaRepository;
    private final IObjectMapper objectMapper;

    public AddressRepositoryAdapter(AddressJpaRepository addressJpaRepository, IObjectMapper objectMapper) {
        this.addressJpaRepository = addressJpaRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    public Address save(Address address) {
        AddressEntity entity = objectMapper.parseObject(address, AddressEntity.class);
        return objectMapper.parseObject(addressJpaRepository.save(entity), Address.class);
    }

    @Override
    public Optional<Address> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<Address> findByStreetAndNumber(String street, String number) {
        return Optional.empty();
    }

    @Override
    public void delete(Address address) {

    }

    @Override
    public List<Address> findAll() {
        return List.of();
    }

}
