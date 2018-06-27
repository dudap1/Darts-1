package tim.wat.darts.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tim.wat.darts.objects.ContestObject;
import tim.wat.darts.source.Contest;
import tim.wat.darts.source.Player;

import java.util.List;


@Repository
public interface ContestRepository extends CrudRepository<Contest, Long> {
    Contest findByContestNameAndContestPass(String contestName, String contestPass);



    List<ContestObject> findAllByPlayers(Player player);
}
