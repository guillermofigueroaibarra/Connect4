import javax.swing.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {



        Scanner scanner = new Scanner(System.in);
        String i;



        do {

            SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GUI();

            }
        });


            System.out.println("Would you like to play again?");
            i = scanner.nextLine();
        }while(i.equals("yes"));
        scanner.close();
    }
}
