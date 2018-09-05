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
    @Query("select c.id, r.amount, r.full_amount,r.photo_path,p.login from contest c " +
            "left join contest_player player on c.id = player.contest_id " +
            "join round r on c.id = r.contest_id where c.contest_name=?1")
    ArrayList<Round> findAllByContest(Contest contest);
}
