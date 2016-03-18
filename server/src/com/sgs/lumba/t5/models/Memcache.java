package com.sgs.lumba.t5.models;

import java.util.HashSet;

/**
 * Created by rypon on 2/22/16.
 */
public class Memcache {
    public static final HashSet<String> listPlayers = new HashSet<String>();
    public static final HashSet<MatchState> listMatches = new HashSet<MatchState>();
}
