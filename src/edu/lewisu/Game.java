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
    ArrayList<Piece> game_pieces = new ArrayList<>();

    public static void main(String[] args){
        Game myGame = new Game();
        myGame.Play();
        /*
        myGame = Game.load_game();
        //myGame.Play();
        myGame.showBoard();

         */
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
