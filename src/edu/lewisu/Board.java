package edu.lewisu;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Board implements Serializable {
    ArrayList<Piece> redPieces = new ArrayList<>();
    ArrayList<Piece> whitePieces = new ArrayList<>();
    Square[][] squares = new Square[8][8];

    public Board(){
        this.setBoard();
    }

    private void setBoard(){
        for (int x=0;x<8;x++){
            for(int y = 0;y<8;y++){
                squares[x][y] = new Square();
            }
        }
        //with empty squares created we can begin adding pieces row by row.
        //create 12 red pieces
        ArrayList<RegularPiece> tempList = new ArrayList<>();
        for (int x = 0; x < 8; x++) {
            switch (x) {
                case 0:
                    setEvenCols(x, redPieces, "R",-1, -2);
                    break;
                case 1:
                    setOddCols(x,redPieces,"R",-1, -2);
                    break;
                case 2:
                    setEvenCols(x,redPieces,"R",-1, -2);

                    break;
                case 3: //empty rows
                    sentAllBlank(x);
                    break;
                case 4: //empty rows
                    sentAllBlank(x);
                    break;
                case 5: //white rows
                    setOddCols(x, whitePieces,"W",1, 2);
                    break;
                case 6: //white rows
                    setEvenCols(x,whitePieces,"W",1,2);
                    break;
                case 7: //white rows
                    setOddCols(x, whitePieces,"W",1,2);
                    break;
            }
        }
    }

    private void setOddCols(int row, ArrayList<Piece> pieces, String color, int move, int jump) {
        int pieceCounter = 0;
        for(int y = 0;y<8;y++){
            if(y%2==0){
                RegularPiece thisPiece = new RegularPiece();
                setPieceOnSpace(thisPiece,row,y);
                thisPiece.setName("__"+ color +  row + "-" + y + "_" );
                thisPiece.setFriendly_name(color + row + "-" + y);
                //thisPiece.setxCord(row);
                //thisPiece.setyCord(y);
                pieces.add(thisPiece);
            }
            else{
                RegularPiece emptyPiece = new RegularPiece();
                setPieceOnSpace(emptyPiece,row,y);
                emptyPiece.setName("__" + row + "-" + y + "__");
                emptyPiece.setFriendly_name("empty");
            }

        }
    }

    public void sentAllBlank(int row){
        for (int y=0;y<8;y++){
            RegularPiece emptyPiece = new RegularPiece();
            setPieceOnSpace(emptyPiece,row,y);
            emptyPiece.setName("__" + row + "-" + y + "__");
            emptyPiece.setFriendly_name("empty");
        }
    }


    private void setEvenCols(int row,ArrayList<Piece> pieces, String color, int move, int jump) {
        int pieceCounter = 0;
        for(int y = 0;y<8;y++){
            if(y%2!=0){
                RegularPiece thisPiece = new RegularPiece();
                setPieceOnSpace(thisPiece,row,y);
                thisPiece.setName("__"+ color +  row + "-" + y + "_" );
                thisPiece.setFriendly_name(color + row + "-" + y);
                //thisPiece.setxCord(row);
                //thisPiece.setyCord(y);
                pieces.add(thisPiece);
            }
            else{
                RegularPiece emptyPiece = new RegularPiece();
                setPieceOnSpace(emptyPiece,row,y);
                emptyPiece.setName("__" + row + "-" + y + "__");
                emptyPiece.setFriendly_name("empty");
            }

        }
    }

    public void setPieceOnSpace(Piece piece, int x, int y){
        squares[x][y].setPiece(piece);
    }

    public ArrayList<Piece> showBoard(){
        ArrayList<Piece> piece_list = new ArrayList<>();
        for (int x=0;x<8;x++){
            for(int y = 0;y<8;y++){
                System.out.print(squares[x][y].getPiece().getName());
                piece_list.add(squares[x][y].getPiece());
            }
            System.out.println();
        }
        return piece_list;
    }


}
