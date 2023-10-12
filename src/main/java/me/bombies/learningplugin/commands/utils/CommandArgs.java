package me.bombies.learningplugin.commands.utils;

import java.util.List;

public class CommandArgs {

    private final List<String> args;

    protected CommandArgs(List<String> args) {
        this.args = args;
    }

    public String join(CharSequence delimiter) {
        return String.join(delimiter, args);
    }

    public String join(int fromIndex, CharSequence delimiter) {
        return String.join(delimiter, args.subList(fromIndex, args.size()));
    }

    public String join(int fromIndex, int toIndex, CharSequence delimiter) {
        return String.join(delimiter, args.subList(fromIndex, toIndex));
    }

    public String get(int index) {
        return args.get(index);
    }

    public String first() {
        return isEmpty() ? null : args.get(0);
    }

    public String last() {
        return isEmpty() ? null : args.get(args.size() - 1);
    }

    public int length() {
        return args.size();
    }

    public boolean isEmpty() {
        return args.isEmpty();
    }

    public List<String> all() {
        return this.args;
    }

    @Override
    public String toString() {
        return args.toString();
    }
}
