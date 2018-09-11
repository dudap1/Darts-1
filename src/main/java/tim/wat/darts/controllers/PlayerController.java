package tim.wat.darts.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import tim.wat.darts.objects.PlayerObject;
import tim.wat.darts.repositories.PlayerRepository;
import tim.wat.darts.source.Player;

import java.util.Collections;
import java.util.Map;

@RestController("/api")
public class PlayerController {
    @Autowired
    PlayerRepository playerRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @RequestMapping(value = "/setPlayer", method = RequestMethod.POST)
    @ResponseBody
    public PlayerObject setPlayer(@RequestParam(value = "name", defaultValue = "") String name,
                                  @RequestParam(value = "surname", defaultValue = "") String surname,
                                  @RequestParam(value = "login", defaultValue = "") String login,
                                  @RequestParam(value = "password", defaultValue = "") String password,
                                  @RequestParam(value = "avatarPath", defaultValue = "") String avatarPath) {
        String encodedPassword = passwordEncoder.encode(password);
        Player player = new Player(name, surname, login, encodedPassword, avatarPath);
        playerRepository.save(player);
        return new PlayerObject(player.getId(), player.getName(),player.getSurname(),player.getLogin(),player.getAvatarPath());
    }


    @RequestMapping(value = "/setAvatarImage", method = RequestMethod.POST)
    @ResponseBody
    public PlayerObject setVatharImage(@RequestParam(value = "name", defaultValue = "") String name,
                                  @RequestParam(value = "surname", defaultValue = "") String surname,
                                  @RequestParam(value = "login", defaultValue = "") String login,
                                  @RequestParam(value = "password", defaultValue = "") String password,
                                  @RequestParam(value = "avatarPath", defaultValue = "") String avatarPath) {
        String encodedPassword = passwordEncoder.encode(password);
        Player player = new Player(name, surname, login, encodedPassword, avatarPath);
        playerRepository.save(player);
        return new PlayerObject(player.getId(), player.getName(),player.getSurname(),player.getLogin(),player.getAvatarPath());
    }

    @RequestMapping(value = "/whoAmI", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, String> whoAmI() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!authentication.getName().equals("anonymousUser")) {
            return Collections.singletonMap("login", authentication.getName());
        }
        return null;
    }


}
