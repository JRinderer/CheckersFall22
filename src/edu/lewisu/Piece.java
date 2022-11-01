package edu.lewisu;

public interface Piece {
    String name ="";
    String friendly_name = "";
    String color="";



    public String getName();
    public String getFriendly_name();
    public String getColor();

    public void setName(String name);
    public void setFriendly_name(String name);
    public void setColor(String color);

    public boolean Move(Board board, int x, int y);



}
