package mobots.mbot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import mobots.mbot.model.GameOrder;
import mobots.mbot.model.GameState;
import mobots.mbot.service.GameService;

@RestController
public class GameController {

	@Autowired
	GameService gameService;
	
	@PostMapping("/bot")
	public GameOrder makeOrder(@RequestBody GameState gameState) {
		return gameService.getOrder(gameState);
	}
	
}
