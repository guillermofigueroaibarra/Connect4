public class Main {
    public static void main(String[] args) {

        Board boardGame = new Board();
        boardGame.printBoard();
        System.out.println();
        boardGame.addPiece(0, "X");
        boardGame.addPiece(0, "O");
        boardGame.addPiece(0, "X");
        boardGame.addPiece(0, "X");
        boardGame.addPiece(0, "X");
        boardGame.addPiece(0, "X");
        System.out.println("....d");
        boardGame.printBoard();



    }
}