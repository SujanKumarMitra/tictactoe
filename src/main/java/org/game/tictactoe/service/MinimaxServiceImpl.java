package org.game.tictactoe.service;

import static org.game.tictactoe.entity.Cell.EMPTY;
import static org.game.tictactoe.entity.Cell.O;
import static org.game.tictactoe.entity.Cell.X;
import static org.game.tictactoe.entity.Outcome.DRAW;
import static org.game.tictactoe.entity.Outcome.LOSE;
import static org.game.tictactoe.entity.Outcome.WIN;
import static org.game.tictactoe.entity.Player.MAX;
import static org.game.tictactoe.entity.Player.MIN;

import java.util.Comparator;
import java.util.Set;
import java.util.function.Function;

import org.game.tictactoe.entity.Board;
import org.game.tictactoe.entity.Cell;
import org.game.tictactoe.entity.Coordinate;
import org.game.tictactoe.entity.Outcome;
import org.game.tictactoe.entity.Player;

/**
 * Implementation of {@link MinimaxService}
 * 
 * @author Sujan Kumar Mitra
 * @since 2020-10-16
 */
public class MinimaxServiceImpl implements MinimaxService {

	/**
	 * utility to calculate initial score based on current player
	 */
	Function<Player, Integer> getInitialScore;

	/**
	 * comparison logic or scores based on current player
	 */
	Function<Player, Comparator<Integer>> getComparator;

	/**
	 * utility to get appropriate move for current player
	 */
	Function<Player, Cell> getMoveForPlayer;

	/**
	 * utility to get opposite player of current player
	 */
	Function<Player, Player> getOppositePlayer;

	public MinimaxServiceImpl() {
		initLambdas();
	}

	/**
	 * initializes the lambdas
	 */
	private void initLambdas() {
		// @formatter:off
		getInitialScore = player -> (player == MAX) ? 
									Integer.MIN_VALUE : 
									Integer.MAX_VALUE;
		
		getComparator = player -> {
			Comparator<Integer> c = Integer::compare;
			if(player == MIN)
				c = c.reversed();
			return c;
		};
		
		getMoveForPlayer = player -> {
			switch (player) {
			case MAX:
				return X;
			case MIN:
				return O;
			default:
				return EMPTY;
			}
		};
		
		getOppositePlayer = player -> player == MIN ? MAX : MIN;
		// @formatter:on
	}

	@Override
	public Result getNextBestMove(Board board) {
		return getNextBestMove(board, MAX);
	}

	@Override
	public Result getNextBestMove(Board board, Player player) {
		ResultSet nextMove = findMove(board, player);
		return new ResultImpl(nextMove.outcome, nextMove.coordinate);
	}

	/**
	 * Finds an optimal move based on the {@link Player} currently making a move and
	 * the state of the {@link Board}.
	 * 
	 * @param board  the board
	 * @param player the player
	 * @return the result
	 * @see ResultSet
	 */
	private ResultSet findMove(Board board, Player player) {
		Outcome outcome = getOutcome(board);
		Set<Coordinate> positions = board.getUnfilledPositions();
		int size = positions.size();
		if (outcome == WIN) {
			return new ResultSet(size + 1, WIN, null);
		} else if (outcome == LOSE) {
			return new ResultSet(-size - 1, LOSE, null);
		} else if (size == 0) {
			return new ResultSet(0, DRAW, null);
		}
		int score = getInitialScore.apply(player);
		Coordinate bestMove = null;
		Outcome finalOutcome = null;
		for (Coordinate position : positions) {
			Cell move = getMoveForPlayer.apply(player);
			board.setCell(move, position);
			ResultSet result = findMove(board, getOppositePlayer.apply(player));

			if (isBetterMove(score, result, player)) {
				bestMove = position;
				finalOutcome = result.outcome;
				score = result.score;
			}
			board.setCell(Cell.EMPTY, position);
		}
		return new ResultSet(score, finalOutcome, bestMove);
	}

	/**
	 * Checks whether the new move is better that previously chosen move
	 * 
	 * @param score  the current score
	 * @param result the previous result
	 * @param player the player making the move
	 * @return if better then {@code true}, else {@code false}
	 */
	private boolean isBetterMove(Integer score, ResultSet result, Player player) {
		Comparator<Integer> comparator = getComparator.apply(player);
		if (comparator.compare(result.score, score) >= 0)
			return true;
		else
			return false;
	}

	/**
	 * Returns the outcome of the current state of the {@link Board}
	 * 
	 * @param board the board
	 * @return the outcome
	 */
	private Outcome getOutcome(Board board) {
		Cell[][] cells = board.getBoard();
		Coordinate bound = board.getBounds();
		int rows = bound.getRowCoordinate();
		int cols = bound.getColumnCoordinate();
		Cell currentMove;
		int i, j;

		// check each column
		for (i = 0; i < rows; i++) {
			currentMove = cells[0][i];
			for (j = 1; j < cols; j++)
				if (cells[j][i] != currentMove)
					break;

			if (j == cols)
				if (currentMove == X)
					return WIN;
				else if (currentMove == O)
					return LOSE;
		}

		// check each row
		for (i = 0; i < rows; i++) {
			currentMove = cells[i][0];
			for (j = 1; j < cols; j++)
				if (cells[i][j] != currentMove)
					break;
			if (j == cols)
				if (currentMove == X)
					return WIN;
				else if (currentMove == O)
					return LOSE;
		}

		// check main diagonal
		currentMove = cells[0][0];
		for (i = 1; i < rows; i++)
			if (cells[i][i] != currentMove)
				break;
		if (i == rows)
			if (currentMove == X)
				return WIN;
			else if (currentMove == O)
				return LOSE;

		// check anti diagonal
		currentMove = cells[0][cols - 1];
		for (i = cols - 2; i >= 0; i--)
			if (cells[cols - i - 1][i] != currentMove)
				break;
		if (i == -1)
			if (currentMove == X)
				return WIN;
			else if (currentMove == O)
				return LOSE;
		return DRAW;
	}

	/**
	 * This class holds the result of a particular state
	 * 
	 * @author Sujan Kumar Mitra
	 * @since 2020-10-16
	 */
	private class ResultSet {
		/**
		 * score is calculated based on this formula:: <br>
		 * 
		 * <pre>
		 * score = (availableMoves+1) * 1 (for {@link Player#MAX}) 
		 * score = (availableMoves+1) * -1 (for {@link Player#MIN})
		 * </pre>
		 */
		final int score;
		/**
		 * the outcome of a state
		 */
		final Outcome outcome;
		/**
		 * the coordinate
		 */
		final Coordinate coordinate;

		public ResultSet(int score, Outcome outcome, Coordinate coordinate) {
			this.score = score;
			this.outcome = outcome;
			this.coordinate = coordinate;
		}

		@Override
		public String toString() {
			StringBuilder builder = new StringBuilder();
			builder.append("ResultSet [score=");
			builder.append(score);
			builder.append(", outcome=");
			builder.append(outcome);
			builder.append(", coordinate=");
			builder.append(coordinate);
			builder.append("]");
			return builder.toString();
		}

	}

}
