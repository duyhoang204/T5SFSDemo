package com.sgs.lumba.t5.sfs2x;
import com.smartfoxserver.bitswarm.sessions.Session;
import com.smartfoxserver.v2.api.ISFSApi;
import com.smartfoxserver.v2.core.ISFSEvent;
import com.smartfoxserver.v2.core.SFSConstants;
import com.smartfoxserver.v2.core.SFSEventParam;
import com.smartfoxserver.v2.entities.User;
import com.smartfoxserver.v2.entities.data.ISFSObject;
import com.smartfoxserver.v2.exceptions.SFSErrorCode;
import com.smartfoxserver.v2.exceptions.SFSErrorData;
import com.smartfoxserver.v2.exceptions.SFSException;
import com.smartfoxserver.v2.exceptions.SFSLoginException;
import com.smartfoxserver.v2.extensions.BaseServerEventHandler;
import com.smartfoxserver.v2.extensions.ExtensionLogLevel;
import com.sgs.lumba.t5.models.Error;
import org.json.JSONObject;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import com.sgs.lumba.t5.models.Memcache;

/**
 * Created by rypon on 2/15/16.
 */
public class LoginEventHandler extends  BaseServerEventHandler {
    private static final HashSet<String> loggingUsers = new HashSet<String>();

    private static boolean beginLogin(String userId) {
        synchronized (LoginEventHandler.loggingUsers) {
            if (LoginEventHandler.loggingUsers.contains(userId)) {
                return false;
            } else {
                LoginEventHandler.loggingUsers.add(userId);
                return true;
            }
        }
    }

    private static boolean endLogin(String userId) {
        synchronized (LoginEventHandler.loggingUsers) {
            if (LoginEventHandler.loggingUsers.contains(userId)) {
                LoginEventHandler.loggingUsers.remove(userId);
                return true;
            } else {
                return false;
            }
        }
    }
    @Override
    public void handleServerEvent(ISFSEvent isfsEvent) throws SFSException {
        String userId = (String) isfsEvent.getParameter(SFSEventParam.LOGIN_NAME);
        if (userId != null && !userId.isEmpty() && !LoginEventHandler.beginLogin(userId)) {
            SFSErrorData errorData = new SFSErrorData(SFSErrorCode.LOGIN_ALREADY_LOGGED);
            throw new SFSLoginException("Login in processing!", errorData);
        }
        ISFSObject outData = (ISFSObject) isfsEvent.getParameter(SFSEventParam.LOGIN_OUT_DATA);
        outData.putInt(Error.PARAM_NAME, Error.SUCCESS);
        LoginEventHandler.endLogin(userId);
        Memcache.listPlayers.add(userId);
    }
}
