package knightPackage;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class Square extends JButton {
    private static final long serialVersionUID = 1L;
    private static int currentRow;
    private static int currentColumn;
    private static int count = 0; // total moves
    private boolean marked; // true if knight has touched this square
    private int av; // holds the accessibility value for this square
    private int r;
    private int c;
    Square(int r_, int c_, int acv) {
// super(x + ", " + y);
// super(""+acv);
        super();
        marked = false;
        av = acv;
        r = r_;
        c = c_;
        setOpaque(true);
        setBorder(BorderFactory.createLineBorder(Color.black));
        addActionListener(new clickHandler());
        setVisible(true);
    }

    public static int getCurrentRow() {
        return currentRow;
    }
    public static void setCurrentRow(int currentRow) {
        Square.currentRow = currentRow;
    }
    public static int getCurrentColumn() {
        return currentColumn;
    }
    public static void setCurrentColumn(int currentColumn) {
        Square.currentColumn = currentColumn;
    }

    public static int getCount() {
        return count;
    }
    public static void setCount(int count) {
        Square.count = count;
    }

    public boolean getMarked() {
        return marked;
    }

    public void setMarked(boolean marked) {
        this.marked = marked;
    }
    public int getAv() {
        return av;
    }
    public void setAv(int av) {
        this.av = av;
    }
    public int getR() {
        return r;
    }
    public void setR(int r) {
        this.r = r;
    }
    public int getC() {
        return c;
    }
    public void setC(int c) {
        this.c = c;
    }
    // this method sets this square as the current square by recording the
    // static varaibles currentRow and currentColumn
    // setting this square as marked, highlighting it to yellow, and setting its
    // text to the current count
    //
    public void setCurrentSq() {
        setText(Integer.toString(++count));
        setMarked(true);
        setBackground(Color.YELLOW);
        setCurrentRow(getR());
        setCurrentColumn(getC());
    }
    public void resetSq() {
        setText("");
        setMarked(false);
    }
    // this method highlights the square if it has not yet been visited by
    // the knight
    //
    public void highlightSq() {
        if (getBackground() != Color.GREEN && getMarked() == false) {
            setBackground(Color.GREEN);
        }
    }
    private class clickHandler implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            // if sq has never been touched and a valid position
            if ((getCount() == 0) || (getMarked() == false && getBackground() == Color.GREEN)) {
// System.out.println("SELECTED: " + r + " " + c);
                ChessBoard.resetColor();
                setCurrentSq();
                // get next moves and highlight those squares
                Moves.getMoves(getR(), getC());
            } else {
                JOptionPane.showMessageDialog(getParent(), "Illegal Move");
            }
        }
    }
}
