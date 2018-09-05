package tim.wat.darts.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tim.wat.darts.source.Contest;
import tim.wat.darts.source.Player;

import java.util.ArrayList;


@Repository
public interface PlayerRepository extends CrudRepository<Player, Long> {
    Player findByLogin(String login);
    ArrayList<Player> findAllByContests(Contest contest);
}
