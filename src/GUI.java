
import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import java.util.Scanner;
import javax.swing.*;

/** Test setting Swing's JComponents properties and appearances */
@SuppressWarnings("serial")
public class GUI extends JFrame {


    private Container window;
    private Game game;

    private String yellowTitle = "Connect 4 --- YELLOW PIECE TURN";
    private String redTitle = "Connect 4 --- RED PIECE TURN";


    int rows = 6; // make rows constant
    int columns = 7; //make columns constant



    // variables to set window width and height
    int widthWindow = 750;
    int heightWindow = 650;




    // images path
    private String imgEmptyFilename = "empty.png";
    private String imgRedFilename = "red.png";
    private String imgYellowFilename = "yellow.png";

   private ImageIcon iconEmpty = null;
    private ImageIcon iconRed = null;
    private ImageIcon iconYellow = null;




    public void resetBoard() {
        int reply = JOptionPane.showConfirmDialog(null, "Do you like to play again?", null, JOptionPane.YES_NO_OPTION);
        if(reply == JOptionPane.YES_OPTION) {
            game.reset();
            for(int row = 0; row < rows; row++)
                for (int col = 0; col < columns; col++)
                    ((JButton)(window.getComponent(columns * row + col))).setIcon(iconEmpty);
        } else {
            System.exit(0);

        }


    }


    private void updateBoard(JButton button){
        int row10plusCol = Integer.parseInt(button.getName());
        int col = row10plusCol % 10;



        // find who is the next color piece to play and display it as the window title
        boolean playerTurn = game.isPlayingFirst();
        if(!playerTurn) setTitle(redTitle);
        else setTitle(yellowTitle);


        int addedRow = game.round(col);
        if(addedRow != -1){
            JButton buttonUpdate = ((JButton)(window.getComponent(columns * addedRow + col)));
            // inverting players as round ended
            if(game.isPlayingFirst()) buttonUpdate.setIcon(iconYellow);
            else buttonUpdate.setIcon(iconRed);
            if(game.checkWinnerGUI(col)) {
                JOptionPane.showMessageDialog(null, "You Won");
                resetBoard();
            }
        } else{
           JOptionPane.showMessageDialog(null, "Please enter a valid position number!");
        }

    }






    public GUI() {



            game = new Game();

            // set up color icons

            // empty icon, if empty icon image is not found, error message is to be displayed
            URL imgURL = getClass().getClassLoader().getResource(imgEmptyFilename);
            if (imgURL != null) iconEmpty = new ImageIcon(imgURL);
            else System.err.println("Could not find file: " + imgEmptyFilename);

            // red icon, if empty icon image is not found, error message is to be displayed
            imgURL = getClass().getClassLoader().getResource(imgRedFilename);
            if (imgURL != null) iconRed = new ImageIcon(imgURL);
            else System.err.println("Could not find file: " + imgRedFilename);

            // yellow icon, if empty icon image is not found, error message is to be displayed
            imgURL = getClass().getClassLoader().getResource(imgYellowFilename);
            if (imgURL != null) iconYellow = new ImageIcon(imgURL);
            else System.err.println("Could not find file: " + imgYellowFilename);


            window = getContentPane();
            window.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));


            for (int row = 0; row < rows; row++)
                for (int col = 0; col < columns; col++) {
                    JButton button = new JButton();
                    button.setIcon(iconEmpty);
                    button.setPreferredSize(new Dimension(100, 100));
                    button.setName(Integer.toString((row * 10 + col)));
                    button.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent actionEvent) {
                            updateBoard(((JButton) (actionEvent.getSource())));
                        }
                    });
                    window.add(button);
                }

            
             /*

        JTabbedPane tabPanel = new JTabbedPane(JTabbedPane.LEFT);


        // Create the first tab (page1) and add a JLabel to it
        JPanel page1 = new JPanel();
        page1.add(new JLabel("This is Tab 1"));

        // Create the second tab (page2) and add a JLabel to it
        JPanel page2 = new JPanel();
        page2.add(new JLabel("This is Tab 2"));

        // Create the third tab (page3) and add a JLabel to it
        JPanel page3 = new JPanel();
        page3.add(new JLabel("This is Tab 3"));

        // Add the three tabs to the JTabbedPane
        tabPanel.addTab("Tab 1", page1);
        tabPanel.addTab("Tab 2", page2);
        tabPanel.addTab("Tab 3", page3);

        // Add the JTabbedPane to the JFrame's content
        window.add(tabPanel);
         */

       




            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // find who is the first color piece to play and display it as the window title
             boolean playerTurn = game.isPlayingFirst();
            if(playerTurn) setTitle(redTitle);
            else setTitle(yellowTitle);


            setLocationRelativeTo(null); // center window on the screen
            setSize(widthWindow, heightWindow);
            setVisible(true);







    }
}