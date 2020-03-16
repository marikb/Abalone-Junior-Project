package abalonegame;

import java.util.ArrayList;

public class ComputerPlayer extends Player {

    ArrayList<Move> Avaliable; // All avaliable moves for CPU.

    /* ComputerPlayer constractor */
    public ComputerPlayer(GameFrame Parent) {
        super(Parent);
        this.Avaliable = new ArrayList();
    }

    /* Function making the CPU move after finding the best move.
        Input; Non.
        Processing; Making visible moves to find the best, and returns back for all avaliable moves,
    calculating Human moves as well. Eventually, best move being chose and made.
    */
    public void DoStep() {
        FillMoves(); // Setting up the all possible moves.
        Move BestMove = null; // Best move we're looking for.
        double MaxGrade = -30000.0D; // Minimal possible move.
        for (int i = 0; i < Avaliable.size(); i++) {

            ((Move) Avaliable.get(i)).DoMove(m_Parent); // Making a move for Humman in temp board.
            pieces = m_Parent.m_Board.m_Comp.pieces; // Inits comp balls.
            ((Move) Avaliable.get(i)).grade = GetGrade(); // Calculationg of move grade.
            if (((Move) Avaliable.get(i)).pushOut) {
                ((Move) Avaliable.get(i)).grade += 100.0D; // High motivation to push out.
            }
            ((Move) Avaliable.get(i)).UndoMove(m_Parent);
            pieces = m_Parent.m_Board.m_Comp.pieces; // Sets back comp balls.
            if (((Move) Avaliable.get(i)).grade > MaxGrade) {
                MaxGrade = ((Move) Avaliable.get(i)).grade;
                BestMove = (Move) Avaliable.get(i);
            }
        }

        if (BestMove != null) {
            BestMove.virtual = false; // Not a virtual move and actually made one.
            BestMove.DoMove(m_Parent);
        }
        m_Parent.m_Board.ChangeTurn(); // Change turn.
    }

    /* Function that creates for all pieces it's avaliable moves and save it into ArrayList 'Avaliable' */
    private void FillMoves() {
        for (int i = 0; i < pieces.size(); i++) {
            ((Cell) pieces.get(i)).FillMovesAvaliable(Avaliable); // Fill all avaliable moves for given pieces.
        }
    }

    /* Function to calcualte the grade of every spot
        Input; Non.
        Processing; Function clculating it's grade based on the spots,
    calculating Human's grade based on his Spots together, and setting best
    move to go on min-max calculation and to know best move.
     */
    private double GetGrade() {
        createSpots(); // Absract function that creates the spots of CPU.
        double grade = 0.0; // Decleration double for CPU grade.
        for (int i = 0; i < spotList.size(); i++) { // Calculate grade for every spot.
            grade += ((Spot) spotList.get(i)).SpotGrade;
        }
        grade /= spotList.size(); // Calculation recognizing the less spots, ..
                                 // .. higher grade.
        /* Creating the spots for Human and calculating them. */
        m_Parent.m_Board.m_Human.createSpots();
        double HumGrade = 0.0; // Decleration double for Human grade. 
        for (int i = 0; i < m_Parent.m_Board.m_Human.spotList.size(); i++) {
            HumGrade += ((Spot) m_Parent.m_Board.m_Human.spotList.get(i)).SpotGrade;
        }
        return ((m_Parent.m_Board.m_Human.spotList.size() - spotList.size()) * 4 + grade - HumGrade); // Function calculating grade in min-max style.
    }

    /* Thread thinking to protect the moves to mix.
        Input: Non.
        Processing; sending to AI class that extends from Thread the ComputerPlayer.
     */
    void AIDoStep() {
        AI Ai = new AI(this);
        Ai.start();
    }

}
