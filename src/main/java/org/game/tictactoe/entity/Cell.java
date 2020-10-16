package org.game.tictactoe.entity;

/**
 * This enum depicts the particular value present in the playing {@link Board}
 * 
 * @author Sujan Kumar Mitra
 * @since 2020-10-15
 */
public enum Cell {
	/**
	 * symbol for {@link Player#MAX}
	 * 
	 * @see Player
	 */
	X,
	/**
	 * symbol for {@link Player#MIN}
	 * 
	 * @see Player
	 */
	O,
	/**
	 * depicts cell is empty
	 */
	EMPTY;
}
