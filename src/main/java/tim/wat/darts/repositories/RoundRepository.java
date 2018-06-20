package tim.wat.darts.repositories;


import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;
import tim.wat.darts.source.Round;

@Repository
public interface RoundRepository extends CrudRepository<Round, Long> {
}
