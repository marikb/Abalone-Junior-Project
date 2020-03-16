package abalonegame;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import javax.swing.ImageIcon;

public class GameFrame extends javax.swing.JFrame {

    /* Decleration of class values */
    public Board m_Board;
    int Dir;
    boolean Start;
    public static Point m_Start = new Point(100, 60);
    Image EmptyBoard = null, BarImage, Turn;
    public int wBoard, hBoard;

    /**
     * Creates new form GameFrame
     */
    public GameFrame() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        Start = false; // Setting to not started yet.
        
        /* Images for tools in Project Folder */
        EmptyBoard = getToolkit().createImage("Board.jpg");
        BarImage = getToolkit().createImage("Bar.png");
        Turn = getToolkit().createImage("Red.png");

        /* Images for Buttons in Project Folder */
        UpRightBut.setIcon(new ImageIcon("ru.png"));
        UpLeftBut.setIcon(new ImageIcon("lu.png"));
        RightBut.setIcon(new ImageIcon("r.png"));
        LeftBut.setIcon(new ImageIcon("l.png"));
        DownRightBut.setIcon(new ImageIcon("rd.png"));
        DownLeftBut.setIcon(new ImageIcon("ld.png"));
    }

    /* Starting game */
    public void NewGame(int Num) {
        Start = true;
        Turn = getToolkit().createImage("Red.png"); // Red starts.
        m_Board = new Board(this, m_Start, Num); // Creating the board.
        repaint();
    }

    @Override
    public void paint(Graphics g) {
        wBoard = 522;
        hBoard = 462;
        g.drawImage(EmptyBoard, m_Start.x, m_Start.y, wBoard, hBoard, this);

        int wBar = 241; 
        int hBar = 39;    

        g.drawImage(BarImage, m_Start.x + wBoard + 20, m_Start.y, wBar, hBar, this);
        g.drawImage(BarImage, m_Start.x + wBoard + 20, m_Start.y + 100, wBar, hBar, this);
        if (Start) {
            m_Board.Draw(g);
            g.drawImage(Turn, m_Start.x - 80, m_Start.y + 450, Cell.CellSizeW, Cell.CellSizeH, this);
        }
        this.jMenuBar1.updateUI();
        this.jMenu1.updateUI();
        this.LeftBut.updateUI();
        this.RightBut.updateUI();
        this.DownLeftBut.updateUI();
        this.DownRightBut.updateUI();
        this.UpLeftBut.updateUI();
        this.UpRightBut.updateUI();
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        NetWaitDialog = new javax.swing.JDialog();
        jLabel2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        UpLeftBut = new javax.swing.JButton();
        UpRightBut = new javax.swing.JButton();
        LeftBut = new javax.swing.JButton();
        RightBut = new javax.swing.JButton();
        DownLeftBut = new javax.swing.JButton();
        DownRightBut = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        NewMenu = new javax.swing.JMenu();
        PlayerMenuItem = new javax.swing.JMenuItem();
        CompMenuItem = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JSeparator();
        ExitItem = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        About = new javax.swing.JMenuItem();

        NetWaitDialog.setModal(true);

        jLabel2.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel2.setText("Wait  to second  player");

        org.jdesktop.layout.GroupLayout NetWaitDialogLayout = new org.jdesktop.layout.GroupLayout(NetWaitDialog.getContentPane());
        NetWaitDialog.getContentPane().setLayout(NetWaitDialogLayout);
        NetWaitDialogLayout.setHorizontalGroup(
            NetWaitDialogLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(NetWaitDialogLayout.createSequentialGroup()
                .add(28, 28, 28)
                .add(jLabel2)
                .addContainerGap(25, Short.MAX_VALUE))
        );
        NetWaitDialogLayout.setVerticalGroup(
            NetWaitDialogLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(NetWaitDialogLayout.createSequentialGroup()
                .add(23, 23, 23)
                .add(jLabel2)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                formMousePressed(evt);
            }
        });
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                formComponentResized(evt);
            }
        });

        UpLeftBut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UpLeftButActionPerformed(evt);
            }
        });

        UpRightBut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UpRightButActionPerformed(evt);
            }
        });

        LeftBut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LeftButActionPerformed(evt);
            }
        });

        RightBut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RightButActionPerformed(evt);
            }
        });

        DownLeftBut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DownLeftButActionPerformed(evt);
            }
        });

        DownRightBut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DownRightButActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(UpLeftBut, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 25, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(UpRightBut, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 25, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(LeftBut, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 25, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(RightBut, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 25, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(DownLeftBut, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 25, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(DownRightBut, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 25, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(68, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .add(90, 90, 90)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(UpLeftBut, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 20, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(UpRightBut, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 20, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(LeftBut, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 20, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(RightBut, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 20, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(DownLeftBut, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 20, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(DownRightBut, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 20, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jMenu1.setText("Game");

        NewMenu.setIcon(new javax.swing.ImageIcon("C:\\Users\\borod\\OneDrive\\Documents\\NetBeansProjects\\Abalone\\Selected.JPG")); // NOI18N
        NewMenu.setText("New");

        PlayerMenuItem.setText("vs Player");
        PlayerMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PlayerMenuItemActionPerformed(evt);
            }
        });
        NewMenu.add(PlayerMenuItem);

        CompMenuItem.setText("vs Computer");
        CompMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CompMenuItemActionPerformed(evt);
            }
        });
        NewMenu.add(CompMenuItem);

        jMenu1.add(NewMenu);
        jMenu1.add(jSeparator1);

        ExitItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_ESCAPE, 0));
        ExitItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/abalonegame/Black.JPG"))); // NOI18N
        ExitItem.setText("Exit");
        ExitItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExitItemActionPerformed(evt);
            }
        });
        jMenu1.add(ExitItem);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Help");

        About.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_H, 0));
        About.setText("About");
        About.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AboutActionPerformed(evt);
            }
        });
        jMenu2.add(About);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(787, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(85, 85, 85)
                .add(jPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(239, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void CompMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CompMenuItemActionPerformed
        NewGame(1);
        repaint();
    }//GEN-LAST:event_CompMenuItemActionPerformed

    private void PlayerMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PlayerMenuItemActionPerformed
        NewGame(0);
        repaint();
    }//GEN-LAST:event_PlayerMenuItemActionPerformed
    /* Moves Buttons */
    private void DownRightButActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DownRightButActionPerformed
        Dir = 3;
        m_Board.DoStep(Dir);

    }//GEN-LAST:event_DownRightButActionPerformed

    private void DownLeftButActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DownLeftButActionPerformed
        Dir = 4;
        m_Board.DoStep(Dir);

    }//GEN-LAST:event_DownLeftButActionPerformed

    private void RightButActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RightButActionPerformed
        Dir = 2;
        m_Board.DoStep(Dir);

    }//GEN-LAST:event_RightButActionPerformed

    private void LeftButActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LeftButActionPerformed

        Dir = 5;
        m_Board.DoStep(Dir);

    }//GEN-LAST:event_LeftButActionPerformed

    private void UpRightButActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UpRightButActionPerformed
        Dir = 1;
        m_Board.DoStep(Dir);

    }//GEN-LAST:event_UpRightButActionPerformed

    private void UpLeftButActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UpLeftButActionPerformed

        Dir = 0;
        m_Board.DoStep(Dir);

    }//GEN-LAST:event_UpLeftButActionPerformed

    private void formMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMousePressed
        if (m_Board != null) {
            m_Board.Click(evt.getPoint());
            repaint();
        }
    }//GEN-LAST:event_formMousePressed

    private void ExitItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExitItemActionPerformed
        System.exit(0);
    }//GEN-LAST:event_ExitItemActionPerformed

    private void formComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentResized
        repaint();
    }//GEN-LAST:event_formComponentResized

    private void AboutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AboutActionPerformed

        AboutFrame f = new AboutFrame();
        f.setVisible(true);
    }//GEN-LAST:event_AboutActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GameFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem About;
    private javax.swing.JMenuItem CompMenuItem;
    private javax.swing.JButton DownLeftBut;
    private javax.swing.JButton DownRightBut;
    private javax.swing.JMenuItem ExitItem;
    private javax.swing.JButton LeftBut;
    public javax.swing.JDialog NetWaitDialog;
    private javax.swing.JMenu NewMenu;
    private javax.swing.JMenuItem PlayerMenuItem;
    private javax.swing.JButton RightBut;
    private javax.swing.JButton UpLeftBut;
    private javax.swing.JButton UpRightBut;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    // End of variables declaration//GEN-END:variables

}
