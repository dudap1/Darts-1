package tim.wat.darts.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tim.wat.darts.objects.ContestObject;
import tim.wat.darts.objects.RemoveObject;
import tim.wat.darts.objects.RoundObject;
import tim.wat.darts.repositories.ContestRepository;
import tim.wat.darts.repositories.PlayerRepository;
import tim.wat.darts.repositories.RoundRepository;
import tim.wat.darts.source.Contest;
import tim.wat.darts.source.Player;
import tim.wat.darts.source.Round;

import java.util.ArrayList;
import java.util.List;

@RestController
public class RoundController {
    @Autowired
    RoundRepository roundRepository;
    @Autowired
    ContestRepository contestRepository;
    @Autowired
    PlayerRepository playerRepository;

    @RequestMapping(value = "/setRound", method = RequestMethod.POST)
    @ResponseBody
    public RoundObject setRound(@RequestParam(value = "amount", defaultValue = "0") int amount,
                                @RequestParam(value = "photoPath", defaultValue = "") String photoPath,
                                @RequestParam(value = "contest", defaultValue = "") long contest,
                                @RequestParam(value = "player", defaultValue = "") long player) {
        Round round = new Round(amount, photoPath);
        Contest contest1 = contestRepository.findById(contest).get();
        round.setContest(contest1);
        Player player1 = playerRepository.findById(player).get();
        round.setPlayer(player1);
        List<Round> rounds = roundRepository.findAllByContest(contest1);
        int fullAmount = rounds.stream().mapToInt(Round::getAmount).sum();
        int newFullAmount = fullAmount + amount;
        if (newFullAmount > 501) {
            round.setAmount(0);
            round.setFullAmount(fullAmount);
            roundRepository.save(round);
            return new RoundObject(round.getId(), 0, fullAmount, photoPath);
        } else if (newFullAmount == 501) {
            round.setFullAmount(newFullAmount);
            contest1.setWinner(player1);
            roundRepository.save(round);
            contestRepository.save(contest1);
            return new RoundObject(round.getId(), amount, newFullAmount, photoPath);
        } else {
            round.setFullAmount(newFullAmount);
            roundRepository.save(round);
            return new RoundObject(round.getId(), amount, newFullAmount, photoPath);
        }
    }

    @RequestMapping(value = "/deleteRound", method = RequestMethod.POST)
    @ResponseBody
    public RemoveObject deleteRound(@RequestParam(value = "id", defaultValue = "") long id) {
        Round round = roundRepository.findById(id).get();
        roundRepository.delete(round);
        return new RemoveObject("Round " + id + " deleted");
    }

    @RequestMapping(value = "/updateRound", method = RequestMethod.POST)
    @ResponseBody
    public RoundObject updateRound(@RequestParam(value = "id", defaultValue = "") long id,
                                   @RequestParam(value = "amount", defaultValue = "0") int amount,
                                   @RequestParam(value = "contest", defaultValue = "") long contest,
                                   @RequestParam(value = "player", defaultValue = "") long player) {
        Round round = roundRepository.findById(id).get();
        Contest contest1 = contestRepository.findById(contest).get();
        round.setContest(contest1);
        Player player1 = playerRepository.findById(player).get();
        round.setPlayer(player1);
        List<Round> rounds = roundRepository.findAllByContestAndPlayer(contest1, player1);
        int fullAmount = rounds.stream().mapToInt(Round::getAmount).sum() - round.getAmount();
        int newFullAmount = fullAmount + amount;
        if (newFullAmount > 501) {
            round.setAmount(0);
            round.setFullAmount(fullAmount);
            roundRepository.save(round);
            return new RoundObject(round.getId(), 0, fullAmount, round.getPhotoPath());
        } else if (newFullAmount == 501) {
            round.setFullAmount(newFullAmount);
            contest1.setWinner(player1);
            roundRepository.save(round);
            contestRepository.save(contest1);
            return new RoundObject(round.getId(), amount, newFullAmount, round.getPhotoPath());
        } else {
            round.setFullAmount(newFullAmount);
            round.setAmount(amount);
            roundRepository.save(round);
            return new RoundObject(round.getId(), amount, newFullAmount, round.getPhotoPath());
        }
    }
    @RequestMapping(value = "/getRounds", method = RequestMethod.POST)
    @ResponseBody
    public ArrayList<RoundObject> getContestRounds(@RequestParam(value = "contest_id", defaultValue = "") Long contest_id,
                                                   @RequestParam(value = "login", defaultValue = "") String login
    ) {
        ArrayList<Round> rounds=roundRepository.findAllByContestAndPlayer(contestRepository.findById(contest_id).get(),playerRepository.findByLogin(login));
        ArrayList<RoundObject> roundObjects=new ArrayList<>();
        RoundObject tempRoundObject=new RoundObject();
        fillRoundList(rounds, roundObjects);
        return roundObjects;
    }

    @RequestMapping(value = "/getRoundsByName", method = RequestMethod.POST)
    @ResponseBody
    public ArrayList<RoundObject> getContestRoundsByName(@RequestParam(value = "contestName", defaultValue = "") String contestName,
                                                   @RequestParam(value = "login", defaultValue = "") String login
    ) {
        ArrayList<Round> rounds=roundRepository.findAllByContest(contestRepository.findByContestName(contestName));
        ArrayList<RoundObject> roundObjects=new ArrayList<>();

        fillRoundList(rounds, roundObjects);
        return roundObjects;
    }

    private void fillRoundList(ArrayList<Round> rounds, ArrayList<RoundObject> roundObjects) {
        for(int i =0; i< rounds.size();i++){
            RoundObject tempRoundObject=new RoundObject();
            tempRoundObject.setId(rounds.get(i).getId());
            tempRoundObject.setAmount(rounds.get(i).getAmount());
            tempRoundObject.setFullAmount(rounds.get(i).getFullAmount());
            tempRoundObject.setPhotoPath(rounds.get(i).getPhotoPath());
            tempRoundObject.setLogin(rounds.get(i).getPlayer().getLogin());
            roundObjects.add(tempRoundObject);
        }
    }
}
