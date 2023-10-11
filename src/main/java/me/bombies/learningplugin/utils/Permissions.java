package me.bombies.learningplugin.utils;

public class Permissions {
    private final static String NODE_PREFIX = "learningplugin.";

    public final static String FLY = NODE_PREFIX + "fly";
    public final static String FLY_OTHERS = NODE_PREFIX + "fly.others";

    public final static String NICK = NODE_PREFIX + "nick";
    public final static String NICK_OTHERS = NODE_PREFIX + "nick.others";

    public final static String SET_SPAWN = NODE_PREFIX + "spawn.set";

    public final static String VIEW_WORLDS = NODE_PREFIX + "worlds.view";
    public final static String CREATE_WORLDS = NODE_PREFIX + "worlds.create";

    public final static String INV_SEE = NODE_PREFIX + "invsee";
    public final static String INV_SEE_MOVE = NODE_PREFIX + "invsee.move";

    public final static String TELEPORT_BOW = NODE_PREFIX + "teleportbow";
    public final static String TELEPORT_BOW_OTHERS = NODE_PREFIX + "teleportbow.others";
}
