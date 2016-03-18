package com.sgs.lumba.t5.sfs2x;

import com.sgs.lumba.t5.models.Command;
import com.smartfoxserver.v2.core.ISFSEvent;
import com.smartfoxserver.v2.core.SFSEventParam;
import com.smartfoxserver.v2.entities.Room;
import com.smartfoxserver.v2.entities.User;
import com.smartfoxserver.v2.exceptions.SFSCreateRoomException;
import com.smartfoxserver.v2.exceptions.SFSException;
import com.smartfoxserver.v2.extensions.BaseServerEventHandler;
import com.smartfoxserver.v2.extensions.ExtensionLogLevel;
import com.smartfoxserver.v2.mmo.CreateMMORoomSettings;
import com.smartfoxserver.v2.mmo.MMORoom;
import com.smartfoxserver.v2.mmo.Vec3D;
import com.smartfoxserver.v2.entities.variables.UserVariable;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import java.util.Date;

/**
 * Created by rypon on 2/22/16.
 */
public class OtherEventHandler extends BaseServerEventHandler {


    @Override
    public void handleServerEvent(ISFSEvent event) throws SFSException {
        Date begin = new Date();
        User user = (User) event.getParameter(SFSEventParam.USER);
        Room room = (Room) event.getParameter(SFSEventParam.ROOM);
        try {
            switch (event.getType()) {
                case USER_DISCONNECT:
                    this.onUserDisconnect(event, user);
                    break;
                case USER_JOIN_ROOM:
                    this.onJoinRoom(event);
                    break;
                case USER_VARIABLES_UPDATE:
                    this.onUserVariablesUpdate(event);
                    break;
                case SERVER_READY:
                    this.onServerReady();
                    break;
                default:
                    break;
            }
        } catch (Exception exception) {
            trace(ExtensionLogLevel.ERROR, "CustomEventHandler:" + event.getType().toString() + ": " + exception.toString());
        }
    }

    public void onServerReady(){
        String matchId = "NetworkPhysics";
        CreateMMORoomSettings matchRoomSetting = new CreateMMORoomSettings();
        matchRoomSetting.setProximityListUpdateMillis(100);
        matchRoomSetting.setUserMaxLimboSeconds(60);
        matchRoomSetting.setSendAOIEntryPoint(false);
        Vec3D defaultAOI = new Vec3D(100f, 100f, 100f);
        matchRoomSetting.setDefaultAOI(defaultAOI);
        matchRoomSetting.setDynamic(true);
        matchRoomSetting.setMaxUsers(5);
        matchRoomSetting.setGame(true);
        matchRoomSetting.setName(matchId);
        matchRoomSetting.setGroupId("game");
        try {
            MMORoom newRoom = (MMORoom) ClientRequestHandler.sfsApi.createRoom(ClientRequestHandler.parentZone, matchRoomSetting, null);
//            FEN defaultFEN = new FEN();
//            mmoAPi.setMMOItemPosition(defaultFEN, new Vec3D(0,0,0), newRoom);
        } catch (SFSCreateRoomException e) {
            trace(ExtensionLogLevel.ERROR, "createMatches:Exception:" + e.toString());
        }
    }

    public void onUserDisconnect(ISFSEvent event, User user){

    }

    public void onJoinRoom(ISFSEvent event){
        User player = (User) event.getParameter(SFSEventParam.USER);
        MMORoom match = (MMORoom) event.getParameter(SFSEventParam.ROOM);
        trace(ExtensionLogLevel.INFO, "onJoinRoom:" + event.getType().toString());
        Vec3D newPosition = new Vec3D(0f, 0f, 0f);
        ClientRequestHandler.mmoAPi.setUserPosition(player,newPosition,match);
    }
    public void onUserVariablesUpdate(ISFSEvent event){
//        User player = (User) event.getParameter(SFSEventParam.USER);
//        MMORoom match = (MMORoom) player.getLastJoinedRoom();
//        trace(ExtensionLogLevel.INFO, "onUserVarUpdate:" + event.getType().toString());
////        Vec3D newPosition = new Vec3D(0,0,0);
////        ClientRequestHandler.mmoAPi.setUserPosition(player,newPosition,match);
//        List<UserVariable> variables = (List<UserVariable>) event.getParameter(SFSEventParam.VARIABLES);
//        Map<String, UserVariable> varMap = new HashMap<String, UserVariable>();
//        for (UserVariable var : variables)
//        {
//            varMap.put(var.getName(), var);
//        }
//        if (varMap.containsKey("x") && varMap.containsKey("y")&& varMap.containsKey("z"))
//        {
//            Vec3D pos = new Vec3D
//                    (
//                            varMap.get("x").getIntValue(),
//                            varMap.get("y").getIntValue(),
//                            varMap.get("z").getIntValue()
//                    );
//            ClientRequestHandler.mmoAPi.setUserPosition(player,pos,match);
//        }

    }
}
