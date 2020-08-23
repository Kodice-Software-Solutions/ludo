package dev.kodice.games.ludo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Service;

import dev.kodice.games.ludo.domain.dto.Landing;
import dev.kodice.games.ludo.domain.model.Meeple;
import dev.kodice.games.ludo.domain.model.Player;

@Service
public class TurnExecutor {

	public int rollDice() {
		Random rand = new Random();
		return rand.nextInt(6) + 1;
	}

	public boolean canMeepleMove(Meeple meeple, int dice) {
		if (meeple.getPosition() == 0) {
			if (dice != 6)
				return false;
			else
				return true;
		} else {
			if (57 >= (meeple.getRelativePosition() + dice))
				return true;
			else
				return false;
		}
	}

	public List<Boolean> getLegalMoves(List<Meeple> meeples, int dice) {
		List<Boolean> moving = new ArrayList<Boolean>();
		for (Meeple m : meeples) {
			if (this.canMeepleMove(m, dice)) {
				moving.add(true);
			} else {
				moving.add(false);
			}
		}
		return moving;
	}

	public boolean isCellProtected(int cell) {
		if (cell == 1 || cell == 9 || cell == 14 || cell == 22 || cell == 27 || cell == 35 || cell == 40
				|| cell == 48) {
			return true;
		} else {
			return false;
		}
	}

	public int getNumberOfLegalMoves(List<Boolean> legalMoves) {
		int num = 0;
		for (Boolean b : legalMoves) {
			if (b) {
				num++;
			}
		}
		return num;
	}

	public int getLegalMove(List<Boolean> legalMoves) {
		int num = 1;
		for (Boolean b : legalMoves) {
			if (b) {
				return num;
			}
			num++;
		}
		return num;
	}

	public Player getPlayerInTurn(List<Player> players) {
		for (Player p : players) {
			if (p.getTurn()) {
				return p;
			}
		}
		return null;
	}

	public Landing getLandingCell(Meeple meeple, int dice, int turn) {
		Landing landing = new Landing();
		System.out.println("Player " + turn + " meeple " + meeple + " and dice " + dice);
		switch (turn) {
		case (1):
			if (meeple.getPosition() == 0) {
				landing.setPosition(1);
				landing.setRelativePosition(1);
			} else {
				landing.setRelativePosition(meeple.getRelativePosition() + dice);
				if (landing.getRelativePosition() > 51) {
					landing.setPosition(landing.getRelativePosition() + 1);
				} else
					landing.setPosition(meeple.getPosition() + dice);
			}
			break;
		case (2):
			if (meeple.getPosition() == 0) {
				landing.setPosition(14);
				landing.setRelativePosition(1);
			} else {
				landing.setRelativePosition(meeple.getRelativePosition() + dice);
				if (landing.getRelativePosition() > 51) {
					landing.setPosition(landing.getRelativePosition() + 7);
				} else {
					if (meeple.getPosition() + dice == 52) {
						landing.setPosition(52);
					} else {
						landing.setPosition((meeple.getPosition() + dice) % 52);
					}
				}
			}
			break;
		case (3):
			if (meeple.getPosition() == 0) {
				landing.setPosition(27);
				landing.setRelativePosition(1);
			} else {
				landing.setRelativePosition(meeple.getRelativePosition() + dice);
				if (landing.getRelativePosition() > 51) {
					landing.setPosition(landing.getRelativePosition() + 13);
				} else {
					if (meeple.getPosition() + dice == 52) {
						landing.setPosition(52);
					} else {
						landing.setPosition((meeple.getPosition() + dice) % 52);
					}
				}
			}
			break;
		case (4):
			if (meeple.getPosition() == 0) {
				landing.setPosition(40);
				landing.setRelativePosition(1);
			} else {
				landing.setRelativePosition(meeple.getRelativePosition() + dice);
				if (landing.getRelativePosition() > 51) {
					landing.setPosition(landing.getRelativePosition() + 19);
				} else {
					if (meeple.getPosition() + dice == 52) {
						landing.setPosition(52);
					} else {
						landing.setPosition((meeple.getPosition() + dice) % 52);
					}
				}
			}
			break;
		default:
			break;
		}
		System.out.println(landing);
		return landing;
	}

	public List<Player> passTurn(List<Player> players) {
		int num = players.size();
		int which = 0;
		for (Player p : players) {
			if (p.getTurn()) {
				p.setTurn(false);
				which = num;
			}
			num++;
		}
		num = 0;
		which = (which + 1) % 4;
		for (Player p : players) {
			if (which == num) {
				p.setTurn(true);
				which = num;
			}
			num++;
		}
		return players;
	}

}
