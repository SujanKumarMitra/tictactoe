package org.game.tictactoe.entity;

/**
 * @author Sujan Kumar Mitra
 * @since 2020-10-16
 */
public class CoordinateImpl implements Coordinate {

	final int row;
	final int column;

	public CoordinateImpl(int row, int column) {
		this.row = row;
		this.column = column;
	}

	@Override
	public int getRowCoordinate() {
		return row;
	}

	@Override
	public int getColumnCoordinate() {
		return column;
	}

	@Override
	public String toString() {
		return String.format("{row=%d, col=%d}", row,column);
	}
	
	

}
