package com.sgs.lumba.t5.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

import com.sgs.lumba.t5.sfs2x.DBManager;
import com.smartfoxserver.v2.entities.data.ISFSObject;
import com.smartfoxserver.v2.entities.data.SFSObject;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by rypon on 2/16/16.
 */
public class LumbaUser {


    public static boolean Exist(String userId) {

        boolean exist = true;
        try {

        } catch (Exception e) {
            logger.error("Exist:Exception: " + e.toString());
        }

        return exist;
    }
    public static ISFSObject createUserAtLogin(String locale, String platform) {

        ISFSObject out = new SFSObject();
        out.putInt(Error.PARAM_NAME, Error.SUCCESS);
        return out;
    }
    static Connection getConnection() {
        return DBManager.getConnection();
    }
    static void closeConnection(Connection connection) {
        DBManager.closeConnection(connection);
    }
    private final static Logger logger = Logger.getLogger(LumbaUser.class);
}
