package sk.tuke.kpi.kp.game.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sk.tuke.kpi.kp.game.entity.player.Player;
import sk.tuke.kpi.kp.game.server.service.player.PlayerService;

import java.util.Optional;

@Controller
@RequestMapping
public class MainPageController {

  @Autowired
  private PlayerService playerService;
  private Player player;

  @GetMapping
  public String mainPage(Model model) {
    model.addAttribute("player");
    return "index";
  }

  @PostMapping("/login")
  public String login(@ModelAttribute("player") Player player) {
    this.player = player;
    if(isLogged()) {
      playerService.addPlayer(player);
    }
    return "redirect:/game";
  }

  public boolean isLogged() {
    return Optional.ofNullable(player).isPresent()
      && player.getName() != null && player.getName().length() > 0
      && player.getPassword() != null && player.getPassword().length() > 0;
  }

  public Player getPlayer() {
    return player;
  }
}
