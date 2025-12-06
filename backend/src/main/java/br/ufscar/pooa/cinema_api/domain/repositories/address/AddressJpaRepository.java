package br.ufscar.pooa.cinema_api.domain.repositories.address;

import br.ufscar.pooa.cinema_api.domain.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressJpaRepository extends JpaRepository<Address, Long> {
}
