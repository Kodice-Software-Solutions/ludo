package dev.kodice.games.ludo.service;

import java.util.List;
import java.util.Optional;

import dev.kodice.games.ludo.domain.model.Game;
import dev.kodice.games.ludo.domain.model.Player;

public interface GameService {

	Game newGame(Game game);

	Game reset(Game game);

	Optional<Game> getGameById(Long id);

	Game save(Game game);

	Game update(Game game);

	boolean isKeyFromGame(List<Player> players, String key);

	int getPlayerToRoll(List<Player> players);
}
