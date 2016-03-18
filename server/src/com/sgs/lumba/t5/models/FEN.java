package com.sgs.lumba.t5.models;

import com.smartfoxserver.v2.mmo.MMOItem;
import com.smartfoxserver.v2.mmo.MMOItemVariable;

/**
 * Created by rypon on 2/24/16.
 */
public class FEN extends MMOItem {
    private String FEN;

    public FEN(){
        this.FEN = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1";
        MMOItemVariable var = new MMOItemVariable("FEN", "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1");
        this.setVariable(var);
    }

    public String getFEN() {
        return FEN;
    }

    public void setFEN(String FEN) {
        this.FEN = FEN;
    }
}
