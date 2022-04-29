package sk.tuke.kpi.kp.game.server.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping
public class MainPageController {

  private String nickname;

  @GetMapping
  public String mainPage(Model model) {
    model.addAttribute("nickname", nickname);
    return "index";
  }

  @PostMapping("/nickname")
  public String login(@ModelAttribute("nickname") String nickname) {
    this.nickname = nickname;
    return "redirect:/game";
  }

  public String getNickname() {
    return nickname;
  }
}
