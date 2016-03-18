package com.sgs.lumba.t5.models;


import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by rypon on 2/23/16.
 */
public class MatchState {
    private String whitePlayerId;
    private String blackPlayerId;
    private String FEN;
    private String algebraicNotation;
    private String matchId;

    public MatchState(String whitePlayerId, String blackPlayerId, String FEN, String algebraicNotation, String matchId) {
        this.whitePlayerId = whitePlayerId;
        this.blackPlayerId = blackPlayerId;
        this.FEN = FEN;
        this.algebraicNotation = algebraicNotation;
        this.matchId = matchId;
    }

    public JSONObject toJSON(){
        JSONObject matchInfo = new JSONObject();
        try {
            matchInfo.put("whitePlayerId", this.whitePlayerId);
            matchInfo.put("blackPlayerId", this.blackPlayerId);
            matchInfo.put("FEN", this.FEN);
            matchInfo.put("algebraicNotation", this.algebraicNotation);
            matchInfo.put("matchId", this.matchId);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return matchInfo;
    }


    public String getWhitePlayerId() {
        return whitePlayerId;
    }

    public void setWhitePlayerId(String whitePlayerId) {
        this.whitePlayerId = whitePlayerId;
    }

    public String getBlackPlayerId() {
        return blackPlayerId;
    }

    public void setBlackPlayerId(String blackPlayerId) {
        this.blackPlayerId = blackPlayerId;
    }

    public String getFEN() {
        return FEN;
    }

    public void setFEN(String FEN) {
        this.FEN = FEN;
    }

    public String getAlgebraicNotation() {
        return algebraicNotation;
    }

    public void setAlgebraicNotation(String algebraicNotation) {
        this.algebraicNotation = algebraicNotation;
    }

    public String getMatchId() {
        return matchId;
    }

    public void setMatchId(String matchId) {
        this.matchId = matchId;
    }
}
