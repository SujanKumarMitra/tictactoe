package org.game.tictactoe.entity;

import org.game.tictactoe.service.MinimaxService;

/**
 * Depicts the outcome of the game
 * 
 * @author Sujan Kumar Mitra
 * @since 2020-10-15
 * @see MinimaxService
 */
public enum Outcome {
	/**
	 * depicts player has won
	 */
	WIN,
	/**
	 * depicts player has lost
	 */
	LOSE,
	/**
	 * depicts the game ended in a draw
	 * 
	 */
	DRAW;
}
