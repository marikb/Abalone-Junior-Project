package abalonegame;

import java.util.ArrayList;

/* Abstract class Player that both Human and Computer extends from, which allows the rating of the currect position.*/
public abstract class Player {

    /* Decleration of global values */
    GameFrame m_Parent;
    ArrayList<Cell> pieces;
    ArrayList<Spot> spotList;

    /* Player constractor */
    public Player(GameFrame Parent) {
        this.pieces = new ArrayList();
        this.spotList = new ArrayList();
        this.m_Parent = Parent;
    }

    /* Function that both players extend, but do differently. */
    public abstract void DoStep();

    /* Sets the pieces to be recognised as not visited */
    public void InitCells() {
        for (int i = 0; i < pieces.size(); i++) {
            ((Cell) pieces.get(i)).bolVisited = false;
        }
    }

    /* Function creating all spots for player */
    public void createSpots() {
        InitCells(); // Sets for all pieces Visited status to false.
        spotList.clear(); // Clearning previous created spots.
        Spot s = new Spot();
        spotList.add(s);
        makeSpot((Cell) pieces.get(0), ((Cell) pieces.get(0)).m_Type); // Function recursively calculates the spot.
        ((Spot) spotList.get(0)).setGrade(); // Setting grade for the currect spot.
        for (int i = 1; i < pieces.size(); i++) {
            if (((Cell) pieces.get(i)).bolVisited == false) { /* Creating spot for unvisited spots */
                spotList.add(new Spot());
                makeSpot((Cell) pieces.get(i), ((Cell) pieces.get(i)).m_Type);
                ((Spot) spotList.get(spotList.size() - 1)).setGrade(); // Setting grade.
            }
        }
    }


    /* Function making the spots and adding them to the spot ArrayList
        Input; Cell and type.
        Processing; Function adding all cells to the currect spot according to the spots around it.
    */
    private void makeSpot(Cell cell, int type) {
        if (cell == null) {
            return;
        }
        Cell[][] m = m_Parent.m_Board.ArCells; // New Cells array, based on real one.
        int row = cell.m_Row;
        int col = cell.m_Col;
        
        if ((cell.m_Type != type) || cell.bolVisited) { // Spot already made.
            return;
        }
        ((Spot) spotList.get(spotList.size() - 1)).listSpot.add(cell); // Else - Make the spot and add it to the spots list.
        m[row][col].bolVisited = true;

        /* Making spot in all directions recursively. */
        makeSpot(m[Board.Direction[0][1] + row][Board.Direction[0][1] + col], type);
        makeSpot(m[Board.Direction[1][1] + row][Board.Direction[0][1] + col], type);
        makeSpot(m[Board.Direction[2][1] + row][Board.Direction[0][1] + col], type);
        makeSpot(m[Board.Direction[3][1] + row][Board.Direction[0][1] + col], type);
        makeSpot(m[Board.Direction[4][1] + row][Board.Direction[0][1] + col], type);
        makeSpot(m[Board.Direction[5][1] + row][Board.Direction[0][1] + col], type);
    }
    
    /* Function updating new cell in old cell */
    public void upDateList(Cell newCell, Cell oldCell)
    {
        pieces.remove(oldCell);
        pieces.add(newCell);
    }
}
