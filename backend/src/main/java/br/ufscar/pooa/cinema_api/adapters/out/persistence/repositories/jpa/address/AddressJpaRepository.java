package br.ufscar.pooa.cinema_api.adapters.out.persistence.repositories.jpa.address;

import br.ufscar.pooa.cinema_api.adapters.out.persistence.entities.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressJpaRepository extends JpaRepository<AddressEntity, Long> {
}
