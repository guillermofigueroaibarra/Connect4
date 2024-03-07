
import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import javax.swing.*;

/** Test setting Swing's JComponents properties and appearances */
@SuppressWarnings("serial")
public class GUI extends JFrame {


    private Container cp;
    private Game game;

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



    private void updateBoard(JButton button){
        int row10plusCol = Integer.parseInt(button.getName());
        int col = row10plusCol % 10;
        int addedRow = game.round(col);
        if(addedRow != -1){
            JButton buttonUpdate = ((JButton)(cp.getComponent(columns * addedRow + col)));
            // inverting players as round ended
            if(game.isPlayingFirst()) buttonUpdate.setIcon(iconYellow);
            else buttonUpdate.setIcon(iconRed);
            if(game.checkWinnerGUI(col)) JOptionPane.showMessageDialog(null, "You Won!");
        } else{
           JOptionPane.showMessageDialog(null, "Please enter a valid position number!");
        }

    }




    public GUI() {
        game = new Game();

        // set up color icons

        // empty icon, if empty icon image is not found, error message is to be displayed
        URL imgURL = getClass().getClassLoader().getResource(imgEmptyFilename);
        if(imgURL != null) iconEmpty = new ImageIcon(imgURL);
        else System.err.println("Could not find file: " + imgEmptyFilename);

        // red icon, if empty icon image is not found, error message is to be displayed
        imgURL = getClass().getClassLoader().getResource(imgRedFilename);
        if(imgURL != null) iconRed = new ImageIcon(imgURL);
        else System.err.println("Could not find file: " + imgRedFilename);

        // yellow icon, if empty icon image is not found, error message is to be displayed
        imgURL = getClass().getClassLoader().getResource(imgYellowFilename);
        if(imgURL != null) iconYellow = new ImageIcon(imgURL);
        else System.err.println("Could not find file: " + imgYellowFilename);


        cp = getContentPane();
        cp.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));




        for(int row = 0; row < rows; row++)
            for(int col=0; col < columns; col++){
                JButton button = new JButton();
                button.setIcon(iconEmpty);
                button.setPreferredSize(new Dimension(100, 100));
                button.setName(Integer.toString((row*10+col)));
               button.addActionListener(new ActionListener() {
                   @Override
                   public void actionPerformed(ActionEvent actionEvent) {
                       updateBoard(((JButton)(actionEvent.getSource())));
                   }
               });
               cp.add(button);
            }



        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Connect 4"); // window title
        setLocationRelativeTo(null); // center window on the screen
        setSize(widthWindow, heightWindow);
        setVisible(true);


    }



}