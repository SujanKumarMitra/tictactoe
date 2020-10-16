package org.game.tictactoe.launcher;

import static org.game.tictactoe.entity.Cell.EMPTY;
import static org.game.tictactoe.entity.Cell.O;
import static org.game.tictactoe.entity.Cell.X;

import java.util.Scanner;

import org.game.tictactoe.entity.Board;
import org.game.tictactoe.entity.BoardImpl;
import org.game.tictactoe.entity.Cell;
import org.game.tictactoe.service.MinimaxService;
import org.game.tictactoe.service.MinimaxServiceImpl;
import org.game.tictactoe.service.Result;

/**
 * @author Sujan Kumar Mitra
 * @since 2020-10-16
 */
public class ConsoleApp {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the board size::");

		int size = sc.nextInt();
		Cell[][] cells = new Cell[size][size];

		System.out.println("Enter the board matrix::");
		System.out.println("X => X");
		System.out.println("O => O");
		System.out.println("$ => Empty");

		System.out.println();
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				System.out.printf("At %d,%d\n", i, j);
				String choice = sc.next();
				cells[i][j] = getCell(choice);
			}
		}
		sc.close();
		Board board = new BoardImpl(cells);
		MinimaxService minimax = new MinimaxServiceImpl();
		Result bestMove;

		long start = System.currentTimeMillis();
		bestMove = minimax.getNextBestMove(board);
		long end = System.currentTimeMillis();

		System.out.println(bestMove);
		System.out.println("Time taken = " + (end - start) + "ms");
	}

	private static Cell getCell(String choice) {
		switch (choice.toLowerCase()) {
		case "x":
			return X;
		case "o":
			return O;
		case "$":
			return EMPTY;
		default:
			System.out.println("unknown choice {" + choice + "}. Setting it to Empty");
			return EMPTY;
		}
	}
}
