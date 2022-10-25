package edu.lewisu;

import java.io.Serializable;

public class Square implements Serializable {
    Piece piece;

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public Square() {
    }
}
