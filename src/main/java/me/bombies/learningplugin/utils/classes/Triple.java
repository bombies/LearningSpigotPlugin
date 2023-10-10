package me.bombies.learningplugin.utils.classes;

public class Triple<A, B, C> {
    private final A first;
    private final B second;
    private final C third;

    private Triple(A first, B second, C third) {
        this.first = first;
        this.second = second;
        this.third = third;
    }

    public A getLeft() {
        return first;
    }

    public B getMiddle() {
        return second;
    }

    public C getRight() {
        return third;
    }

    public static <A, B, C> Triple<A, B, C> of(A first, B second, C third) {
        return new Triple<>(first, second, third);
    }
}
