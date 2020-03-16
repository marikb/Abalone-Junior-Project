package abalonegame;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Board {

    GameFrame Parent;
    public static Point m_Start;

    /* Sizes of Rows and Cols in the board. */
    public static final byte ROWS = 11;
    public static final byte COLS = 21;

    /* Images */
    public static Image RedBallImage, BlackBallImage, SelectedBallImage;

    /* Setting global statments */
    public static final byte EMPTY = 1;
    public static final byte BLACK = 2;
    public static final byte RED = 3;
    public static final byte SELECTED = 4;

    /* ArrayLists */
    public ArrayList<Cell> SelectedBalls;
    public ArrayList<Cell> Enemy;
    ArrayList<Cell> OutRed, OutBlack;
    
    Cell[][] ArCells; // The Boards by cells.

    public static boolean Turn = false; // To keep the following after every turn.

    /* Players - Human and CPU */
    HumanPlayer m_Human;
    ComputerPlayer m_Comp;

    /* InitBoard for the board the painting */
    public static byte[][] InitBoard = {
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 2, 0, 2, 0, 2, 0, 2, 0, 2, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 2, 0, 2, 0, 2, 0, 2, 0, 2, 0, 2, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 1, 0, 1, 0, 2, 0, 2, 0, 2, 0, 1, 0, 1, 0, 0, 0, 0},
        {0, 0, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 0, 0},
        {0, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 0},
        {0, 0, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 0, 0},
        {0, 0, 0, 0, 1, 0, 1, 0, 3, 0, 3, 0, 3, 0, 1, 0, 1, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 3, 0, 3, 0, 3, 0, 3, 0, 3, 0, 3, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 3, 0, 3, 0, 3, 0, 3, 0, 3, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
    };

    private int Num; // Variable checking the game status: Against Player or CPU.

    /* Possible directions array to apply ball moves based on the selected direction */
    static int[][] Direction
            = {// dx dy 
                {-1, -1}, // LEFTUP
                {1, -1}, // RIGHTUP
                {2, 0}, // RIGHT
                {1, 1}, // RIGHTDOWN
                {-1, 1}, // LEFTDOWN
                {-2, 0} // LEFT
            };

    /* Creates a new instance of Board */
    public Board(GameFrame Parent, Point Start, int Num) {

        /* Setting the images to to know to each image we relate */
        RedBallImage = Parent.getToolkit().createImage("Red.png");
        BlackBallImage = Parent.getToolkit().createImage("Black.png");
        SelectedBallImage = Parent.getToolkit().createImage("Selected.png");

        m_Human = new HumanPlayer(Parent);

        if (Num == 1) { // Creating a computer player in case user selected vs Computer
            this.m_Comp = new ComputerPlayer(Parent);
        }

        /* Setting values recieved from frame. */
        this.Parent = Parent;
        m_Start = Start;
        this.Num = Num;

        SelectedBalls = new ArrayList<Cell>();  // Own Selected ArrayList
        Enemy = new ArrayList<Cell>();          // Enemy balls  ArrayList 
        ArCells = new Cell[ROWS][COLS];         // Cells array
        OutRed = new ArrayList<Cell>();         // Lost RED   balls
        OutBlack = new ArrayList<Cell>();       // Lost BLACK balls

        /* Painting the cells based on our InitBoard decerated before. */
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {

                if (InitBoard[i][j] >= 1) {
                    ArCells[i][j] = new Cell(Parent, i, j, InitBoard[i][j]);
                    ArCells[i][j].DrawImage(Board.m_Start.x + 87, Board.m_Start.y + 70);
                    if (InitBoard[i][j] == 2) {
                        if (m_Comp != null) { // Adding balls to Computer if user decided to play him.
                            this.m_Comp.pieces.add(ArCells[i][j]);
                        }
                    }
                    if (InitBoard[i][j] == 3) { // Adding balls to Human.
                        this.m_Human.pieces.add(ArCells[i][j]);
                    }
                } else {
                    ArCells[i][j] = null; // Setting empty spots.
                }

            }
        }
    }

    /* Empty Constractor for when Copying board and doing moves on it */
    public Board() {
    }

    /* CopyBoard function will be used to copy the board to the blank board created above. Board is based on currect board.
        Input; Function recieving the GameFrame, and the Array of Cells.
        Processing; Function Creating Human and Player, and for cells it inits back the pieces of each party,
    so what's literally being done is a Copied board.
     */
    public void CopyBoard(GameFrame Parent, Cell[][] Mat) {
        m_Human = new HumanPlayer(Parent);
        m_Comp = new ComputerPlayer(Parent);
        ArCells = new Cell[ROWS][COLS];
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                if (Mat[i][j] != null) {
                    ArCells[i][j] = new Cell(Parent, Mat[i][j].m_Row, Mat[i][j].m_Col, Mat[i][j].m_Type);
                    if (Mat[i][j].m_Type == Cell.BLACK) {
                        m_Comp.pieces.add(ArCells[i][j]);
                    }
                    if (Mat[i][j].m_Type == Cell.RED) {
                        m_Human.pieces.add(ArCells[i][j]);
                    }
                } else {
                    ArCells[i][j] = null;
                }
            }
        }
    }

    /* Function manages the graphic drawings.
        Input; Function recieves all graphics. to draw, by size calculation.
        Processing; Function running through all row and cols and draws according to the given type, what needed.
    Firstly, Function draws all cells, after that function draws the balls.
     */
    public void Draw(Graphics Gr) {
        /* Balls drawning */
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                if (ArCells[i][j] != null && ArCells[i][j].m_Type > 1) {
                    ArCells[i][j].DrawImage(Board.m_Start.x + 87, Board.m_Start.y + 70);
                }
            }
        }

        /* Balls poped out drawnings. */
        for (int i = 0; i < OutRed.size(); i++) {
            OutRed.get(i).DrawImage(GameFrame.m_Start.x + Parent.wBoard + 25 + i * (Cell.CellSizeW + 4), 60);
        }
        for (int i = 0; i < OutBlack.size(); i++) {
            OutBlack.get(i).DrawImage(GameFrame.m_Start.x + Parent.wBoard + 25 + i * (Cell.CellSizeW + 4), 160);
        }

    }

    /* Function recieving a click location, checking if legal and if so, sends a message to Cell to click. */
    public void Click(Point point) {
        int row = (point.y - m_Start.y - 70) / 35 + 1;
        int col = (point.x - m_Start.x - 87) / 20 + 2;
        if (IsLegal(row, col) && ArCells[row][col] != null) {
            ArCells[row][col].ClickCell(); // ClickCell in Cell class, checking if the click is legal, and selecting based on situation. 
        }
    }

    /* Function checking if the made click is in board's size */
    private boolean IsLegal(int row, int col) {
        return row >= 0 && row < ROWS && col >= 0 && col < COLS;
    }

    /* Changing Turn function, also changing who's turn box in GUI */
    public void ChangeTurn() {
        Turn = !Turn;
        Parent.Turn = Turn ? Parent.getToolkit().createImage("Black.png")
                : Parent.getToolkit().createImage("Red.png");
        Parent.repaint();
    }

    /* Function gets from main a direction to move
        Input; Integer Dir which represents the move being made, later will be translate in the Direction matrix.
        Processing; Function does not go for non-selected clicks. Then function copies the array for the type tests,
    finding the enemy if any to count his power. If move is legal, function sends message to MoveCells to start
    the movment, otherwise it alerts the error via a MessageDialog.
     */
    public void DoStep(int Dir) {
        if (SelectedBalls.isEmpty()) { // Function won't move if no balls selected to move.
            javax.swing.JOptionPane.showMessageDialog(Parent, "Woops, you haven't selected any ball, OR forgot to re-select, select some by clicking on them before moving.");
            return;
        }
        Object[] ArC = SelectedBalls.toArray(); // ArC - Copied Array which will be used for checks.
        int row = ((Cell) ArC[0]).m_Row;
        int col = ((Cell) ArC[0]).m_Col;
        while (ArCells[row][col] != null && ArCells[row][col].m_Type == ((Cell) ArC[0]).m_Type) { //  Skip same type
            row += Direction[Dir][1];
            col += Direction[Dir][0];
        }
        int EnemyType = ((Cell) ArC[0]).m_Type == Cell.SELECTEDBLACK ? Cell.RED : Cell.BLACK; // // Decleration of integer to later check which is enemy.

        //  Check is enemy - IF YES -> Building ArrayList to know the enemy's size.
        while (ArCells[row][col] != null && ArCells[row][col].m_Type == EnemyType && Enemy.size()<3) {
            Enemy.add(ArCells[row][col]);
            row += Direction[Dir][1];
            col += Direction[Dir][0];
        }

        /* Check if ball-push can be done. */
        if (ArCells[row][col] != null // The done move isn't going outside.
                && ArCells[row][col].m_Type == Cell.EMPTY // Moving it an empty cell.
                && (SelectedBalls.size() > Enemy.size() // Move done is possible and Enemy's size isn't bigger.  
                && Enemy.size() > 0 || Enemy.isEmpty())) { // The enemy is being moved.

            MoveCells(Dir, null);
            ChangeTurn();
        } else { // Recognizing another illegal move - pushing ball outside the board.
            if (ArCells[row][col] == null) { // Going for checks if the 
                if (Enemy.isEmpty()) { // Confiration the next move is outside the board.

                    javax.swing.JOptionPane.showMessageDialog(Parent, "Illegal - Pushing own ball outside the board is against the game motivation, as you might lose it.");
                    javax.swing.JOptionPane.showMessageDialog(Parent, "Remainder: Unselect your balls to go for a new selection.");

                } else {
                    if (SelectedBalls.size() > Enemy.size() && Enemy.size() > 0) {
                        MoveCells(Dir, null);
                        ChangeTurn();
                    }
                }
            } else {
                javax.swing.JOptionPane.showMessageDialog(Parent, "Illegal - Attempt to go on impossible push. To check the rules press 'H'. ");
                javax.swing.JOptionPane.showMessageDialog(Parent, "Remainder: Unselect your balls to go for a new selection.");

            }
        }
        SelectedBalls.clear();
        Enemy.clear();
    }

    /* Function to set Player and Enemy to sort, to know which set should be set as null, and moves the balls in the given direction.
    + Sending messages to make move for AI
        Input; Integer represents the direction, and Comp move.
        Processing; Function re-setting the Selected and Enemy balls, and moving them.*/
    public void MoveCells(int Dir, Move Comp) {

        /* Sorting both Selected and Enemy ArrayLists to know which cell to set null */
        Sort(SelectedBalls, Dir);
        Sort(Enemy, Dir);

        /* Moving Enemy's balls, then Player's */
        for (int i = 0; i < Enemy.size(); i++) {
            MoveCell(Enemy.get(i), Dir, Comp);
        }
        for (int i = 0; i < SelectedBalls.size(); i++) {
            MoveCell(SelectedBalls.get(i), Dir, Comp);
        }

        SelectedBalls.clear();
        Enemy.clear();

        Win(); // Check if win.

        if (Comp == null && m_Comp != null) {
            m_Comp.AIDoStep();
        }
    }

    /* Function sorting the moving balls to know which cells should be set to EMPTY
        Input; ArrayList needed to sort, direction integer.
        Processing; Function using the direction to recognise which treat should the ArrayList have,
    depending on it function does the changes.
     */
    private void Sort(ArrayList List, int Dir) {
        Object[] Ob = List.toArray(); // Copying the ArrayList given.

        if (Dir < 2) { // Working with RIGHT-UP and LEFT-UP directions.
            for (int i = 0; i < List.size() - 1; i++) {
                for (int j = 0; j < List.size() - 1; j++) {
                    if (((Cell) Ob[j]).m_Row > ((Cell) Ob[j + 1]).m_Row) {
                        Object T = Ob[j];
                        Ob[j] = Ob[j + 1];
                        Ob[j + 1] = T;
                    }
                }
            }
        }

        if (Dir == 3 || Dir == 4) { // Working with RIGHT-DOWN and LEFT-DOWN directions.
            for (int i = 0; i < List.size() - 1; i++) {
                for (int j = 0; j < List.size() - 1; j++) {
                    if (((Cell) Ob[j]).m_Row < ((Cell) Ob[j + 1]).m_Row) {
                        Object T = Ob[j];
                        Ob[j] = Ob[j + 1];
                        Ob[j + 1] = T;
                    }
                }
            }
        }

        if (Dir == 2) { // Working with RIGHT direction.
            for (int i = 0; i < List.size() - 1; i++) {
                for (int j = 0; j < List.size() - 1; j++) {
                    if (((Cell) Ob[j]).m_Col < ((Cell) Ob[j + 1]).m_Col) {
                        Object T = Ob[j];
                        Ob[j] = Ob[j + 1];
                        Ob[j + 1] = T;
                    }
                }
            }
        }

        if (Dir == 5) { // Working with LEFT direction.
            for (int i = 0; i < List.size() - 1; i++) {
                for (int j = 0; j < List.size() - 1; j++) {
                    if (((Cell) Ob[j]).m_Col > ((Cell) Ob[j + 1]).m_Col) {
                        Object T = Ob[j];
                        Ob[j] = Ob[j + 1];
                        Ob[j + 1] = T;
                    }
                }
            }
        }
        List.clear();
        List.addAll(Arrays.asList(Ob));
    }

    /* The actual function that runs the moves and moves only selected balls + Removes if balls pushed outside the board.
    +  The moves cells are actually sorted to the function knows which cells to update. 
        Input: Cell, direction, and Computer move.
        Processing; Function checking if ball pushed out, if yes it removes it, sending message to CPU if played, 
    Updating the pieces and eventually setting the place the ball moves from to EMPTY.
     */
    private void MoveCell(Cell C, int Dir, Move Comp) {
        int row = C.m_Row;
        int col = C.m_Col;
        switch (C.m_Type) {
            case Cell.RED:
            case Cell.SELECTEDRED:
                if (ArCells[row + Direction[Dir][1]][col + Direction[Dir][0]] == null) {
                    if (Comp == null || !Comp.virtual) {
                        PutOut(OutRed, Cell.RED, GameFrame.m_Start.y);
                        m_Human.pieces.remove(C);
                    } else {
                        Comp.pushOut = true;
                    }
                } else {
                    ArCells[row + Direction[Dir][1]][col + Direction[Dir][0]].SetCell(Cell.RED);
                    m_Human.upDateList(ArCells[row + Direction[Dir][1]][col + Direction[Dir][0]], C);
                }
                break;
            case Cell.BLACK:
            case Cell.SELECTEDBLACK:
                if (ArCells[row + Direction[Dir][1]][col + Direction[Dir][0]] == null) {
                    if (Comp == null) {
                        PutOut(OutBlack, Cell.BLACK, GameFrame.m_Start.y + 100);
                        if (Comp != null) {
                            m_Comp.pieces.remove(C);
                        }
                    } else {
                        Comp.pushOut = true;
                    }
                } else {
                    ArCells[row + Direction[Dir][1]][col + Direction[Dir][0]].SetCell(Cell.BLACK);
                    if (m_Comp != null) {
                        m_Comp.upDateList(ArCells[row + Direction[Dir][1]][col + Direction[Dir][0]], C);
                    }
                }
                break;
        }
        ArCells[row][col].SetCell(Cell.EMPTY);
    }

    /* Function manages the pushed outside balls.
        Input; ArrayList we're drawning, type drawn and y location(There's a difference of 100 between the two bars)
        Processing; Function adds to the drawning the cell and adds ArrayList
     */
    private void PutOut(ArrayList<Cell> Out, int T, int y) {
        Cell Temp = new Cell(Parent, 1, 2, T);
        Temp.DrawImage(GameFrame.m_Start.x + Parent.wBoard + 20 + Out.size() * (Cell.CellSizeW + 6), y);
        Out.add(Temp);
    }

    /* Function checks if any side won the game by pushed out sizes, to annouch so and start a new game */
    private void Win() {
        if (OutRed.size() == 6) {
            javax.swing.JOptionPane.showMessageDialog(Parent, "Black player has won the game!");
            Parent.NewGame(Num);
        }
        if (OutBlack.size() == 6) {
            javax.swing.JOptionPane.showMessageDialog(Parent, "Red player has won the game!");
            Parent.NewGame(Num);
        }
    }
}
