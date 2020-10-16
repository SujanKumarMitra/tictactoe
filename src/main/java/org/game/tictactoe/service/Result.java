package org.game.tictactoe.service;

import org.game.tictactoe.entity.Coordinate;
import org.game.tictactoe.entity.Outcome;

/**
 * @author Sujan Kumar Mitra
 * @since 2020-10-16
 */
public interface Result {
	
	Outcome getOutcome();
	
	Coordinate getNextBestPosition();
}
