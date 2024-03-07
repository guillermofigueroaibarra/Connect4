
import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import java.util.Random;
import java.util.Scanner;
import javax.swing.*;

/** Test setting Swing's JComponents properties and appearances */
@SuppressWarnings("serial")
public class GUI extends JFrame {


    private Container window;
    private Game game;

    private String yellowTitle = "Connect 4 --- YELLOW PIECE TURN";
    private String redTitle = "Connect 4 --- RED PIECE TURN";

    private String winRed = "RED PIECES WON!";
    private String winYellow = "YELLOW PIECES WON!";
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


    int ranNum = new Random().nextInt(1, 7);
    int ranNumr = new Random().nextInt(1, 6);

    public void resetBoard() {
        /*
        this function resets the Board and switches the images to empty spaces,
        When a winner is found, a pop up window will appear asking user if they want to play again
        if user says yes, the board will be reset it and images will switch to empty, otherwise it'll close the window
         */
        int reply = JOptionPane.showConfirmDialog(null, "Would you like to play again?", null, JOptionPane.YES_NO_OPTION);
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
        String winner;

        // find who is the next color piece to play and display it as the window title
        boolean playerTurn = game.isPlayingFirst();

        // update window titles and set winner message to its respective winner
        if(!playerTurn) {
            setTitle(redTitle);
            winner = winYellow;
        }else {
            setTitle(yellowTitle);
            winner = winRed;
        }

        int addedRow = game.round(col); // check if piece can be successfully added
        if(addedRow != -1){ // if piece was added then run the following code
            JButton buttonUpdate = ((JButton)(window.getComponent(columns * addedRow + col)));
            // inverting players as round ended
            if(game.isPlayingFirst()) buttonUpdate.setIcon(iconYellow);
            else buttonUpdate.setIcon(iconRed);

            if(game.checkWinnerGUI(col)) {
                JOptionPane.showMessageDialog(null, winner);
                resetBoard();
            }
            // if the piece is not successfully added with the "round" function, then an error message is to be displayed
        } else{
           JOptionPane.showMessageDialog(null, "Please enter valid unoccupied column number!");
        }


    }






    public GUI() {

            // create a Game object
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



            window = getContentPane(); // create window container
            window.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0)); // window position


            // creates the grid with board empty spaces
            // assigns a name to each space
            for (int row = 0; row < rows; row++)
                for (int col = 0; col < columns; col++) {
                    JButton button = new JButton();     // create a new button
                    button.setIcon(iconEmpty);          // set the empty icon to the new button created
                    button.setPreferredSize(new Dimension(100, 100)); // button size
                    button.setName(Integer.toString((row * 10 + col))); // assigned a name to the button depending on its location in the grid
                    // button name will be determined by the row * 10 + col and this integer will then be converted to the string that will be the buttons name/label



                    // if one of the buttons of the grid is clicked, the board will update
                    button.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent actionEvent) {
                            updateBoard(((JButton) (actionEvent.getSource())));
                        }
                    });
                    window.add(button);
                }

            

            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

             // find who is the first color piece to play and display it as the window title
            boolean playerTurn = game.isPlayingFirst();
            if(playerTurn) setTitle(redTitle);
            else setTitle(yellowTitle);


            setLocationRelativeTo(null); // center window on the screen
            setSize(widthWindow, heightWindow); // window size
            setVisible(true);







    }


}