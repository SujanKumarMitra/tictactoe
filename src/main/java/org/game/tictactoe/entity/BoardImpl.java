package org.game.tictactoe.entity;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Implementation of the {@link Board}
 * 
 * @author Sujan Kumar Mitra
 * @since 2020-10-16
 */
public class BoardImpl implements Board {

	/**
	 * the board
	 */
	private Cell[][] board;
	/**
	 * boundaries of {@link #board}
	 */
	private Coordinate bound;
	/**
	 * cache instance
	 */
	private CoordinateCache cache;

	/**
	 * Constructs a 3x3 playing board, all cells are set to {@link Cell#EMPTY}
	 */
	public BoardImpl() {
		initCache();
		this.board = createBoard(3);
		fillBoard();
		this.bound = cache.get(3, 3);
	}

	/**
	 * Constructs a board with the supplies cells, <br>
	 * checks whether the board is a square matrix or not
	 * 
	 * @param cells the cell
	 * @throws IllegalArgumentException if supplied cell is not a square matrix
	 */
	public BoardImpl(Cell[][] cells) throws IllegalArgumentException {
		Objects.requireNonNull(cells);
		assertEqualsRowsAndColumns(cells);
		initCache();
		this.board = cells;
		this.bound = cache.get(cells.length, cells[0].length);
	}

	/**
	 * Performs a deep-copy of the supplied board
	 * 
	 * @param board the board to copy
	 */
	public BoardImpl(Board board) {
		Objects.requireNonNull(board, () -> "board can't be null");
		initCache();
		this.board = deepCloneCells(board.getBoard());
		Coordinate bounds = board.getBounds();
		Objects.requireNonNull(bounds, () -> "bounds can't be null");
		this.bound = cache.get(bounds.getRowCoordinate(), bounds.getColumnCoordinate());
	}

	/**
	 * Constructs a board with the size supplied, all cells are set to
	 * {@link Cell#EMPTY}
	 * 
	 * @param boardSize the size
	 */
	public BoardImpl(int boardSize) {
		initCache();
		this.board = createBoard(boardSize);
		this.bound = cache.get(boardSize, boardSize);
	}

	/**
	 * creates a board instance with the given size
	 * 
	 * @param size the size
	 * @return a 2-d board
	 */
	private Cell[][] createBoard(int size) {
		return new Cell[size][size];
	}

	/**
	 * initializes the {@link #cache}
	 */
	private void initCache() {
		this.cache = CoordinateCache.getInstance();
	}

	/**
	 * Performs deep-copy of the cells supplied
	 * 
	 * @param cells the cells to copy
	 * @return the cloned cells
	 */
	private Cell[][] deepCloneCells(Cell[][] cells) {
		int rows = cells.length;
		int cols = cells[0].length;
		Cell[][] clones = new Cell[rows][cols];
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				clones[i][j] = cells[i][j];
			}
		}
		return clones;
	}

	/**
	 * Checks whether the cells supplied are a square matrix or not
	 * 
	 * @param cells the cells
	 */
	private void assertEqualsRowsAndColumns(Cell[][] cells) {
		int rows = cells.length;
		for (int i = 0; i < rows; i++) {
			if (cells[i].length != rows) {
				throw new IllegalArgumentException("board should be square");
			}
		}
	}

	/**
	 * fills the {@link #board} the {@link Cell#EMPTY}
	 */
	private void fillBoard() {
		for (Cell[] row : board) {
			Arrays.fill(row, Cell.EMPTY);
		}
	}

	@Override
	public Cell[][] getBoard() {
		return board;
	}

	@Override
	public Coordinate getBounds() {
		return bound;
	}

	@Override
	public void setCell(Cell cell, Coordinate coord) throws CoordinateOutOfBoundsException {
		Objects.requireNonNull(cell, () -> "cell can't be null");
		Objects.requireNonNull(coord, () -> "coord can't be null");
		try {
			board[coord.getRowCoordinate()][coord.getColumnCoordinate()] = cell;
		} catch (ArrayIndexOutOfBoundsException e) {
			throw new CoordinateOutOfBoundsException(e.getMessage());
		}
	}

	@Override
	public Cell getCell(Coordinate coord) throws CoordinateOutOfBoundsException {
		Objects.requireNonNull(coord, () -> "coord can't be null");
		try {
			return board[coord.getRowCoordinate()][coord.getColumnCoordinate()];
		} catch (ArrayIndexOutOfBoundsException e) {
			throw new CoordinateOutOfBoundsException(e.getMessage());
		}
	}

	@Override
	public Set<Coordinate> getUnfilledPositions() {
		Set<Coordinate> unfilledPositions = getSet();
		for (int row = 0; row < board.length; row++) {
			for (int col = 0; col < board[row].length; col++) {
				if (board[row][col] == Cell.EMPTY) {
					Coordinate coord = cache.get(row, col);
					unfilledPositions.add(coord);
				}
			}
		}
		return unfilledPositions;
	}

	/**
	 * Returns an instance of {@link Set}
	 * 
	 * @return a set
	 */
	private Set<Coordinate> getSet() {
//		return new HashSet<>();
		return new LinkedHashSet<>();
	}

}
