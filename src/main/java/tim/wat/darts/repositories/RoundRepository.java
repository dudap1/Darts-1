package tim.wat.darts.repositories;


import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;
import tim.wat.darts.source.Contest;
import tim.wat.darts.source.Player;
import tim.wat.darts.source.Round;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface RoundRepository extends CrudRepository<Round, Long> {
    ArrayList<Round> findAllByContestAndPlayer(Contest contest, Player player);

    ArrayList<Round> findAllByContest(Contest contest);
}
