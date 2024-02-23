
public class Board {




    private static final int rows = 6; // make rows constant
    private static final int columns = 7; //make columns constant


    Piece [][]GameBoard = new Piece[rows][columns]; 


    public static int getColumns() {
        return columns;
    }

    public static int getRows() {
        return rows;
    }



    // add piece to the board method
    public boolean addPiece(int addToColumn, String color){
        if(addToColumn >= 0 && addToColumn < columns){ // if the integer given by user is in the columns range, then add
            if(GameBoard[0][addToColumn]== null){ // check if spot in board is empty
                boolean wasPieceAdded = false;
                for (int row = rows - 1; row >= 0; row--){
                    if(GameBoard[row][addToColumn] ==null){
                        GameBoard[row][addToColumn] = new Piece();
                        GameBoard[row][addToColumn].setColor(color);
                        wasPieceAdded = true;
                        break;
                    }

                }
                return wasPieceAdded;
            } else { // otherwise column is full, error message to be displayed
                // row is full
                System.err.println("this column is full");
                return false;

            }

        }else{ // otherwise integer or input given is out of range, error message to be displayed
            System.err.println("This is out of the range, choose between  0 and 5");
            return false;
        }

    }



    public void printBoard(){
        for(int col = 0; col < columns + 2; col++) System.out.print("-");
        System.out.println();
        for(int row = 0; row< rows; row++){
            System.out.print("|");
            for(int col = 0; col < columns; col++){
                if(GameBoard[row][col] == null) {
                    System.out.print("_");
                } else {
                    System.out.print(GameBoard[row][col].getColor());
                }
                System.out.print("|");
            }
            System.out.println();
        }
        for(int col = 0; col < columns + 2; col++) System.out.print("-");
        System.out.println();
    }

    public Board(){
        for(int row = 0; row< rows; row++){
            for(int col = 0; col < columns; col++){
                GameBoard[row][col] = null;
            }
        }
    }




/*
    // After the piece is dropped, it will check in all directions for 3 more consecutive pieces
    public boolean checkWinner() {
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                char piece = Board[row][col];
                if (piece != empty) {
                    // Check horizontally (left and right)
                    if (col + 3 < COLS &&
                            piece == board[row][col + 1] &&
                            piece == board[row][col + 2] &&
                            piece == board[row][col + 3]) {
                        return true;
                    }
                    // Check vertically (up and down)
                    if (row + 3 < ROWS &&
                            piece == board[row + 1][col] &&
                            piece == board[row + 2][col] &&
                            piece == board[row + 3][col]) {
                        return true;
                    }
                    // Check diagonally (positive slope)
                    if (row + 3 < ROWS && col + 3 < COLS &&
                            piece == board[row + 1][col + 1] &&
                            piece == board[row + 2][col + 2] &&
                            piece == board[row + 3][col + 3]) {
                        return true;
                    }
                    // Check diagonally (negative slope)
                    if (row - 3 >= 0 && col + 3 < COLS &&
                            piece == board[row - 1][col + 1] &&
                            piece == board[row - 2][col + 2] &&
                            piece == board[row - 3][col + 3]) {
                        return true;
                    }
                }
            }
        }
        return false;
    }*/




}


