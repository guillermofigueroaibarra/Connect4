
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

    public boolean checkWinner(int col, String winnerColor){
        boolean somebodyWon = false;
        for(int row = 0; row < rows; row++){
            if(GameBoard[row][col] != null){
                int winningStreak = 3;


                // check for winner in downward direction
                for(int winRow = row + 1; winRow < rows; winRow++){
                    if(GameBoard[winRow][col].getColor().equals(winnerColor)){
                        winningStreak--;
                        if(winningStreak == 0){
                            somebodyWon = true;
                        }
                    } else {
                        winningStreak = 3;
                    }
                }

                winningStreak = 4;


                // check for winner horizontally
                for(int winCol = col - 3; winCol <= col + 3; winCol++){
                    if(winCol < 0) continue;
                    if(winCol >= columns)  break;
                    if(GameBoard[row][winCol] != null && GameBoard[row][winCol].getColor().equals(winnerColor)) {
                        winningStreak--;
                        if(winningStreak == 0){
                            somebodyWon = true;
                        }
                    }else{
                        winningStreak = 4; // reset
                    }
                }
                winningStreak = 4;



                // check for winner left diagonally
                for(int winRow = row - 3, winCol = col - 3; winRow <= row + 3 && winCol <= col +3; winRow++, winCol++){
                    if(winRow < 0 || winCol < 0)continue;
                    if(winRow >= rows || winCol >= columns) break;
                    if(GameBoard[winRow][winCol] != null && GameBoard[winRow][winCol].getColor().equals(winnerColor)) {
                        winningStreak--;
                        if(winningStreak == 0){
                            somebodyWon = true;
                        }
                    }else{
                        winningStreak = 4; // reset
                    }
                }
                winningStreak = 4;


                // check for winner right diagonally
                for(int winRow = row - 3, winCol = col + 3; winRow <= row + 3 && winCol >= col - 3; winRow++, winCol--){
                    if(winRow < 0 || winCol>= columns)continue;
                    if(winRow >= rows || winCol < 0) break;
                    if(GameBoard[winRow][winCol] != null && GameBoard[winRow][winCol].getColor().equals(winnerColor)) {
                        winningStreak--;
                        if(winningStreak == 0){
                            somebodyWon = true;
                        }
                    }else{
                        winningStreak = 4; // reset

                    }

                }

                break;
            }
        }
        return somebodyWon;
    }


    // add piece to the board method
    public boolean addPiece(int addToColumn, String color){
        if(addToColumn >= 0 && addToColumn < columns ){ // if the integer given by user is in the columns range, then add
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
            } else {
                System.err.println("this column is full, please choose another one"); // error message if colum is full
                return false;

            }

        }else{

            return false;
        }

    }




    public void printBoard(){
        for(int col = 0; col < columns + 2; col++) System.out.print("--");
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
        for(int col = 0; col < columns + 2; col++) System.out.print("--");
        System.out.println();
    }

    public Board(){
        for(int row = 0; row< rows; row++){
            for(int col = 0; col < columns; col++){
                GameBoard[row][col] = null;
            }
        }
    }








}


