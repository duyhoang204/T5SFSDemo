package com.sgs.lumba.t5.models;

import com.smartfoxserver.v2.entities.data.ISFSObject;
import com.smartfoxserver.v2.entities.data.SFSDataType;
import com.smartfoxserver.v2.entities.data.SFSObject;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by rypon on 2/23/16.
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;


public final class Utils {
    public static String byteArrayToString(byte[] bytes)
            throws UnsupportedEncodingException {
        return new String(bytes, "UTF-8");
    }

    public static int compareVersion(int[] ver1, int[] ver2) {
        if ((ver1 == null && ver2 == null)
                || (ver1.length == 0 && ver2.length == 0)) {
            return 0;
        }
        if (ver1 == null || ver1.length == 0) {
            return -1;
        }
        if (ver2 == null || ver2.length == 0) {
            return 1;
        }
        int minLength = Math.min(ver1.length, ver2.length);
        int result = 0;
        for (int i = 0; i < minLength; i++) {
            result = Integer.valueOf(ver1[i]).compareTo(Integer.valueOf(ver2[i]));
            if (result != 0) {
                return result;
            }
        }
        if (ver1.length > ver2.length) {
            for (int i = minLength; i < ver1.length; i++) {
                if (ver1[i] > 0) {
                    result = 1;
                    break;
                }
            }
        } else if (ver1.length < ver2.length) {
            for (int i = minLength; i < ver2.length; i++) {
                if (ver2[i] > 0) {
                    result = -1;
                    break;
                }
            }
        }
        return result;
    }

    public static int compareVersion(String ver1, String ver2) {
        if (ver1 == null && ver2 == null) {
            return 0;
        }
        if (ver1 == null) {
            return -1;
        }
        if (ver2 == null) {
            return 1;
        }
        if (ver1.isEmpty() || ver1.equals("*") || ver2.isEmpty()
                || ver2.equals("*")) {
            return 0;
        }
        return Utils.compareVersion(Utils.parserVersionString(ver1),
                Utils.parserVersionString(ver2));
    }

    public static JSONObject convertToGameStateV1(JSONObject gameState)
            throws JSONException {
        JSONObject gameStateV1 = new JSONObject(gameState.toString());
        gameStateV1.remove("ver");
        Utils.convertToObjectsV1(gameStateV1.getJSONObject("Objects"));
        return gameStateV1;
    }

    public static JSONObject convertToGameStateV2(JSONObject gameState,
                                                  String version) throws JSONException {
        JSONObject gameStateV2 = new JSONObject(gameState.toString());
        gameStateV2.put("ver", version);
        Utils.convertToObjectsV2(gameStateV2.getJSONObject("Objects"), version);
        return gameStateV2;
    }

    public static JSONObject convertToObjectsV1(JSONObject objects)
            throws JSONException {
        objects.remove("ver");
        JSONArray bds = objects.getJSONArray("bds");
        objects.remove("bds");
        int nTiles = bds.length();
        objects.put("nTiles", nTiles);
        for (int j = 0; j < nTiles; j++) {
            JSONObject tile = bds.getJSONObject(j);
            objects.put("tile" + j, tile);
            Utils.convertToTileV1(tile);
            tile = null;
        }
        return objects;
    }

    public static JSONObject convertToObjectsV2(JSONObject objects,
                                                String version) throws JSONException {
        objects.put("ver", version);
        JSONArray bds = new JSONArray();
        objects.put("bds", bds);
        int nTiles = objects.getInt("nTiles");
        objects.remove("nTiles");
        for (int j = 0; j < nTiles; j++) {
            JSONObject tile = objects.getJSONObject("tile" + j);
            objects.remove("tile" + j);
            bds.put(tile);
            Utils.convertToTileV2(tile);
            tile = null;
        }
        return objects;
    }

    public static JSONObject convertToTileV1(JSONObject tile)
            throws JSONException {
        if (tile.has("sd")) {
            JSONArray specialData = null;
            if (tile.optString("sd").equals("null")) {
                tile.put("specialData", "null");
            } else {
                specialData = tile.getJSONArray("sd");
                tile.put("specialData", specialData);
            }
            tile.remove("sd");
            specialData = null;
        }
        int type = tile.getInt("ty");
        tile.remove("ty");
        tile.put("type", type);
        if (tile.has("d")) {
            boolean isDestroyed = tile.getBoolean("d");
            tile.remove("d");
            tile.put("isDestroyed", isDestroyed);
        }
        JSONArray tInfo = tile.getJSONArray("ti");
        tile.remove("ti");
        tile.put("tInfo", tInfo);
        tInfo = null;
        return tile;
    }

    public static JSONObject convertToTileV2(JSONObject tile)
            throws JSONException {
        if (tile.has("specialData")) {
            if (tile.optString("specialData").equals("null")) {
                tile.put("sd", "null");
            } else {
                JSONArray specialData = tile.getJSONArray("specialData");
                tile.put("sd", specialData);
                specialData = null;
            }
        }
        tile.remove("specialData");
        int type = tile.getInt("type");
        tile.remove("type");
        tile.put("ty", type);
        if (tile.has("isDestroyed")) {
            boolean isDestroyed = tile.getBoolean("isDestroyed");
            tile.remove("isDestroyed");
            tile.put("d", isDestroyed);
        }
        JSONArray tInfo = tile.getJSONArray("tInfo");
        tile.remove("tInfo");
        tile.put("ti", tInfo);
        tInfo = null;
        return tile;
    }

    public static void CopySFSObject(ISFSObject source, ISFSObject destination) {
        Set<String> keys = source.getKeys();
        Iterator<String> iterator = keys.iterator();
        while (iterator.hasNext()) {
            String key = iterator.next();
            destination.put(key, source.get(key));
        }
    }

    public static ISFSObject CopySFSObjectWithoutByteArray(ISFSObject source) {
        ISFSObject destination = new SFSObject();
        Set<String> keys = source.getKeys();
        Iterator<String> iterator = keys.iterator();
        while (iterator.hasNext()) {
            String key = iterator.next();

            if (source.get(key).getTypeId() != SFSDataType.BYTE_ARRAY) {
                destination.put(key, source.get(key));
            }
        }
        return destination;
    }

    public static int detectGameStateFormat(JSONObject jsonData)
            throws JSONException {
        if (jsonData.has("Objects")) {
            if (jsonData.has("ver")
                    && jsonData.getJSONObject("Objects").has("ver")) {
                return GAME_STATE_FORMAT_AFTER_1_9_3;
            } else if (!jsonData.has("ver")
                    && !jsonData.getJSONObject("Objects").has("ver")) {
                return GAME_STATE_FORMAT_BEFORE_1_9_3;
            } else {
                return GAME_STATE_FORMAT_INVALID_FORMAT;
            }
        } else {
            if (jsonData.has("bds")) {
                return GAME_STATE_FORMAT_OBJECTS_AFTER_1_9_3;
            } else if (jsonData.has("nTiles")) {
                return GAME_STATE_FORMAT_OBJECTS_BEFORE_1_9_3;
            } else {
                if (jsonData.has("ty")) {
                    return GAME_STATE_FORMAT_TILE_AFTER_1_9_3;
                } else if (jsonData.has("type")) {
                    return GAME_STATE_FORMAT_TILE_BEFORE_1_9_3;
                } else {
                    return GAME_STATE_FORMAT_UNKNOWN;
                }
            }
        }
    }

    public static String generateRandomCode(int length) {
        StringBuilder result = new StringBuilder("");
        int rand = (int) (Math.random() * Utils.alphabetic.length);
        result.append(Utils.alphabetic[rand]);
        for (int i = 1; i < length; i++) {
            rand = (int) (Math.random() * Utils.alphanumeric.length);
            result.append(Utils.alphanumeric[rand]);
        }
        return result.toString();
    }

    public static String GenerateRandomString() {
        return GenerateRandomString(characters.length);
    }

    public static String GenerateRandomString(int length) {
        StringBuilder result = new StringBuilder("");
        for (int i = 0; i < length; i++) {
            int rand = (int) (Math.random() * characters.length);
            result.append(characters[rand]);
        }
        return result.toString();
    }

    public static String getGameStateObjectsVersion(JSONObject objects)
            throws JSONException {
        return objects.has("ver") ? objects.getString("ver") : null;
    }

    public static String getGameStateVersion(JSONObject gameState)
            throws JSONException {
        return gameState.has("ver") ? gameState.getString("ver") : null;
    }

    public static boolean IsEmailValid(String emailStr) {
        if (emailStr == null || emailStr.isEmpty()) {
            return false;
        }
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.find();
    }

    public static boolean IsFileExists(String filePath) {
        return (new File(filePath)).exists();
    }

    public static String MD5Checksum(String input)
            throws NoSuchAlgorithmException {
        StringBuffer sb = new StringBuffer();
        MessageDigest messageDigest = null;
        messageDigest = MessageDigest.getInstance("MD5");
        messageDigest.update(input.getBytes());
        byte[] mdbytes = messageDigest.digest();
        for (int i = 0; i < mdbytes.length; i++) {
            sb.append(Integer.toString((mdbytes[i] & 0xff) + 0x100, 16)
                    .substring(1));
        }
        return sb.toString();
    }

    public static String MD5Encode(String input, String salt)
            throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        byte[] hash = null;
        messageDigest.update(salt.getBytes("UTF-8"));
        messageDigest.update(input.getBytes("UTF-8"));
        hash = messageDigest.digest();
        if (hash != null) {
            StringBuilder output = new StringBuilder(2 * hash.length);
            for (byte b : hash) {
                output.append(String.format("%02x", b & 0xff));
            }
            return output.toString();
        }
        return null;
    }

    public static int[] parserVersionString(String ver) {
        if (ver == null) {
            return null;
        }
        if (ver.isEmpty()) {
            return new int[] { 0 };
        }
        String[] verArray = ver.split("\\.");
        int[] result = new int[verArray.length];
        for (int i = 0; i < verArray.length; i++) {
            result[i] = 0;
        }
        for (int i = 0; i < verArray.length; i++) {
            try {
                result[i] = Integer.parseInt(verArray[i]);
                if (result[i] < 0) {
                    result[i] = 0;
                }
            } catch (Exception e) {
                result[i] = 0;
            }
        }
        return result;
    }

    public static String readFile(String filePath) throws IOException {
        BufferedReader bufferedReader = null;
        String output = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(filePath));
            StringBuilder stringBuilder = new StringBuilder();
            String line = bufferedReader.readLine();
            while (line != null) {
                stringBuilder.append(line);
                stringBuilder.append("\n");
                line = bufferedReader.readLine();
            }
            output = stringBuilder.toString();
        } catch (Exception e) {
            output = null;
        } finally {
            if (bufferedReader != null) {
                bufferedReader.close();
            }
        }
        return output;
    }

    public static byte[] stringToBytesArray(String input)
            throws UnsupportedEncodingException {
        return input.getBytes("UTF-8");
    }

    public static JSONObject rewriteClanUnits(String clientGameStateStr, String serverGameStateStr) throws JSONException{
        JSONObject clientGameState = new JSONObject(clientGameStateStr);
        JSONObject clientClanUnits = clientGameState.optJSONObject("clanUnits");
        JSONObject serverGameState = new JSONObject(serverGameStateStr);
        JSONObject serverClanUnits = serverGameState.optJSONObject("clanUnits");
        if (serverClanUnits != null && clientClanUnits != null) {
            int serverOccupied = serverClanUnits.getInt("occupied");
            JSONArray serverUntis = serverClanUnits.getJSONArray("units");
            clientClanUnits.put("occupied", serverOccupied);
            clientClanUnits.put("units", serverUntis);
            clientGameState.put("clanUnits", clientClanUnits);
        }
        return clientGameState;
    }

    public static JSONObject updateGameStateObjects(String gameState,
                                                    String newGameState) throws JSONException {
        JSONObject oldGameStateJson = new JSONObject(gameState);
        JSONArray bds = oldGameStateJson.getJSONObject("Objects").getJSONArray(
                "bds");
        oldGameStateJson.getJSONObject("Objects").remove("bds");
        JSONObject newGameStateJson = new JSONObject(newGameState);
        newGameStateJson.getJSONObject("Objects").put("bds", bds);
        JSONArray rbs = newGameStateJson.getJSONObject("Objects").optJSONArray(
                "rbs");
        newGameStateJson.getJSONObject("Objects").remove("rbs");
        JSONArray cbs = newGameStateJson.getJSONObject("Objects").optJSONArray(
                "cbs");
        newGameStateJson.getJSONObject("Objects").remove("cbs");
        for (int i = 0; i < bds.length();) {
            String uId = bds.getJSONObject(i).getString("uId");
            boolean isRemoved = false;
            if (rbs != null) {
                for (int j = 0; j < rbs.length(); j++) {
                    JSONObject removedTile = rbs.optJSONObject(j);
                    if (uId.equals(removedTile.getString("uId"))) {
                        bds.remove(i);
                        rbs.remove(j);
                        isRemoved = true;
                        break;
                    }
                }
            }
            if (isRemoved) {
                continue;
            }
            if (cbs != null) {
                for (int j = 0; j < cbs.length(); j++) {
                    JSONObject changedTile = cbs.optJSONObject(j);
                    if (uId.equals(changedTile.getString("uId"))) {
                        bds.put(i, changedTile);
                        cbs.remove(j);
                        break;
                    }
                }
            }
            i++;
        }
        JSONArray abs = newGameStateJson.getJSONObject("Objects").optJSONArray(
                "abs");
        newGameStateJson.getJSONObject("Objects").remove("abs");
        if (abs != null) {
            for (int i = 0; i < abs.length(); i++) {
                bds.put(abs.getJSONObject(i));
            }
        }
        return newGameStateJson;
    }

    public static final int GAME_STATE_FORMAT_INVALID_FORMAT = -2;
    public static final int GAME_STATE_FORMAT_UNKNOWN = -1;
    public static final int GAME_STATE_FORMAT_BEFORE_1_9_3 = 0;
    public static final int GAME_STATE_FORMAT_AFTER_1_9_3 = 1;
    public static final int GAME_STATE_FORMAT_OBJECTS_BEFORE_1_9_3 = 2;
    public static final int GAME_STATE_FORMAT_OBJECTS_AFTER_1_9_3 = 3;
    public static final int GAME_STATE_FORMAT_TILE_BEFORE_1_9_3 = 4;
    public static final int GAME_STATE_FORMAT_TILE_AFTER_1_9_3 = 5;
    private static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile(
            "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
            Pattern.CASE_INSENSITIVE);
    private static String[] characters = { "A", "B", "C", "D", "E", "F", "G",
            "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T",
            "U", "V", "W", "X", "Y", "Z", "0", "1", "2", "3", "4", "5", "6",
            "7", "8", "9" };
    private static String[] alphanumeric = { "A", "B", "C", "D", "E", "F", "G",
            "H", "I", "J", "K", "L", "M", "N", "P", "Q", "R", "S", "T", "U",
            "V", "W", "X", "Y", "Z", "1", "2", "3", "4", "5", "6", "7", "8",
            "9" };
    private static String[] alphabetic = { "A", "B", "C", "D", "E", "F", "G",
            "H", "I", "J", "K", "L", "M", "N", "P", "Q", "R", "S", "T", "U",
            "V", "W", "X", "Y", "Z" };
}