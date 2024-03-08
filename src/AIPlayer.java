import java.util.Random;


    public class AIPlayer {

        public static int makeMove() {
            Random random = new Random();
            return random.nextInt(Board.getColumns()) + 1; // Generate a random move between 1 and the number of columns
        }
}
