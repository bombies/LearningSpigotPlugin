package me.bombies.learningplugin.utils.menus;

public enum MenuSize {
    NINE(9),
    EIGHTEEN(18),
    TWENTY_SEVEN(27),
    THIRTY_SIX(36),
    FORTY_FIVE(45),
    FIFTY_FOUR(54);

    private final int size;

    MenuSize(int size) {
        this.size = size;
    }

    public int getSize() {
        return size;
    }
}
