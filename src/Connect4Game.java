import java.util.Random;

public class Connect4Game {

    private Board board;
    private String color1;
    private String color2;


    // true if players turn
    private boolean is1playing;


    //false if players 2 turn


    public Connect4Game(String  color1, String color2){
        this.board = new Board();
        this.color1 = color1;
        this.color2 = color2;
        is1playing = (new Random().nextBoolean());

    }


    public void startGame(){
        boolean running = true;

        while (running){
            String color;
            if(is1playing) {
                color = color1;
            }else {
                color = color2;

            }
            is1playing = !is1playing;
        }


    }



}
