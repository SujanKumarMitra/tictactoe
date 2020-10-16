package org.game.tictactoe.service;

import org.game.tictactoe.entity.Coordinate;
import org.game.tictactoe.entity.Outcome;

/**
 * This entity depicts the outcome of the Minimax algorithm. <br>
 * Contains information about possible outcome and next bext move
 * 
 * @author Sujan Kumar Mitra
 * @since 2020-10-16
 */
public interface Result {

	/**
	 * Returns the possible outcome, if {@link #getNextBestPosition()} is played
	 * 
	 * @return the outcome
	 */
	Outcome getOutcome();

	/**
	 * The coordinates of the next best position to play
	 * 
	 * @return the coordinates
	 */
	Coordinate getNextBestPosition();
}
