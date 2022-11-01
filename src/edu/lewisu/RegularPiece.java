package edu.lewisu;

import java.io.Serializable;

public class RegularPiece implements Piece, Serializable {
    private String name;
    private String friendly_name;
    private String color;
    private int x;
    private int y;
    private int forward;

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

    public boolean Move(Board b, int x, int y) {
        boolean ret_value = false;
            if ((Math.abs(y - this.y)) == 1 && (x - this.x==this.forward) && b.isSquareEmpty(x, y)) {
                b.setPieceOnSpace(this, x, y);
                b.removePieceOnSpace(this.x, this.y);
                this.x = x;
                this.y = y;
                ret_value = true;
            }
        return ret_value;
    }

    public RegularPiece() {

    }
    public RegularPiece(String color, int x, int y){
        this.color=color;
        this.x=x;
        this.y = y;
        this.setName("__"+ color +  x + "-" + y + "_" );
        if(color.equals("R")){
            this.forward = 1;
        }else{
            this.forward = -1;
        }
    }

    public RegularPiece(int x, int y, String color){
        this.color=color;
        this.x=x;
        this.y = y;
        this.setName("__" + x + "-" + y + "__");
    }
}
