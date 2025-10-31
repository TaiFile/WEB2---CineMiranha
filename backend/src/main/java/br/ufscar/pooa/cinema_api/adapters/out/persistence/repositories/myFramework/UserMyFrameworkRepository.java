package br.ufscar.pooa.cinema_api.adapters.out.persistence.repositories.myFramework;

import br.ufscar.pooa.cinema_api.adapters.out.persistence.entities.UserEntity;
import br.ufscar.pooa.cinema_api.application.ports.out.mapper.IObjectMapper;
import br.ufscar.pooa.cinema_api.application.ports.out.repository.IUserRepository;
import br.ufscar.pooa.cinema_api.domain.User;
import framework.FrameworkClass;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Profile("myFramework")
@Repository
public class UserMyFrameworkRepository implements IUserRepository {
    private final FrameworkClass frameworkClass;
    private final IObjectMapper objectMapper;

    public UserMyFrameworkRepository(FrameworkClass frameworkClass, IObjectMapper objectMapper) {
        this.frameworkClass = frameworkClass;
        this.objectMapper = objectMapper;
    }

    @Override
    public User save(User user) {
        UserEntity entity = objectMapper.parseObject(user, UserEntity.class);
        Long originalId = entity.getId();

        frameworkClass.save(entity);
        if (entity.getId() == null && originalId == null) {
            throw new RuntimeException("Failed to save user - no ID was generated");
        }

        user.setId(entity.getId());
        return user;
    }


    @Override
    public Optional<User> findById(Long id) {
        UserEntity entitySearched = new UserEntity();
        entitySearched.setId(id);
        boolean found = frameworkClass.findById(entitySearched);

        if (!found) {
            return Optional.empty();
        }

        return Optional.of(objectMapper.parseObject(entitySearched, User.class));
    }

    @Override
    public Optional<User> findByEmail(String email) {
        UserEntity entitySearched = new UserEntity();
        frameworkClass.find("email", email, entitySearched);

        if (entitySearched.getId() == null) {
            return Optional.empty();
        }

        return Optional.of(objectMapper.parseObject(entitySearched, User.class));
    }

    @Override
    public void delete(User usuario) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
