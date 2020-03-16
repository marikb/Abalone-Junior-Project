package abalonegame;

/* Artificial intelligence class to promise the moves won't be done at same time. */
public class AI extends Thread {

    ComputerPlayer Comp;

    /* AI Constractor */
    public AI(ComputerPlayer comp) {
        this.Comp = comp;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(1000L); // Suspends the thread for 1000 miliseconds.
        } catch (InterruptedException ex) {
        }
        Comp.DoStep();
    }
}
