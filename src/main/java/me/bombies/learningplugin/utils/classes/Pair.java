package me.bombies.learningplugin.utils.classes;

public class Pair<A,B> {
    private final A a;
    private final B b;

    private Pair(A a, B b) {
        this.a = a;
        this.b = b;
    }

    public static <A,B> Pair<A,B> of(A a, B b) {
        return new Pair<>(a, b);
    }

    public A getLeft() {
        return a;
    }

    public B getRight() {
        return b;
    }
}
