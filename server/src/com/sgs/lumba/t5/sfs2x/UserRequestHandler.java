package com.sgs.lumba.t5.sfs2x;

import com.sgs.lumba.t5.models.*;
import com.sgs.lumba.t5.models.Error;
import com.smartfoxserver.v2.SmartFoxServer;
import com.smartfoxserver.v2.api.ISFSApi;
import com.smartfoxserver.v2.api.SFSMMOApi;
import com.smartfoxserver.v2.entities.Room;
import com.smartfoxserver.v2.entities.SFSRoomRemoveMode;
import com.smartfoxserver.v2.entities.User;
import com.smartfoxserver.v2.entities.Zone;
import com.smartfoxserver.v2.entities.data.ISFSObject;
import com.smartfoxserver.v2.entities.data.SFSObject;
import com.smartfoxserver.v2.exceptions.SFSCreateRoomException;
import com.smartfoxserver.v2.exceptions.SFSJoinRoomException;
import com.smartfoxserver.v2.extensions.ExtensionLogLevel;
import com.smartfoxserver.v2.mmo.*;
import org.json.JSONArray;
import com.sgs.lumba.t5.sfs2x.TimeManager;

import java.io.UnsupportedEncodingException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by rypon on 2/22/16.
 */
public class UserRequestHandler extends ClientRequestHandler {
    private int lastSeqNo;
    @Override
    protected void handleRequest(User player, ISFSObject params, ISFSObject out) {
        int commandId = params.getInt("commandId");
        out.putInt("commandId", commandId);
        String userId = params.getUtfString("userId");
        out.putInt(Error.PARAM_NAME, Error.SUCCESS);
        switch (commandId){
            case Command.LIST_PLAYERS:
                this.listPlayers(player, params, out);
            break;
            case Command.LIST_MATCHES:
                this.listMatches(player, params, out);
                break;
            case Command.CREATE_MATCHES:
                this.createMatches(player, params, out);
                break;
            case Command.JOIN_MATCHES:
                this.joinMatches(player, params, out);
                break;
            case Command.DEPLOY_CARD:
                deployCard(player, params, out);
                break;
            case Command.MOVE:
                trace(ExtensionLogLevel.DEBUG, "Received move request");
                move(player, params, out);
                break;
            default:
            break;
        }
    }

    private void listPlayers(User player,ISFSObject params, ISFSObject out){
        JSONArray list = new JSONArray();
        for (String p : Memcache.listPlayers){
            if (!p.equals(player.getName())){
                list.put(p);
            }
        }
        try {
            out.putByteArray("listPlayers", Utils.stringToBytesArray(list.toString()));
        } catch (UnsupportedEncodingException e) {
            trace(ExtensionLogLevel.ERROR, "ListPlayers:Exception:" + e.toString());
        }
    }

    private void listMatches(User player,ISFSObject params, ISFSObject out){
        JSONArray listMatches = new JSONArray();
        for (MatchState matchInfo : Memcache.listMatches){
            listMatches.put(matchInfo.toJSON());
        }
        try {
            out.putByteArray("listMatches", Utils.stringToBytesArray(listMatches.toString()));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }


    private void createMatches(User player,ISFSObject params, ISFSObject out){
        String userId = player.getName();
        String matchId = String.valueOf(TimeManager.GetTimeInMillis());
        CreateMMORoomSettings matchRoomSetting = new CreateMMORoomSettings();
        matchRoomSetting.setProximityListUpdateMillis(100);
        matchRoomSetting.setUserMaxLimboSeconds(60);
        matchRoomSetting.setSendAOIEntryPoint(false);
        Vec3D defaultAOI = new Vec3D(100f,100f,100f);
        matchRoomSetting.setDefaultAOI(defaultAOI);
        matchRoomSetting.setDynamic(true);
        matchRoomSetting.setMaxUsers(5);
        matchRoomSetting.setGame(true);
        matchRoomSetting.setName(matchId);
        matchRoomSetting.setGroupId("game");
        try {
            MMORoom newRoom = (MMORoom) this.getApi().createRoom(this.parentZone, matchRoomSetting, null);
//            FEN defaultFEN = new FEN();
//            mmoAPi.setMMOItemPosition(defaultFEN, new Vec3D(0,0,0), newRoom);
        } catch (SFSCreateRoomException e) {
            trace(ExtensionLogLevel.ERROR, "createMatches:Exception:" + e.toString());
        }
        out.putInt("commandId", Command.CREATE_MATCHES);
        out.putUtfString("matchId", matchId);
    }

    private void joinMatches(User player,ISFSObject params, ISFSObject out){
        String userId = player.getName();
        String matchId = params.containsKey("matchId") ? params.getUtfString("matchId") : "test";
        if (matchId.equals("test")){
            out.putInt(Error.PARAM_NAME, Error.DATA_NOT_FOUND);
        } else {
            MMORoom match = (MMORoom) this.parentZone.getRoomByName(matchId);
            if (match !=null){
                try {
                    this.getApi().joinRoom(player, match);
                } catch (SFSJoinRoomException e) {
                    trace(ExtensionLogLevel.ERROR, "joinMatches:Exception:" + e.toString());
                }
            }
        }
        out.putUtfString("matchId", matchId);
    }

    private void createMatches1(User player,ISFSObject params, ISFSObject out){
        String userId = player.getName();
        String opponentId = params.containsKey("opponentId") ? params.getUtfString("opponentId") : "test";
        String matchId = String.valueOf(TimeManager.GetTimeInMillis());
        String FEN = "";
        String algebraicNotation = "";
        MatchState newMatch = new MatchState(userId,opponentId,FEN,algebraicNotation,matchId);
        Memcache.listMatches.add(newMatch);
        CreateMMORoomSettings matchRoomSetting = new CreateMMORoomSettings();
        matchRoomSetting.setProximityListUpdateMillis(100);
        matchRoomSetting.setUserMaxLimboSeconds(60);
        matchRoomSetting.setSendAOIEntryPoint(false);
        Vec3D defaultAOI = new Vec3D(100,100,100);
        matchRoomSetting.setDefaultAOI(defaultAOI);
        matchRoomSetting.setDynamic(true);
        matchRoomSetting.setMaxUsers(5);
        matchRoomSetting.setGame(true);
        matchRoomSetting.setName(matchId);
        matchRoomSetting.setGroupId("game");
        try {
            MMORoom newRoom = (MMORoom) this.getApi().createRoom(this.parentZone, matchRoomSetting, null);
            FEN defaultFEN = new FEN();
            mmoAPi.setMMOItemPosition(defaultFEN, new Vec3D(0,0,0), newRoom);
        } catch (SFSCreateRoomException e) {
            trace(ExtensionLogLevel.ERROR, "createMatches:Exception:" + e.toString());
        }
        out.putUtfString("matchId", matchId);
    }

    private void joinMatches1(User player,ISFSObject params, ISFSObject out){
        String userId = player.getName();
        String matchId = params.containsKey("matchId") ? params.getUtfString("matchId") : "test";
        if (matchId.equals("test")){
            out.putInt(Error.PARAM_NAME, Error.DATA_NOT_FOUND);
        } else {
            MMORoom match = (MMORoom) this.parentZone.getRoomByName(matchId);
            if (match !=null){
                try {
                    this.getApi().joinRoom(player, match);
                } catch (SFSJoinRoomException e) {
                    trace(ExtensionLogLevel.ERROR, "joinMatches:Exception:" + e.toString());
                }
            }
        }
    }

    private void deployCard(User player, ISFSObject params, ISFSObject out) {
        if (!isValidDeploy()) {
            out.putInt(Error.PARAM_NAME, Error.INVALID_DEPLOY);
            return;
        }

        //prepare unit state
        float moveSpd = 8f; //sample move speed to calculate moveTime
        float inputX = params.getFloat("x");
        float inputY= params.getFloat("y");
        float inputZ = params.getFloat("z");
        int unitType = params.getInt("ut");
        int unitAction = Unit.MOVE;
        float currentX = inputX;
        float currentY = inputY;
        float currentZ = inputZ;
        float endX = inputX + 50f;
        float endY = inputY;
        float endZ = inputX + 50f;
        float moveTime = 20; //20s
        float currentTimeFrame = 1;
        int lastSeqNo = params.getInt("seqNo");


        List<IMMOItemVariable> cardVars = new LinkedList<IMMOItemVariable>();
        //sample variables
        cardVars.add(new MMOItemVariable("ut", unitType));
        cardVars.add(new MMOItemVariable("ua", unitAction));
        cardVars.add(new MMOItemVariable("curX", currentX));
        cardVars.add(new MMOItemVariable("curY", currentY));
        cardVars.add(new MMOItemVariable("curZ", currentZ));
        cardVars.add(new MMOItemVariable("endX", endX));
        cardVars.add(new MMOItemVariable("endY", endY));
        cardVars.add(new MMOItemVariable("endZ", endZ));
        cardVars.add(new MMOItemVariable("curFrame", currentTimeFrame));
        cardVars.add(new MMOItemVariable("mt", moveTime));
        cardVars.add(new MMOItemVariable("lastSeq", lastSeqNo));


        SFSMMOApi mmoApi = (SFSMMOApi) SmartFoxServer.getInstance().getAPIManager().getMMOApi();
        MMORoom match = player.getCurrentMMORoom();
        trace(ExtensionLogLevel.DEBUG, match.toString());
        //deploy
        MMOItem card = new MMOItem(cardVars);
        mmoApi.setMMOItemPosition(card, new Vec3D(inputX,inputY,inputZ), match);
    }

    private void move(User player, ISFSObject params, ISFSObject out) {
        float inputX = params.getFloat("x");
        float inputY= params.getFloat("y");
        float inputZ = params.getFloat("z");



        List<IMMOItemVariable> cardVars = new LinkedList<IMMOItemVariable>();
        MMORoom match = player.getCurrentMMORoom();
        boolean firstPlayer = false;
        if (player.getId() == match.getVariable("owner").getIntValue())
            firstPlayer = true;

        cardVars.add(new MMOItemVariable("1st", firstPlayer));
        cardVars.add(new MMOItemVariable("x", inputX));
        cardVars.add(new MMOItemVariable("y", inputY));
        cardVars.add(new MMOItemVariable("z", inputZ));
        cardVars.add(new MMOItemVariable("uid", player.getId()));

        trace(ExtensionLogLevel.DEBUG, match.toString());
        //deploy
        MMOItem card = new MMOItem(cardVars);

        SFSMMOApi mmoApi = (SFSMMOApi) SmartFoxServer.getInstance().getAPIManager().getMMOApi();
        mmoApi.setMMOItemPosition(card, new Vec3D(inputX,inputY,inputZ), match);
    }
    private boolean isValidDeploy() {
        //check valid deploy here

        return true;
    }
}
