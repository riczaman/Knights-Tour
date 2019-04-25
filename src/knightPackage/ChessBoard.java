package knightPackage;
import java.awt.*;
import javax.swing.*;


public class ChessBoard extends JFrame {
    private static final long serialVersionUID = 1L;
    private static Square[][] board;
    private static final int NUM_ROWS = 8;
    private static final int SQ_SIZE = 80;
    private static boolean tour;

    private static final int[][] accArr = { { 2, 3, 4, 4, 4, 4, 3, 2 }, { 3, 4, 6, 6, 6, 6, 4, 3 },
            { 4, 6, 8, 8, 8, 8, 6, 4 }, { 4, 6, 8, 8, 8, 8, 6, 4 }, { 4, 6, 8, 8, 8, 8, 6, 4 },
            { 4, 6, 8, 8, 8, 8, 6, 4 }, { 3, 4, 6, 6, 6, 6, 4, 3 }, { 2, 3, 4, 4, 4, 4, 3, 2 } };

    ChessBoard() {
        super("Knight's Tour Problem");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(NUM_ROWS * SQ_SIZE, NUM_ROWS * SQ_SIZE));
        setLayout(new GridLayout(NUM_ROWS, NUM_ROWS));
        setLocation(400,50);

        board = new Square[8][8];
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                board[row][col] = new Square(row, col, accArr[row][col]);
                // set color
                if ((row % 2 == 1 && col % 2 == 1) || (row % 2 == 0 && col % 2 == 0)) {
                    board[row][col].setBackground(Color.BLACK);
                } else {
                    board[row][col].setBackground(Color.WHITE);
                }
                add(board[row][col]);
            }
        }
        pack();
        setVisible(true);
    }

    public static int getNumRows() {
        return NUM_ROWS;
    }
    public static int getSqSize() {
        return SQ_SIZE;
    }

    public static boolean isTour() {
        return tour;
    }

    public static void setTour(boolean tour) {
        ChessBoard.tour = tour;
    }

    public static void resetColor() {
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                if ((row % 2 == 1 && col % 2 == 1) || (row % 2 == 0 && col % 2 == 0)) {
                    board[row][col].setBackground(Color.BLACK);
                } else {
                    board[row][col].setBackground(Color.WHITE);
                }
            }
        }
    }

    public static Square[][] getChessBoard() {
        return board;
    }
    public static void restartGame() {
        resetColor();
        Square.setCount(0);
        Square.setCurrentRow(0);
        Square.setCurrentColumn(0);

        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                board[row][col].resetSq();
            }
        }

    }
}

