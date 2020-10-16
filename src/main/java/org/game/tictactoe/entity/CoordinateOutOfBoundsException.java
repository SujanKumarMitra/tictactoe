package org.game.tictactoe.entity;

/**
 * @author Sujan Kumar Mitra
 * @since 2020-10-15
 */
public class CoordinateOutOfBoundsException extends ArrayIndexOutOfBoundsException {

	private static final long serialVersionUID = 1L;

	public CoordinateOutOfBoundsException() {
		super();
	}

	public CoordinateOutOfBoundsException(int index) {
		super(index);
	}

	public CoordinateOutOfBoundsException(String s) {
		super(s);
	}

}
