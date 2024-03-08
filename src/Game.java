
import java.util.Random;
import java.util.Scanner;








public class Game {

    private Board board;
    private String color1 = "R";
    private String color2 = "Y";

    private AIPlayer aiPlayer; // Add AIPlayer instance
    private boolean playingFirst;




    // return true/false if playing first are red pieces
    public boolean isPlayingFirst() {
        return playingFirst;
    }





    public Game() {
        this.board = new Board();
        this.aiPlayer = new AIPlayer(); // Initialize AIPlayer instance
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






    public void singleGame() {
        boolean gameRunning = true;
        while (gameRunning) {
            board.printBoard();
            String color;
            int column;

            if (playingFirst) {
                color = color1;
                System.out.println("Player 1's turn");
                column = validateInput();
            } else {
                color = color2;
                System.out.println("AI's turn");
                column = aiPlayer.makeMove();
                System.out.println("AI chose column " + (column + 1)); // Adjust for 0-based index
            }

            boolean successfullyAdded = board.addPieceAI(column, color);

            if (successfullyAdded) {
                if (checkWinner(column)) {
                    board.printBoard();
                    gameRunning = false;

                    if (playingFirst) {
                        System.out.println("Player Number 1 won");
                    } else {
                        System.out.println("AI won");
                    }
                }

                playingFirst = !playingFirst;
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

