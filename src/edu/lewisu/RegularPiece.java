package edu.lewisu;

import java.io.Serializable;

public class RegularPiece implements Piece, Serializable {
    private String name;
    private String friendly_name;
    private String color;

    public String getName(){
        return this.name;
    }
    public String getFriendly_name(){
        return this.friendly_name;
    }
    public String getColor(){
        return this.color;
    }

    public void setName(String name){
        this.name=name;
    }
    public void setFriendly_name(String name){
        this.friendly_name=name;
    }
    public void setColor(String color){
        this.color=color;
    }

    public RegularPiece() {

    }
}
