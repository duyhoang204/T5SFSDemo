package com.sgs.lumba.t5.sfs2x;

import com.sgs.lumba.t5.models.FEN;
import com.smartfoxserver.v2.core.SFSEventType;
import com.smartfoxserver.v2.exceptions.SFSCreateRoomException;
import com.smartfoxserver.v2.extensions.ExtensionLogLevel;
import com.smartfoxserver.v2.extensions.SFSExtension;
import com.smartfoxserver.v2.mmo.CreateMMORoomSettings;
import com.smartfoxserver.v2.mmo.MMORoom;
import com.smartfoxserver.v2.mmo.Vec3D;


/**
 * Created by rypon on 2/4/16.
 */
public class T5Extension extends  SFSExtension {
    public class Service {
        public static final String USER = "user_service";
    }
    @Override
    public void init() {
        trace(ExtensionLogLevel.INFO, "T5 extension is initialized.");
        addEventHandler(SFSEventType.USER_LOGIN, LoginEventHandler.class);
        addEventHandler(SFSEventType.USER_DISCONNECT, OtherEventHandler.class);
        addEventHandler(SFSEventType.USER_JOIN_ROOM, OtherEventHandler.class);
        addEventHandler(SFSEventType.USER_VARIABLES_UPDATE, OtherEventHandler.class);
        addEventHandler(SFSEventType.SERVER_READY, OtherEventHandler.class);
        addRequestHandler(Service.USER, UserRequestHandler.class);
        ClientRequestHandler.init(this);
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}
