package knightPackage;
import java.util.ArrayList;
import javax.swing.*;

public class KnightTourMain {
    public static void manualMode() {
        new ChessBoard();

    }
    public static void heuristicSelectStart() {
        ChessBoard b = new ChessBoard();
        ChessBoard.setTour(false);
        int num_sq = ChessBoard.getNumRows() * ChessBoard.getNumRows();
        int currentRow = -1;
        int currentColumn = -1;
        // select start position
        while (currentRow < 0 || currentRow > 8) {
            currentRow = Integer.valueOf(JOptionPane.showInputDialog(b, "Enter Row"));
        }
        while (currentColumn < 0 || currentColumn > 8) {
            currentColumn = Integer.valueOf(JOptionPane.showInputDialog(b, "Enter Column"));
        }
        while (Moves.getSpeed() < 1 || Moves.getSpeed() > 1000) {
            Moves.setSpeed(
                    Integer.valueOf(JOptionPane.showInputDialog(b, "Enter a speed from 1 to 1000, inclusive")));
        }
        // set the first square as the current and mark it
        ChessBoard.getChessBoard()[currentRow][currentColumn].setCurrentSq();
        // get possible moves based on the current position, select the move
        // this method is updated to set the new position from within
        Moves.getNextAccMove(currentRow, currentColumn);
        while (Square.getCount() < num_sq) {
            Moves.getNextAccMove(Square.getCurrentRow(), Square.getCurrentColumn());
        }
        if (Square.getCount() == 64) {
            JOptionPane.showMessageDialog(null, "Winnnerr!! You have won this showdown young grasshopper", "Game Over", JOptionPane.WARNING_MESSAGE);
            System.exit(0);
        }
    }
    public static void heuristicRunAllSq() {
        ChessBoard b = new ChessBoard();
        int num_sq = ChessBoard.getNumRows() * ChessBoard.getNumRows();
        ArrayList<Integer> fullTours = new ArrayList<Integer>();
        while (Moves.getSpeed() < 1 || Moves.getSpeed() > 1000) {
            Moves.setSpeed(Integer.valueOf(
                    JOptionPane.showInputDialog(b, "Enter a speed from 1 (fast) to 1000 (slow), inclusive")));
        }
        int tourSize = 0;
        // start from 0,0
        for (int r = 0; r < ChessBoard.getNumRows(); r++) {
            for (int c = 0; c < ChessBoard.getNumRows(); c++) {
                ChessBoard.setTour(true);
                // set the first square as the current and mark it
                ChessBoard.getChessBoard()[r][c].setCurrentSq();
                // get possible moves based on the current position, select the move
                // this method is updated to set the new position from within
                Moves.getNextAccMove(r, c);
                while (Square.getCount() < num_sq && ChessBoard.isTour() == true) {
                    Moves.getNextAccMove(Square.getCurrentRow(), Square.getCurrentColumn());
                }
                if (Square.getCount() == 64) {
                    fullTours.add(r);
                    fullTours.add(c);
                    ChessBoard.restartGame();
                }
                tourSize++;
            }
        }
        int pos = 0;
        String allFullTours = "";
        for (int i = 0; i < fullTours.size(); i++) {
            allFullTours += " Position: " + ++pos + "\n" + " Row: " + fullTours.get(i++) + ", Column: "
                    + fullTours.get(i) + "\n\n";
        }
        String result = "Total Rounds: " + tourSize + "\n\n" + "Successful tours: \n" + allFullTours;
        JOptionPane.showMessageDialog(null, result, "Game Over", JOptionPane.PLAIN_MESSAGE);
        System.exit(0);
    }
    public static void main(String[] args) {
       /* try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }*/

        /*************************
         *
         * TASK ONE: MANUAL MODE
         *
         *************************/
       // manualMode();
        /*************************
         *
         * TASK TWO: ACCESSIBILITY HEURISTIC selecting start position
         *
         * 0,0 61 0,7 56 7,0 60 7,7 56
         *
         *************************/
        //heuristicSelectStart();
        /*************************
         *
         * TASK TWO: ACCESSIBILITY HEURISTIC 64 runs
         *
         * 0,0 61 0,7 56 7,0 60 7,7 56
         *
         *************************/
        heuristicRunAllSq();
    }
}
