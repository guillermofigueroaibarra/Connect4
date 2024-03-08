import javax.swing.*;
import java.util.Random;
import java.util.Scanner;

public class Main {

    
    public static void main(String[] args) {
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    new GUI();
                }
            });


            // AI implementation on Terminal only
            Game game = new Game();
            game.startGame();


    }


}
