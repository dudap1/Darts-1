package tim.wat.darts.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tim.wat.darts.objects.RoundObject;
import tim.wat.darts.repositories.ContestRepository;
import tim.wat.darts.repositories.PlayerRepository;
import tim.wat.darts.repositories.RoundRepository;
import tim.wat.darts.source.Round;

@RestController
public class SetRound {
    @Autowired
    RoundRepository roundRepository;
    @Autowired
    ContestRepository contestRepository;
    @Autowired
    PlayerRepository playerRepository;
    @RequestMapping(value = "/setRound", method = RequestMethod.POST)
    @ResponseBody
    public RoundObject setRount(@RequestParam(value = "amount", defaultValue = "0") int amount,
                                @RequestParam(value = "fullAmount", defaultValue = "0") int fullAmount,
                                @RequestParam(value = "photoPath", defaultValue = "") String photoPath,
                                @RequestParam(value = "contest", defaultValue = "") long contest,
                                @RequestParam(value = "player", defaultValue = "") long player) {
        Round round= new Round(amount,fullAmount,photoPath);
        round.setContest(contestRepository.findById(contest).get());
        round.setPlayer(playerRepository.findById(player).get());
        roundRepository.save(round);
        return new RoundObject(amount,fullAmount,photoPath);
    }
}
