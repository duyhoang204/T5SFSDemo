package com.sgs.lumba.t5.models;

/**
 * Created by rypon on 2/15/16.
 */
public final class Error {
    public static String toString(int errorCode) {
        String result = String.format("[%2d] %s", errorCode, "(Unknown error)");
        switch (errorCode) {
            case SUCCESS:
                result = String.format("[%2d] %s", errorCode, "SUCCESS");
                break;
            case DATA_NOT_FOUND:
                result = String.format("[%2d] %s", errorCode, "DATA_NOT_FOUND");
                break;
            case DB_SQL:
                result = String.format("[%2d] %s", errorCode, "DB_SQL");
                break;
            case DB_CONNECTION:
                result = String.format("[%2d] %s", errorCode, "DB_CONNECTION");
                break;
            case EMAIL_EXISTS:
                result = String.format("[%2d] %s", errorCode, "EMAIL_EXISTS");
                break;
            case EMAIL_INVALID:
                result = String.format("[%2d] %s", errorCode, "EMAIL_INVALID");
                break;
            case EMAIL_PASSWORD_INVALID:
                result = String.format("[%2d] %s", errorCode, "EMAIL_PASSWORD_INVALID");
                break;
            case CREATE_SFS_ROOM:
                result = String.format("[%2d] %s", errorCode, "CREATE_SFS_ROOM");
                break;
            case DATA_NOT_SAVED:
                result = String.format("[%2d] %s", errorCode, "DATA_NOT_SAVED");
                break;
            case DATA_EXPIRED:
                result = String.format("[%2d] %s", errorCode, "DATA_EXPIRED");
                break;
            case CLIENT_OVER_TIME:
                result = String.format("[%2d] %s", errorCode, "CLIENT_OVER_TIME");
                break;
            case NOT_CLAN_OWNER:
                result = String.format("[%2d] %s", errorCode, "NOT_CLAN_OWNER");
                break;
            case CANNOT_LOGIN_ROOM:
                result = String.format("[%2d] %s", errorCode, "CANNOT_LOGIN_ROOM");
                break;
            case CLAN_IS_CLOSED:
                result = String.format("[%2d] %s", errorCode, "CLAN_IS_CLOSED");
                break;
            case NOT_ENOUGH_TROPHY_TO_JOIN_CLAN:
                result = String.format("[%2d] %s", errorCode, "NOT_ENOUGH_TROPHY_TO_JOIN_CLAN");
                break;
            case CLAN_PERMISSION_LOWER_ROLE:
                result = String.format("[%2d] %s", errorCode, "CLAN_PERMISSION_LOWER_ROLE");
                break;
            case CLAN_NO_NEW_ROLE:
                result = String.format("[%2d] %s", errorCode, "CLAN_NO_NEW_ROLE");
                break;
            case CLAN_NOT_MATCH:
                result = String.format("[%2d] %s", errorCode, "CLAN_NOT_MATCH");
                break;
            case CLAN_INVALID_ROLE:
                result = String.format("[%2d] %s", errorCode, "CLAN_INVALID_ROLE");
                break;
            case CLAN_REQUEST_TOO_CLOSE:
                result = String.format("[%2d] %s", errorCode, "CLAN_REQUEST_TOO_CLOSE");
                break;
            case CLAN_ALREADY_JOINED:
                result = String.format("[%2d] %s", errorCode, "CLAN_ALREADY_JOINED");
                break;
            case CLAN_IS_FULL:
                result = String.format("[%2d] %s", errorCode, "CLAN_IS_FULL");
                break;
            case CLAN_MESSAGE_HANDLER:
                result = String.format("[%2d] %s", errorCode, "CLAN_MESSAGE_HANDLER");
                break;
            case BEING_ATTACKED:
                result = String.format("[%2d] %s", errorCode, "BEING_ATTACKED");
                break;
            case INVALID_VERSION:
                result = String.format("[%2d] %s", errorCode, "INVALID_VERSION");
                break;
            case PUSH_IS_NOT_CONFIGURED:
                result = String.format("[%2d] %s", errorCode, "PUSH_IS_NOT_CONFIGURED");
                break;
            case UNIT_NOT_FOUND:
                result = String.format("[%2d] %s", errorCode, "UNIT_NOT_FOUND");
                break;
            case NOT_ENOUGH_TROOPS_TO_DONATE:
                result = String.format("[%2d] %s", errorCode, "NOT_ENOUGH_TROOPS_TO_DONATE");
                break;
            case CLAN_CASTLE_NOT_ENOUGH_SPACE:
                result = String.format("[%2d] %s", errorCode, "CLAN_CASTLE_NOT_ENOUGH_SPACE");
                break;
            case DONATE_MAX:
                result = String.format("[%2d] %s", errorCode, "DONATE_MAX");
                break;
            case SHIELD_IS_ACTIVE:
                result = String.format("[%2d] %s", errorCode, "SHIELD_IS_ACTIVE");
                break;
            case USER_ONLINE:
                result = String.format("[%2d] %s", errorCode, "USER_ONLINE");
                break;
            case INVALID_EMAIL_RESET_CODE:
                result = String.format("[%2d] %s", errorCode, "INVALID_EMAIL_RESET_CODE");
                break;
            case RESET_PASSWORD_TOO_CLOSE:
                result = String.format("[%2d] %s", errorCode, "RESET_PASSWORD_TOO_CLOSE");
                break;
            case ATTACK_SAME_CLAN:
                result = String.format("[%2d] %s", errorCode, "ATTACK_SAME_CLAN");
                break;
            case USER_NOT_FOUND:
                result = String.format("[%2d] %s", errorCode, "USER_NOT_FOUND");
                break;
            case CLAN_NOT_FOUND:
                result = String.format("[%2d] %s", errorCode, "CLAN_NOT_FOUND");
                break;
            case CLAN_CASTLE_BROKEN:
                result = String.format("[%2d] %s", errorCode, "CLAN_CASTLE_BROKEN");
                break;
            case CLAN_INVITE_FULL:
                result = String.format("[%2d] %s", errorCode, "CLAN_INVITE_FULL");
                break;
            case CLAN_INVITER_NOT_FOUND:
                result = String.format("[%2d] %s", errorCode, "CLAN_INVITER_NOT_FOUND");
                break;
            case CLAN_INVITER_NOT_ENOUGH_RIGHT:
                result = String.format("[%2d] %s", errorCode, "CLAN_INVITER_NOT_ENOUGH_RIGHT");
                break;
            case CLAN_INVITATION_NOT_FOUND:
                result = String.format("[%2d] %s", errorCode, "CLAN_INVITATION_NOT_FOUND");
                break;
            case CLAN_WRONG_POLICY:
                result = String.format("[%2d] %s", errorCode, "CLAN_WRONG_POLICY");
                break;
            case CLAN_CANNOT_JOIN:
                result = String.format("[%2d] %s", errorCode, "CLAN_CANNOT_JOIN");
                break;
            case CLAN_MESSAGE_TOO_CLOSE:
                result = String.format("[%2d] %s", errorCode, "CLAN_MESSAGE_TOO_CLOSE");
                break;
            case CLAN_USER_ALREADY_JOINED:
                result = String.format("[%2d] %s", errorCode, "CLAN_USER_ALREADY_JOINED");
                break;
            case CLAN_CANNOT_JOIN_MEMBER_DEMOTED:
                result = String.format("[%2d] %s", errorCode, "CLAN_CANNOT_JOIN_MEMBER_DEMOTED");
                break;
            case CLAN_CANNOT_JOIN_INVITER_NOT_IN_CLAN:
                result = String.format("[%2d] %s", errorCode, "CLAN_CANNOT_JOIN_INVITER_NOT_IN_CLAN");
                break;
            case CLAN_IS_BANNED:
                result = String.format("[%2d] %s", errorCode, "CLAN_IS_BANNED");
                break;
            case CLAN_FEATURE_OF_YOU_IS_LOCKED:
                result = String.format("[%2d] %s", errorCode, "CLAN_FEATURE_OF_YOU_IS_LOCKED");
                break;
            case CLAN_FEATURE_OF_USER_IS_LOCKED:
                result = String.format("[%2d] %s", errorCode, "CLAN_FEATURE_OF_USER_IS_LOCKED");
                break;
            case CLAN_MESSAGE_NOT_FOUND:
                result = String.format("[%2d] %s", errorCode, "CLAN_MESSAGE_NOT_FOUND");
                break;
            case COMMAND_IS_DISABLED:
                result = String.format("[%2d] %s", errorCode, "COMMAND_IS_DISABLED");
                break;
            case COMMAND_IS_DEPRECATED:
                result = String.format("[%2d] %s", errorCode, "COMMAND_IS_DEPRECATED");
                break;
            case COMMAND_IS_NOT_SUPPORTED:
                result = String.format("[%2d] %s", errorCode, "COMMAND_IS_NOT_SUPPORTED");
                break;
            case COMMAND_IS_NOT_RESERVED:
                result = String.format("[%2d] %s", errorCode, "COMMAND_IS_NOT_RESERVED");
                break;
            case INVALID_PRODUCT_ID:
                result = String.format("[%2d] %s", errorCode, "INVALID_PRODUCT_ID");
                break;
            case INVALID_PURCHASE:
                result = String.format("[%2d] %s", errorCode, "INVALID_PURCHASE");
                break;
            case HACK:
                result = String.format("[%2d] %s", errorCode, "HACK");
                break;
            case UNKNOWN:
                result = String.format("[%2d] %s", errorCode, "UNKNOWN");
                break;
            case INTERACT_LOWER_VERSION:
                result = String.format("[%2d] %s", errorCode, "INTERACT_LOWER_VERSION");
                break;
            case INTERACT_HIGHER_VERSION:
                result = String.format("[%2d] %s", errorCode, "INTERACT_HIGHER_VERSION");
                break;
            case ATTACK_IS_BLOCK_AFTER_SERVER_RESTART:
                result = String.format("[%2d] %s", errorCode, "ATTACK_IS_BLOCK_AFTER_SERVER_RESTART");
                break;
            case MAINTAINANCE:
                result = String.format("[%2d] %s", errorCode, "MAINTAINANCE");
                break;
            case CLAN_IS_BLOCKED:
                result = String.format("[%2d] %s", errorCode, "CLAN_IS_BLOCKED");
                break;
            case ONLINE_CONSTANTLY:
                result = String.format("[%2d] %s", errorCode, "ONLINE_CONSTANTLY");
                break;
            case GAME_STATE_ERROR:
                result = String.format("[%2d] %s", errorCode, "GAME_STATE_ERROR");
                break;
            case BLOCK_ATTACK_BEFORE_KICK:
                result = String.format("[%2d] %s", errorCode, "BLOCK_ATTACK_BEFORE_KICK");
                break;
            case LINK_CODE_INVALID:
                result = String.format("[%2d] %s", errorCode, "LINK_CODE_INVALID");
                break;
            case PLATFORM_CANT_DETECTED:
                result = String.format("[%2d] %s", errorCode, "PLATFORM_CANT_DETECTED");
                break;
            case LINK_CODE_EXPIRED:
                result = String.format("[%2d] %s", errorCode, "LINK_CODE_EXPIRED");
                break;
            case ALREADY_LINK_DEVICE:
                result = String.format("[%2d] %s", errorCode, "ALREADY_LINK_DEVICE");
                break;
            case ALREADY_LINK_CENTER:
                result = String.format("[%2d] %s", errorCode, "ALREADY_LINK_CENTER");
                break;
            case GAMEID_IS_USED:
                result = String.format("[%2d] %s", errorCode, "GAMEID_IS_USED");
                break;
            case CANT_LINK_SAME_OS:
                result = String.format("[%2d] %s", errorCode, "CANT_LINK_SAME_OS");
                break;
            case KICKED_BY_ADMIN:
                result = String.format("[%2d] %s", errorCode, "KICKED_BY_ADMIN");
                break;
            case CLAN_WAR_NOT_FOUND:
                result = String.format("[%2d] %s", errorCode, "CLAN_WAR_NOT_FOUND");
                break;
            case CLAN_WAR_ALREADY_ATTACK:
                result = String.format("[%2d] %s", errorCode, "CLAN_WAR_ALREADY_ATTACK");
                break;
            case CLAN_WAR_CANT_ATTACK:
                result = String.format("[%2d] %s", errorCode, "CLAN_WAR_CANT_ATTACK");
                break;
            case CLAN_WAR_ID_DOESNT_EXIST:
                result = String.format("[%2d] %s", errorCode, "CLAN_WAR_ID_DOESNT_EXIST");
                break;
            case CLAN_WAR_ENDED:
                result = String.format("[%2d] %s", errorCode, "CLAN_WAR_ENDED");
                break;
            case CLAN_WAR_REPLAY_INVALID:
                result = String.format("[%2d] %s", errorCode, "CLAN_WAR_REPLAY_INVALID");
                break;
            case CLAN_WAR_END_ALREADY:
                result = String.format("[%2d] %s", errorCode, "CLAN_WAR_END_ALREADY");
                break;
            case CLAN_WAR_END_WRONG_ID:
                result = String.format("[%2d] %s", errorCode, "CLAN_WAR_END_WRONG_ID");
                break;
            case CLAN_WAR_END_HAVE_BATTLE:
                result = String.format("[%2d] %s", errorCode, "CLAN_WAR_END_HAVE_BATTLE");
                break;
            case WAR_NOT_FOUND:
                result = String.format("[%2d] %s", errorCode, "WAR_NOT_FOUND");
                break;
            case USER_NOT_IN_CLAN_WAR:
                result = String.format("[%2d] %s", errorCode, "USER_NOT_IN_CLAN_WAR");
                break;
            case CLAN_WAR_DEFENDER_IN_BATTLE:
                result = String.format("[%2d] %s", errorCode, "CLAN_WAR_DEFENDER_IN_BATTLE");
                break;


        }
        return result;
    }

    public static final String PARAM_NAME = "errorCode";
    public static final String PARAM_LOAD_NAME = "loadErrorCode";

    public static final int SUCCESS = 0;
    public static final int DATA_NOT_FOUND = 1;
    public static final int DB_SQL = 2;
    public static final int DB_CONNECTION = 3;
    public static final int EMAIL_EXISTS = 4;
    public static final int EMAIL_INVALID = 5;
    public static final int EMAIL_PASSWORD_INVALID = 6;
    public static final int CREATE_SFS_ROOM = 7;
    public static final int DATA_NOT_SAVED = 8;
    public static final int NOT_CLAN_OWNER = 9;
    public static final int CANNOT_LOGIN_ROOM = 10;
    public static final int CLAN_IS_CLOSED = 11;
    public static final int NOT_ENOUGH_TROPHY_TO_JOIN_CLAN = 12;
    public static final int CLAN_PERMISSION_LOWER_ROLE = 13;
    public static final int CLAN_NO_NEW_ROLE = 14;
    public static final int CLAN_NOT_MATCH = 15;
    public static final int CLAN_INVALID_ROLE = 16;
    public static final int CLAN_REQUEST_TOO_CLOSE = 17;
    public static final int CLAN_ALREADY_JOINED = 18;
    public static final int CLAN_IS_FULL = 19;
    public static final int CLAN_MESSAGE_HANDLER = 20;
    public static final int BEING_ATTACKED = 21;
    public static final int INVALID_VERSION = 22;
    public static final int PUSH_IS_NOT_CONFIGURED = 23;
    public static final int UNIT_NOT_FOUND = 24;
    public static final int NOT_ENOUGH_TROOPS_TO_DONATE = 25;
    public static final int CLAN_CASTLE_NOT_ENOUGH_SPACE = 26;
    public static final int DONATE_MAX = 27;
    public static final int SHIELD_IS_ACTIVE = 28;
    public static final int USER_ONLINE = 29;
    public static final int INVALID_EMAIL_RESET_CODE = 30;
    public static final int RESET_PASSWORD_TOO_CLOSE = 31;
    public static final int ATTACK_SAME_CLAN = 32;
    public static final int USER_NOT_FOUND = 33;
    public static final int CLAN_NOT_FOUND = 34;
    public static final int CLAN_CASTLE_BROKEN = 35;
    public static final int CLAN_INVITE_FULL = 36;
    public static final int CLAN_INVITER_NOT_FOUND = 37;
    public static final int CLAN_INVITER_NOT_ENOUGH_RIGHT = 38;
    public static final int CLAN_INVITATION_NOT_FOUND = 39;
    public static final int CLAN_WRONG_POLICY = 40;
    public static final int CLAN_CANNOT_JOIN = 41;
    public static final int CLAN_MESSAGE_TOO_CLOSE = 42;
    public static final int CLAN_USER_ALREADY_JOINED = 43;
    public static final int CLAN_CANNOT_JOIN_MEMBER_DEMOTED = 44;
    public static final int CLAN_CANNOT_JOIN_INVITER_NOT_IN_CLAN = 45;
    public static final int CLAN_IS_BANNED = 46;
    public static final int CLAN_FEATURE_OF_YOU_IS_LOCKED = 47;
    public static final int CLAN_FEATURE_OF_USER_IS_LOCKED = 48;
    public static final int INTERACT_LOWER_VERSION = 49;
    public static final int INTERACT_HIGHER_VERSION = 50;

    public static final int COMMAND_IS_DISABLED = 93;
    public static final int COMMAND_IS_DEPRECATED = 94;
    public static final int COMMAND_IS_NOT_SUPPORTED = 95;
    public static final int COMMAND_IS_NOT_RESERVED = 96;
    public static final int INVALID_PRODUCT_ID = 97;
    public static final int INVALID_PURCHASE = 98;
    public static final int HACK = 99;
    public static final int UNKNOWN = 100;
    public static final int MAINTAINANCE = 101;
    public static final int DATA_EXPIRED = 102;
    public static final int CLIENT_OVER_TIME = 103;
    public static final int CLAN_MESSAGE_NOT_FOUND = 104;
    public static final int ATTACK_IS_BLOCK_AFTER_SERVER_RESTART = 105;
    public static final int CLAN_IS_BLOCKED = 106;
    public static final int ONLINE_CONSTANTLY = 107;
    public static final int GAME_STATE_ERROR = 108;
    public static final int BLOCK_ATTACK_BEFORE_KICK = 109;
    public static final int LINK_CODE_INVALID = 110;
    public static final int PLATFORM_CANT_DETECTED = 111;
    public static final int LINK_CODE_EXPIRED = 112;
    public static final int CANT_LINK_SAME_OS = 113;
    public static final int ALREADY_LINK_DEVICE = 114;
    public static final int ALREADY_LINK_CENTER = 115;
    public static final int GAMEID_IS_USED = 116;
    public static final int KICKED_BY_ADMIN = 117;
    public static final int ERROR_LOG_GENERAL = 0;
    public static final int ERROR_LOG_GA = 1;
    public static final int ERROR_LOG_OUT_OF_SYNC = 2;
    public static final int ERROR_LOG_CONNECTION_LOST = 3;
    public static final int USER_NOT_IN_CLAN_WAR = 118;
    public static final int CLAN_WAR_NOT_FOUND = 119;
    public static final int CLAN_WAR_ALREADY_ATTACK = 120;
    public static final int CLAN_WAR_ID_DOESNT_EXIST = 121;
    public static final int CLAN_WAR_CANT_ATTACK = 122;
    public static final int CLAN_WAR_ENDED = 123;
    public static final int CLAN_WAR_REPLAY_INVALID = 124;
    public static final int CLAN_WAR_END_WRONG_ID = 125;
    public static final int CLAN_WAR_END_ALREADY = 126;
    public static final int CLAN_WAR_END_HAVE_BATTLE = 127;
    public static final int WAR_NOT_FOUND = 128;
    public static final int CLAN_WAR_DEFENDER_IN_BATTLE = 129;

    public static final int INVALID_DEPLOY = 130;
}
