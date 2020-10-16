package org.game.tictactoe.service;

import org.game.tictactoe.entity.Coordinate;
import org.game.tictactoe.entity.Outcome;

/**
 * Implementation of {@link Result}
 * 
 * @author Sujan Kumar Mitra
 * @since 2020-10-16
 */
public class ResultImpl implements Result {

	/**
	 * the outcome
	 */
	final Outcome outcome;
	/**
	 * the next best position to make a move
	 */
	final Coordinate nextBestPosition;

	public ResultImpl(Outcome outcome, Coordinate nextBestPosition) {
		this.outcome = outcome;
		this.nextBestPosition = nextBestPosition;
	}

	@Override
	public Outcome getOutcome() {
		return outcome;
	}

	@Override
	public Coordinate getNextBestPosition() {
		return nextBestPosition;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ResultImpl [outcome=");
		builder.append(outcome);
		builder.append(", nextBestPosition=");
		builder.append(nextBestPosition);
		builder.append("]");
		return builder.toString();
	}

}
