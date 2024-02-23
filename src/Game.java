import java.util.Random;
import java.util.Scanner;

public class Game {

    private Board board;
    private String color1;
    private String color2;


    // true if players turn
    private boolean is1playing;


    //false if players 2 turn


    public Game(String color1, String color2) {
        this.board = new Board();
        this.color1 = color1;
        this.color2 = color2;
        is1playing = (new Random().nextBoolean());

    }

    public int whoGoesFirst(){
        // this function will decide which player goes first
        Random rand = new Random();
        int whoGoesFirst = rand.nextInt(1,3);
        System.out.println(whoGoesFirst);
        return whoGoesFirst;

    }

    public void startGame() {
        boolean running = true;


        // turns
        while (running) {
            board.printBoard();
            String color;
            if (is1playing) {
                color = color1;
                System.out.println("Player 1's turn!");
            } else {
                color = color2;
                System.out.println("Player 2's turn");

            }


            System.out.println("Enter column number to put piece in");
            Scanner input = new Scanner(System.in);
            int column = input.nextInt();
            boolean success = board.addPiece(column, color);

            if (success) {
                is1playing = !is1playing; // invert player


            }
        }

    }


}

