package edu.lewisu;

import javax.sql.rowset.serial.SerialArray;
import javax.swing.*;
import java.io.*;
import java.util.ArrayList;

public class Game implements Serializable {
    Board board;

    public static void main(String[] args){
        Game myGame = new Game();
        //myGame.Play();
        myGame = Game.load_game();
        //myGame.Play();
        myGame.showBoard();
    }

    public void showBoard(){
        this.board.showBoard();
    }

    public void Play(){
        //Game myGame = new Game();
        ArrayList<Piece> game_pieces = new ArrayList<>();
        this.board = new Board();
        game_pieces = this.board.showBoard();
        this.save_game();
    }

    public void save_game(){
        try {
            FileOutputStream st = new FileOutputStream("game.txt");
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
            FileInputStream st = new FileInputStream("game.txt");
            ObjectInputStream ot = new ObjectInputStream(st);
            myGame = (Game) ot.readObject();
        } catch (Exception ex) {
            System.out.println("Error");
        }
        return myGame;
    }
}
