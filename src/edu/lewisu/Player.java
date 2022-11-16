package edu.lewisu;

import java.util.ArrayList;

public class Player {
    private String color;
    private Boolean turn=false;
    private Boolean won=false;
    private ArrayList<Piece> player_pieces;

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Boolean getTurn() {
        return turn;
    }

    public static void flipTurn(ArrayList<Player> players){
        for(Player plyr : players){
            plyr.setTurn(!plyr.isTurn());
        }
    }

    public void flipTurn(Player player_not_turn){
        this.setTurn(false);
        player_not_turn.setTurn(true);
    }

    public void setTurn(Boolean turn) {
        this.turn = turn;
    }
    public boolean isTurn() {
        return turn;
    }

    public static Player get_player_turn(Player p1, Player p2){
        if(p1.isTurn()){
            return p1;
        }else{
            return p2;
        }
    }

    public Boolean getWon() {
        return won;
    }

    public void setWon(Boolean won) {
        this.won = won;
    }

    public ArrayList<Piece> getPlayer_pieces() {
        return player_pieces;
    }

    public void setPlayer_pieces(ArrayList<Piece> player_pieces) {
        this.player_pieces = player_pieces;
    }

    public Player(){

    }

    public Player(String color){
        this.color = color;
    }
}
