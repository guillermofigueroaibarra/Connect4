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

/*
    private int validateInput(String[] args){
        Scanner scanner = new Scanner(System.in);
        int userInput = 0;
        int inputReturn;

        while (true) {
            try {
                System.out.print("Enter column Number: ");
                userInput = Integer.parseInt(scanner.nextLine());

                // Your validation condition
                if (userInput >= 1 && userInput <= 7) {

                    break; // Break out of the loop if input is valid

                } else {
                    System.out.println("Please enter a number between 1 and 7.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number between 1 and 7.");
            }
        }return userInput;
    }
*/

    public boolean checkWinner(int column){
        String winnerColor;
        if(is1playing) {
            winnerColor = color1;
        }else{
            winnerColor = color2;
        }


        return board.checkWinner(column, winnerColor);

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


            System.out.println("Enter a column number:");
            Scanner input = new Scanner(System.in);



            int column = input.nextInt() - 1;
            boolean success = board.addPiece(column, color);
            if (success) {
                if (checkWinner(column)){
                    board.printBoard();
                    running = false;
                    if(is1playing) {
                        System.out.println("Player Number 1 won");
                    }else{
                        System.out.println("player Number 2 won");
                    }

            }

                is1playing = !is1playing; // invert player


            }
        }

    }


}

