package abalonegame;

import java.awt.Point;

public class Move {

    Point p;
    int dir, attack, enemyAttack;
    double grade;
    boolean pushOut, virtual;
    Board TempBoard = new Board();

    /* Move constractor */
    public Move(Point sour, int dir, int attack, int enemyAttack, boolean pushOut) {
        this.p = sour; // Source point.
        this.dir = dir; // Direcion.
        this.attack = attack; // Self-Attack power by size of balls selected.
        this.enemyAttack = enemyAttack; // Number of balls blocking the attack.
        this.pushOut = pushOut; // Boolean to tell if ball being pushed out.
        this.virtual = true; // Boolean if move isn't really being made, but for ..
        // .. the check of AI. Default option is 'true'.
    }

    /* Function managing a move in virtual board for CPU to think.
        Input; The GameFrame.
        Processing; Function creating a Temporary board, clearing from the frame the both Selected and,
        unselected */
    public void DoMove(GameFrame g) {
        TempBoard.CopyBoard(g, g.m_Board.ArCells); // Copying currect board.

        /* Clearing balls */
        g.m_Board.SelectedBalls.clear();
        g.m_Board.Enemy.clear();

        Cell[][] mat = g.m_Board.ArCells; // New Cells array based on pieces.       
        int row = p.y, col = p.x; // Having location of clicked point.s

        /* Adding both parties given in message sent giving us the powers. */
        for (int i = 0; i < attack; i++) {
            g.m_Board.SelectedBalls.add(mat[row][col]);
            row += Board.Direction[dir][1];
            col += Board.Direction[dir][0];
        }
        for (int i = 0; i < enemyAttack; i++) {
            g.m_Board.Enemy.add(mat[row][col]);
            row += Board.Direction[dir][1];
            col += Board.Direction[dir][0];
        }
        g.m_Board.MoveCells(dir, this); // Move being in a copied board.
    }

    /* Gets back to the old board.
        Input: GameFrame of the board.
        Processing; Copying to Parent, the preiou*/
    public void UndoMove(GameFrame Parent) {
        Parent.m_Board.CopyBoard(Parent, TempBoard.ArCells);
    }

}
