package br.ufscar.pooa.cinema_api.adapters.out.database.repositories.address;

import br.ufscar.pooa.cinema_api.domain.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressJpaRepository extends JpaRepository<Address, Long> {
}
