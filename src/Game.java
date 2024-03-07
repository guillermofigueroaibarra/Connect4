
import java.util.Random;
import java.util.Scanner;








public class Game {

    private Board board;
    private String color1 = "R";
    private String color2 = "Y";


    private boolean playingFirst;




    // return true/false if playing first are red pieces
    public boolean isPlayingFirst() {
        return playingFirst;
    }





    public Game() {
        this.board = new Board();
        playingFirst = (new Random().nextBoolean()); // use random to determine which player starts the game

    }





    public void reset(){
        // this function will reset the game if user decides to play another round
        this.board = new Board();
        playingFirst = (new Random()).nextBoolean();
    }


    public boolean checkWinner(int column) {
        String winnerColor;
        if (playingFirst) {
            winnerColor = color1;
        } else {
            winnerColor = color2;
        }
        return board.checkWinner(column, winnerColor);

    }




    public boolean checkWinnerGUI(int column) {
       // this function will check for a winner in GUI
        String winnerColor;
        // inverting
        if (!playingFirst) {
            winnerColor = color1;
        } else {
            winnerColor = color2;
        }
        return board.checkWinner(column, winnerColor);

    }


    public int round(int col){
        // this function parameter is the column where piece will be added
        // this function will return -1 if unsuccessfully or integer of column if it was successful
      int row  = -1;
        String color = playingFirst ? color1:color2;
        row= board.addPiece(col, color);
        if (row != -1)playingFirst = !playingFirst; // invert player so is the other player's turn
        return row;
    }














    public  void singleGame() {
        // this function will run a single entire Game
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
            int successfullyAdded = board.addPiece(column, color);
            if (successfullyAdded != -1) {
                if (checkWinner(column)) { // if winner found, board will print and gameRunning variable is set to false
                    board.printBoard();
                    gameRunning = false;

                    // find out who won
                    if (playingFirst) {
                        System.out.println("Player Number 1 won");
                    } else {
                        System.out.println("player Number 2 won");
                    }


                }

                playingFirst = !playingFirst; // invert player so is the other player's turn


            }
        }

    }


    private static int validateInput() {
        // This function validates user input and loops until user enters valid input
        System.out.println("Enter a column number:");
        Scanner scanner = new Scanner(System.in);
        int userInput;

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
        Scanner scanner = new Scanner(System.in);
       String i;
        do{

            // play a single game and ask user if they would like to play again
            // if users input is "yes" the loop will continue and the reset function will reset the board
            singleGame();
            System.out.println("Would you like to play again?");
            i = scanner.nextLine();
            reset();
        }
        while (i.equals("yes"));
        scanner.close();
        System.out.println("Game is over");
    }
}

