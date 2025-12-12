package br.ufscar.pooa.cinema_api.domain.repositories.session;

import br.ufscar.pooa.cinema_api.domain.entities.Session;
import java.util.List;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ISessionRepository extends JpaRepository<Session, Long> {

    List<Session> findAllByMovieId(Long movieId);

    @Query("""
        SELECT s FROM Session s
        JOIN s.room r
        WHERE s.movie.id = :movieId AND r.theater.id = :theaterId""")
    @EntityGraph(attributePaths = {"movie", "room"})
    List<Session> findAllByMovieIdAndTheaterId(
        @Param("movieId") Long movieId,
        @Param("theaterId") Long theaterId
    );
}
