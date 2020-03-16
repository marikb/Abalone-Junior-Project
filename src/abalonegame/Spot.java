package abalonegame;

import java.util.ArrayList;

/* Being in spot and in the center - Grading system based in the fact that being in the center is fater
   then the borders. As long as your ball is futher from the borders, it's safer, and when more balls 
   gathered in together in a pack there are more likely to be 3 row+col balls which cannot be moved.   */
public class Spot {

    public ArrayList<Cell> listSpot;
    public double SpotGrade;

    /* Spot constractor */
    public Spot() {
        this.listSpot = new ArrayList();
    }

    /* Function calculating SpotGrade
        Inputs; Non.
        Processing; Function running through all the spot nd calculating it's rows and cols, then it calculates,
    it's grade depending of it's distance from the middle.*/
    public void setGrade() {
        double dRow = 0.0,
                dCol = 0.0;
        /* Calculation of the spot */
        for (int i = 0; i < listSpot.size(); i++) {
            dRow += ((Cell) listSpot.get(i)).m_Row;
            dCol += ((Cell) listSpot.get(i)).m_Col;
        }

        dRow /= listSpot.size();
        dCol /= listSpot.size();

        double dDisCol = distanceColCal(((Cell) listSpot.get(0)).m_Col, dCol);
        double dMaxDis = Math.sqrt(Math.pow(dCol, 2) + Math.pow((((Cell) listSpot.get(0)).m_Row - dRow), 2)); // Setting first Max.
        for (int i = 1; i < listSpot.size(); i++) {
            dDisCol = distanceColCal(((Cell) listSpot.get(i)).m_Col, dCol);
            double dis = Math.sqrt(Math.pow(dDisCol, 2) + Math.pow((((Cell) listSpot.get(i)).m_Row - dRow), 2));
            if (dis > dMaxDis) {
                dMaxDis = dis;
            }
        }
        if (dMaxDis == 0.0) {
            dMaxDis = 1.0; // Setting minimal grade.    
        }
        SpotGrade = listSpot.size() / dMaxDis;
        dDisCol = distanceColCal(10.0, dCol);
        double dCenterDis = Math.sqrt(Math.pow(dDisCol, 2) + Math.pow((5.0D - dRow), 2));
        if (dCenterDis == 0.0) {
            dCenterDis = 1.0;
        }
        SpotGrade += 2 * listSpot.size() / dCenterDis;
    }

    /* Calculating differently odds for cols. 
        Input; Two doubles: Col of list, and col given to calculate.
        Processing; Function calculates the cols the way it can be compared right with the rows.
     */
    private double distanceColCal(double listCol, double dCol) {
        if (Math.abs(listCol - dCol) % 2 == 0) {
            return Math.abs(listCol - dCol) / 2.0D;
        }
        return Math.abs(listCol - dCol);
    }

}
