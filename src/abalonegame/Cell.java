package abalonegame;

import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

public class Cell {

    /* Decleration of global and class values */
    public static final int EMPTY = 1;
    public static final int BLACK = 2;
    public static final int RED = 3;
    public static final int SELECTEDBLACK = 4;
    public static final int SELECTEDRED = 5;
    public static int CellSizeW = 34;
    public static int CellSizeH = 36;
    public static int DirectionSize = 6;
    public int m_Row;
    public int m_Col;
    public int m_Type;
    GameFrame Parent;
    boolean bolVisited;

    /*
     * Cell Constractor - Creates a new instance of Cell
     */
    public Cell(GameFrame Parent, int row, int col, int Type) {
        this.Parent = Parent;
        m_Row = row;
        m_Col = col;
        m_Type = Type;
        bolVisited = false;
    }

    /* Function recieves the type and sends message to DrawImage to draw the imgane in given directions.
        Input: Type.
        Processing: Setting the type of the cell, and sending location to draw to DrawImage function.
     */
    public void SetCell(int Type) {
        m_Type = Type;
        DrawImage(Board.m_Start.x + 87, Board.m_Start.y + 70);
    }

    /* Function painting balls
        Input: (x;y) Locations.
        Processing: Depending on the type, Image being drawn.
     */
    public void DrawImage(int x, int y) {
        Graphics Gr = Parent.getGraphics();
        switch (m_Type) {
            case BLACK:
                Gr.drawImage(Board.BlackBallImage, x + (m_Col - 2) * 20, y + (m_Row - 1) * 35, CellSizeW, CellSizeH, Parent);
                break;

            case RED:
                Gr.drawImage(Board.RedBallImage, x + (m_Col - 2) * 20, y + (m_Row - 1) * 35, CellSizeW, CellSizeH, Parent);
                break;

            case SELECTEDRED:
                Gr.drawImage(Board.SelectedBallImage, x + (m_Col - 2) * 20, y + (m_Row - 1) * 35, CellSizeW, CellSizeH, Parent);
                break;

            case SELECTEDBLACK:
                Gr.drawImage(Board.SelectedBallImage, x + (m_Col - 2) * 20, y + (m_Row - 1) * 35, CellSizeW, CellSizeH, Parent);
                break;
        }
    }

    /* Function checking size selected not above 3, so if so Function won't let go
        Input: Non.
        Processing: Function checking the size and returning false of size is above 3, what's against the rules.
     */
    private boolean CheckPossibleSize() {
        return Parent.m_Board.SelectedBalls.size() < 3;
    }

    /* Function anaging the selections bzsed on user click. 
        Input; Non*
        Processing; Function checking via math calculations if the selection made is possible, After that sends.
    the recuired messages to draw the image 
     */
    public void ClickCell() {
        int S = Parent.m_Board.SelectedBalls.size() - 1,
                i = -1, // Check for first value as 'i' will be used later.
                R, C, T, R2, C2, T2; // Declecered integers which will be used later in function.
        boolean sec = false,
                thr = true;

        if (Board.Turn && m_Type == RED || !Board.Turn && m_Type == BLACK) { // Won't go if no meet with turn and selection.
            return;
        }

        if (S >= 0) {
            R = Parent.m_Board.SelectedBalls.get(0).m_Row;
            C = Parent.m_Board.SelectedBalls.get(0).m_Col;
            T = Parent.m_Board.SelectedBalls.get(0).m_Type;
            if (S == 0) {
                if (m_Type != T) {// Move-check will be based on first ball selected. 
                    for (i = 0; i < DirectionSize; i++) // Going though the directions and checking if move allowed.
                    {

                        if (R + Board.Direction[i][1] == m_Row
                                && C + Board.Direction[i][0] == m_Col) { // Direction right.
                            sec = true;
                        }
                    }
                }
            } else if (S == 1) {
                R2 = Parent.m_Board.SelectedBalls.get(1).m_Row;
                C2 = Parent.m_Board.SelectedBalls.get(1).m_Col;
                T2 = Parent.m_Board.SelectedBalls.get(1).m_Type;
                if (m_Type != T2) { // Selected ball is not already selected.
                    if (R == R2) {
                        if (m_Row == R) {
                            if ((Math.abs(m_Col - C2) > 2 && (Math.abs(m_Col - C) > 2))) {
                                thr = false;
                            }
                        } else {
                            thr = false;
                        }
                    } else if ((Math.abs(m_Col - C2) > 2 && (Math.abs(m_Col - C) > 2)) || m_Row == R || m_Row == R2 || m_Col == C || m_Col == C2) {
                        thr = false;
                    }
                }
            }
        }

        if ((!sec && i != -1) || !thr) {
            javax.swing.JOptionPane.showMessageDialog(Parent, "Impossible balls-selection have made.");
            javax.swing.JOptionPane.showMessageDialog(Parent, "Remainder: Unselect your balls to go for a new selection.");
            return;
        }

        /* Selection */
        switch (m_Type) {
            case BLACK:
                if (CheckPossibleSize()) {
                    m_Type = SELECTEDBLACK;
                    Parent.m_Board.SelectedBalls.add(this);
                }
                break;
            case RED:
                if (CheckPossibleSize()) {
                    m_Type = SELECTEDRED;
                    Parent.m_Board.SelectedBalls.add(this);
                }
                break;
            case SELECTEDRED:
                m_Type = RED;
                Parent.m_Board.SelectedBalls.remove(this);
                break;
            case SELECTEDBLACK:
                m_Type = BLACK;
                Parent.m_Board.SelectedBalls.remove(this);
                break;
        }
        DrawImage(Board.m_Start.x + 87, Board.m_Start.y + 70);
    }

    /* Function used to fill all avalible moves into the Moves ArrayList 
        Input: ArrayList MovesAvaliable that sent from CPU.
        Processing: Function running through all the avaliagle cells, that means that the next move is empty,
    adding them. Once no move avaliable moves, function starting to calcualte power of Human and power of,
    CPU, adding them too to the MovesAvaliable ArrayList. 
     */
    public void FillMovesAvaliable(ArrayList<Move> MovesAvaliable) {
        Cell[][] Mat = Parent.m_Board.ArCells;
        for (int i = 0; i < DirectionSize; i++) { // Running though all the directions.
            if (Mat[(m_Row + Board.Direction[i][1])][(m_Col + Board.Direction[i][0])] != null) { // Checks only on actual cells.
                if (Mat[(m_Row + Board.Direction[i][1])][(m_Col + Board.Direction[i][0])].m_Type == 1) { // Add to avaliable if empty cell.
                    MovesAvaliable.add(new Move(new Point(m_Col, m_Row), i, 1, 0, false));

                } else { // If not empty - do the sizes calculation.
                    /* Saving location. */
                    int row = m_Row;
                    int col = m_Col;
                    /* Decleration two integers for power. */
                    int humanPower = 0;
                    int EnemyPower = 0;

                    while ((Mat[row][col] != null) && (Mat[row][col].m_Type == m_Type)) { // Adding to Human attack till same type and not null.
                        row += Board.Direction[i][1];
                        col += Board.Direction[i][0];
                        humanPower++;
                    }

                    if ((Mat[row][col] != null) && (humanPower <= 3)) { // If not stack ball and not out.
                        if (Mat[row][col].m_Type == 1) {
                            MovesAvaliable.add(new Move(new Point(m_Col, m_Row), i, humanPower, 0, false)); // Add free spots.
                        } else {
                            while ((Mat[row][col] != null) && (Mat[row][col].m_Type == 3)) { // Consider enemy.
                                row += Board.Direction[i][1];
                                col += Board.Direction[i][0];
                                EnemyPower++;
                            }
                            if (humanPower > EnemyPower) { // Avaliable despite the power of enemy.
                                MovesAvaliable.add(new Move(new Point(m_Col, m_Row), i, humanPower, EnemyPower, false));
                            }
                        }
                    }
                }
            }
        }
    }
}