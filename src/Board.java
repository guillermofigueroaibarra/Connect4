
public class Board {




    private static int rows = 6;
    private static int columns = 7;


    Piece [][]GameBoard = new Piece[rows][columns];




    // add piece to the board method
    public boolean addPiece(int colToAdd, String color){
        if(colToAdd >= 0 && colToAdd < columns){
            // we can add

            if(GameBoard[colToAdd][0] == null){
                boolean addedThePiece = false;
                for (int row = rows - 1; row >= 0; row--){
                    if(GameBoard[row][colToAdd] ==null){
                        GameBoard[row][colToAdd] = new Piece();
                        GameBoard[row][colToAdd].setColor(color);
                        addedThePiece = true;
                        break;
                    }

                }
                return addedThePiece;
            } else {
                // row is full
                System.err.println("this column is full");
                return false;

            }

        }else{
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




}


