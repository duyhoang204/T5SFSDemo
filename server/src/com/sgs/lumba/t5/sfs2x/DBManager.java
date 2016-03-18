package com.sgs.lumba.t5.sfs2x;

/**
 * Created by rypon on 2/15/16.
 */
import java.sql.Connection;
import java.sql.SQLException;

import org.apache.log4j.Logger;

public final class DBManager {
    public static void closeConnection(Connection connection) {
        try {
            connection.close();
        } catch (SQLException e) {
            DBManager.logger.error("closeConnection:Exception:" + e.toString());
        }
    }

    public static Connection getConnection() {
        try {
            return DBManager.extension.getParentZone().getDBManager()
                    .getConnection();
        } catch (SQLException e) {
            DBManager.logger.error("getConnection:Exception:" + e.toString());
            return null;
        }
    }

    public static void init(T5Extension lumbaExtension) {
        DBManager.extension = lumbaExtension;
    }

    private final static Logger logger = Logger.getLogger(DBManager.class);
    private static T5Extension extension;
}
