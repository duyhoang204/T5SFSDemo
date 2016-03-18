package com.sgs.lumba.t5.sfs2x;

import com.smartfoxserver.v2.SmartFoxServer;
import com.smartfoxserver.v2.api.ISFSApi;
import com.smartfoxserver.v2.api.ISFSMMOApi;
import com.smartfoxserver.v2.entities.User;
import com.smartfoxserver.v2.entities.Zone;
import com.smartfoxserver.v2.entities.data.ISFSObject;

import com.smartfoxserver.v2.entities.data.SFSObject;
import com.smartfoxserver.v2.extensions.BaseClientRequestHandler;
import com.smartfoxserver.v2.extensions.ExtensionLogLevel;

/**
 * Created by rypon on 2/4/16.
 */
public class ClientRequestHandler extends BaseClientRequestHandler {
    protected static T5Extension t5Extension;
    protected static Zone parentZone = null;
    protected static ISFSApi sfsApi = null;
    protected static ISFSMMOApi mmoAPi;
    @Override
    public void handleClientRequest(User user, ISFSObject isfsObject) {
        ISFSObject out = new SFSObject();
        out.putUtfString("ServerTime", TimeManager.GetServerTimeString());
        handleRequest(user, isfsObject, out);
        this.t5Extension = (T5Extension) getParentExtension();
        this.t5Extension.send("user_service", out, user);
    }

    protected void handleRequest(User player, ISFSObject params, ISFSObject out) {
    }
    public static void init(T5Extension t5Extension){
            ClientRequestHandler.t5Extension = t5Extension;
            ClientRequestHandler.parentZone = t5Extension.getParentZone();
            ClientRequestHandler.sfsApi = t5Extension.getApi();
            ClientRequestHandler.mmoAPi = SmartFoxServer.getInstance().getAPIManager().getMMOApi();

    }
}
