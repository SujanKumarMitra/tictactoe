package org.game.tictactoe.service;

import org.game.tictactoe.entity.Board;
import org.game.tictactoe.entity.Player;

/**
 * This service is responsible to predict the next best move for a
 * {@link Player} ,<br>
 * depending on the state of the {@link Board}
 * 
 * @author Sujan Kumar Mitra
 * @since 2020-10-15
 * @see Result
 */
public interface MinimaxService {
	/**
	 * Returns the result of next move and outcome for the board.<br>
	 * Assumes the current player is {@link Player#MAX}
	 * 
	 * @param board the board state
	 * @return the result
	 */
	Result getNextBestMove(Board board);

	/**
	 * Returns the result of next move and outcome for the board.<br>
	 * 
	 * @param board  the board state
	 * @param player the player current making a move
	 * @return the result
	 */
	Result getNextBestMove(Board board, Player player);
}
