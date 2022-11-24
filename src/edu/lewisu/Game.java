package edu.lewisu;

import javax.sql.rowset.serial.SerialArray;
import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Game implements Serializable {
    Board board;
    Boolean GameOver=false;
    private Player red_player = null;
    private Player white_player = null;
    private Player current_player = null;
    private String board_str = "";
    ArrayList<Piece> game_pieces = new ArrayList<>();

    /*
    public static void main(String[] args){
        Game myGame = new Game();
        myGame.Play();
        /*
        myGame = Game.load_game();
        //myGame.Play();
        myGame.showBoard();


    }
         */

    public void setColors(String color){
        if(color.equals("R")){
            //System.out.println("RED RAN");
            this.red_player = new Player("R");
            this.red_player.setTurn(true);
        }else{
            this.white_player = new Player("W");
            this.red_player.setTurn(true);
        }
    }

    public boolean isColorAvail(String color){
        System.out.println(color);
        if(this.red_player.getColor().equals("") && color.equals("R")){
            System.out.println("RED");
            setColors(color);
            return true;
        }else if(this.white_player.getColor().equals("") && color.equals("W")){
            System.out.println("White");
            setColors(color);
            return true;
        }else{
            //System.out.println(this.red_player.getColor());
            //System.out.println(this.white_player.getColor());
            return false;
        }

    }

    public String processPlayerInput(String piece_name, String x_str, String y_str, String color, String other) {
        int x = -1;
        int y = -1;
        boolean validMove = false;
        String returnString = "";
        Piece pieceHolder= new RegularPiece();
        ArrayList<Player> players = new ArrayList<>();

        if (!x_str.equals("") && !y_str.equals("")) {
            x = Integer.parseInt(x_str);
            y = Integer.parseInt(y_str);
        }

        players.add(this.red_player);
        players.add(this.white_player);

        this.current_player = Player.get_player_turn(this.red_player,this.white_player);
        System.out.println("The current player color is : "  + this.current_player.getColor());

        if(other.equals("get_turn")){
            returnString = "player_turn:" + this.current_player.getColor();
            return returnString;
        }



        try{
            pieceHolder = this.FindPIece(piece_name);
        }catch (Exception ex){
            System.out.println("Here" + ex);
        }

        try {
            validMove = pieceHolder.Move(this.board, x, y);
            if (!validMove) {
                returnString = "error:Invalid_Move";
            } else {
                returnString = "success:Piece_moved";
                Player.flipTurn(players);
            }
        }catch (Exception ex){
            returnString = "error:Invalid_Move";
        }
        returnString = this.board.serializeBoard() + "," + returnString;
        System.out.println(returnString);
        return returnString;
    }

    public void Start(String player1_color, String player2_color){
        if(player1_color.equals("R") && !player2_color.equals("")){
            this.red_player = new Player(player1_color);
            this.white_player = new Player(player2_color);
        }else if(player2_color.equals("R") && !player1_color.equals("")){
            this.red_player = new Player(player2_color);
            this.white_player = new Player(player1_color);
        }else if(player1_color.equals("") && player2_color.equals("")){
            this.red_player = new Player("");
            this.white_player = new Player("");
        }
        //sets our default starting turns
        this.red_player.setTurn(true);
        this.white_player.setTurn(false);
        //fills in our current player is the red player
        this.current_player = this.red_player;
        this.board = new Board();
        this.game_pieces = this.board.showBoard();

    }
    public void showBoard(){
        this.board.showBoard();
    }

    public Piece FindPIece(String pieceName) throws InterruptedException{
        Piece returnPiece=null;

        for(Piece p : this.game_pieces){
            if(Objects.isNull(p.getFriendly_name())){
                System.out.println("The piece doesn't exist");
                TimeUnit.SECONDS.sleep(3);
            }
            if(p.getFriendly_name().equals(pieceName)){
                System.out.println("Found piece!");
                returnPiece = p;
            }
        }
        return returnPiece;

    }

    public void Play(){
        //Game myGame = new Game();

        this.board = new Board();
        this.game_pieces = this.board.showBoard();


        int x_cord=-1;
        int y_cord=-1;
        boolean valid_move = false;
        Piece piece_in_play=null;
        String piece_str= "";
        Scanner my_scanner = new Scanner(System.in);
        //this.save_game();

        //while the game is not over, continue to get user input
        //and play the game.
        while(!this.GameOver){
            //get piece
            System.out.println("Enter a piece name:");
            piece_str = my_scanner.nextLine();
            try{
                piece_in_play = this.FindPIece(piece_str);
            }catch (Exception ex){
                System.out.println("Error in null piece");
            }
            //ensure user enters a valid piece.
            while(Objects.isNull(piece_in_play)){
                System.out.println("Enter a piece name:");
                piece_str = my_scanner.nextLine();
                try{
                    piece_in_play = this.FindPIece(piece_str);
                }catch (Exception ex){
                    System.out.println("Error in null piece");
                }
            }
            //get x & y coordinates
            try{
                System.out.println("Enter an X coordinate:");
                x_cord = my_scanner.nextInt();
                System.out.println("Enter an Y coordinate:");
                y_cord = my_scanner.nextInt();
                valid_move = piece_in_play.Move(this.board,x_cord,y_cord);

                //only switch turns if the user provides a valid piece and
                // valid x y coordinates
            }catch (Exception ex){
                System.out.println("That is invalid try again");
            }
            this.game_pieces = this.board.showBoard();
        }
    }

    public String seralizeBoard(){
        return this.board.serializeBoard();
    }

    public void save_game(){
        try {
            FileOutputStream st = new FileOutputStream("game.bin");
            ObjectOutputStream ot = new ObjectOutputStream(st);
            ot.writeObject(this);
            ot.close();
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
    public static Game load_game() {
        Game myGame = new Game();
        try {
            FileInputStream st = new FileInputStream("game.bin");
            ObjectInputStream ot = new ObjectInputStream(st);
            myGame = (Game) ot.readObject();
        } catch (Exception ex) {
            System.out.println("Error");
        }
        return myGame;
    }
}
