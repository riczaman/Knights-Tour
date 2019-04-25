package knightPackage;
import javax.swing.JOptionPane;
// contains information about the possible
// moves possible by the knight
//
public class Moves {

    private static int speed;
    // array of possible vertical/row moves
    private static final int[] VERTICAL = { -1, -2, -2, -1, 1, 2, 2, 1 };
    // array of possible horizontal/column movies
    private static final int[] HORIZONTAL = { 2, 1, -1, -2, -2, -1, 1, 2 };

    public static void setSpeed(int speed) {
        Moves.speed = speed;
    }

    public static int getSpeed() {
        return speed;
    }

    // get the moves possible based on the knight's
    // current position
    // if the square has not been "marked", highlight
    // the square
    //
    public static void getMoves(int row, int col) {
        System.out.println("in get moves");
        int possibleMoves = 0;
        Square[][] board = ChessBoard.getChessBoard();
        // go through possible moves array
        for (int move = 0; move < HORIZONTAL.length; move++) {
            // check if the next move is within bounds
            if (isValidMove(row, VERTICAL[move], col, HORIZONTAL[move])) {
                // check that this next move hasnt already been marked
                if (board[(row + VERTICAL[move])][(col + HORIZONTAL[move])].getMarked() != true) {
                    System.out.println("next move: " + (row + VERTICAL[move]) + ", " + (col + HORIZONTAL[move]));
                    board[(row + VERTICAL[move])][(col + HORIZONTAL[move])].highlightSq();
                    possibleMoves++;
                }
            }
        }
        if (possibleMoves == 0) {
            JOptionPane.showMessageDialog(null, "Try Again", "Game Over", JOptionPane.WARNING_MESSAGE);
            System.exit(0);
// new EndGameFrame("Game Over");

        }
    }
    // this method chooses the next move based on the accessibility rating of the
    // potential
    // next moves. if there mulitple squares share he lowest accessbility rating,
    // the square that was
    // discovered last will be selected over the others as the next move
    //
    public static void getNextAccMove(int row, int col) {
        System.out.println("in accessbility moves");
        int possibleMoves = 0;
        Square[][] board = ChessBoard.getChessBoard();
        // loop through 2d arr
        // store row and col of lowest av
        int r_min = 0;
        int c_min = 0;
        int av_min = 9;
        for (int move = 0; move < HORIZONTAL.length; move++) {
            if (isValidMove(row, VERTICAL[move], col, HORIZONTAL[move])) {
                if (board[(row + VERTICAL[move])][(col + HORIZONTAL[move])].getMarked() != true) {
                    System.out.println("next move: " + (row + VERTICAL[move]) + ", " + (col + HORIZONTAL[move]));
                    board[(row + VERTICAL[move])][(col + HORIZONTAL[move])].highlightSq();
                    if (board[(row + VERTICAL[move])][(col + HORIZONTAL[move])].getAv() <= av_min) {
                        av_min = board[(row + VERTICAL[move])][(col + HORIZONTAL[move])].getAv();
                        r_min = row + VERTICAL[move];
                        c_min = col + HORIZONTAL[move];
                    }
                    possibleMoves++;
                }
            }
        }
        if (possibleMoves == 0) {
            if (ChessBoard.isTour() == false) {
                JOptionPane.showMessageDialog(null, "Try Again", "Game Over", JOptionPane.WARNING_MESSAGE);
                System.exit(0);
            }else {
                ChessBoard.restartGame();
                ChessBoard.setTour(false);
            }
        } else {
            try {
                Thread.sleep(speed);
                ChessBoard.resetColor();
                ChessBoard.getChessBoard()[r_min][c_min].setCurrentSq(); // set the next yellow square here
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    private static boolean isValidMove(int x, int mx, int y, int my) {
        return (x + mx >= 0) && (x + mx < 8) && (y + my >= 0) && (y + my < 8) ? true : false;
    }
}
