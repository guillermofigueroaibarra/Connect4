import java.util.Random;
import java.util.Scanner;

public class Game {

    private Board board;
    private String color1 = "R";
    private String color2 = "Y";


    // true if players turn
    private boolean playingFirst;


   


    public Game() {
        this.board = new Board();
        this.color1 = color1;
        this.color2 = color2;
        playingFirst = (new Random().nextBoolean()); // use random to determine which player starts the game

    }


    public boolean checkWinner(int column){
        String winnerColor;
        if(playingFirst) {
            winnerColor = color1;
        }else{
            winnerColor = color2;
        }
        return board.checkWinner(column, winnerColor);

    }







    private static int validateInput() {
        // This function validates user input and loops until user enters valid input
        System.out.println("Enter a column number:");
        Scanner scanner = new Scanner(System.in);
        int userInput = 0;

        while (true) {
            try {
                System.out.print("Enter a row number between 1-7: ");
                userInput = Integer.parseInt(scanner.nextLine());

                //  validation condition
                if (userInput >= 1 && userInput <= 7) {
                    break; // Break out of the loop if input is valid
                } else {
                    System.out.println("Please enter a number between 1 and 7.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input Please enter a valid number between 1-7.");
            }
        }

        return userInput;
    }





    public void startGame() {

        boolean gameRunning = true;



        while (gameRunning) { // while gameRunning condition is true game will keep running
            board.printBoard();
            String color;
            if (playingFirst) {
                color = color1;
                System.out.println("Player 1's turn");
            } else {
                color = color2;
                System.out.println("Player 2's turn");

            }


            int userInput = validateInput(); // call function to read and validate users input


            int column = userInput - 1;
            boolean success = board.addPiece(column, color);
            if (success) {
                if (checkWinner(column)){ // if winner found, board will print and gameRunning will be set to falsa to end game
                    board.printBoard();
                    gameRunning = false;
                    if(playingFirst) {
                        System.out.println("Player Number 1 won");
                    }else{
                        System.out.println("player Number 2 won");
                    }

            }

                playingFirst = !playingFirst; // invert player so is the other player's turn


            }
        }

    }


}

