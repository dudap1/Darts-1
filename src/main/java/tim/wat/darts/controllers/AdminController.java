package tim.wat.darts.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import tim.wat.darts.objects.RemoveObject;
import tim.wat.darts.repositories.PlayerRepository;
import tim.wat.darts.source.Player;

@RestController
public class AdminController {

    @Autowired
    PlayerRepository playerRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    @RequestMapping(value = "/admin/deletePlayer", method = RequestMethod.POST)
    @ResponseBody
    public RemoveObject deleteRound (@RequestParam(value = "id", defaultValue = "") long id){
        Player player = playerRepository.findById(id).get();
        player.setDeleted(true);
        playerRepository.save(player);
        return new RemoveObject("Player "+id+" deleted");
    }
}
