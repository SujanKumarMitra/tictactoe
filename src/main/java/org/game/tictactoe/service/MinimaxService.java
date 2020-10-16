package org.game.tictactoe.service;

import org.game.tictactoe.entity.Board;
import org.game.tictactoe.entity.Coordinate;
import org.game.tictactoe.entity.Player;

/**
 * @author Sujan Kumar Mitra
 * @since 2020-10-15
 */
public interface MinimaxService {
	Coordinate getNextBestMove(Board board);
	
	Coordinate getNextBestMove(Board board, Player player);
}
