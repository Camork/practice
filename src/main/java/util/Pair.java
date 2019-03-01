package util;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class Pair<A, B> {

    public final A first;
    public final B second;

    @NotNull
    public static <A, B> Pair<A,B> create(A first, B second) {
        return new Pair<>(first, second);
    }

    @NotNull
    public static <A, B> NonNull<A,B> createNonNull(@NotNull A first, @NotNull B second) {
        return new NonNull<>(first, second);
    }

    @NotNull
    public static <A, B> Pair<A,B> pair(A first, B second) {
        return new Pair<>(first, second);
    }

    public static <T> T getFirst(@Nullable Pair<T,?> pair) {
        return pair != null ? pair.first : null;
    }

    public static <T> T getSecond(@Nullable Pair<?,T> pair) {
        return pair != null ? pair.second : null;
    }

    private static final Pair EMPTY = create(null, null);

    @SuppressWarnings("unchecked")
    public static <A, B> Pair<A,B> empty() {
        return EMPTY;
    }

    public Pair(A first, B second) {
        this.first = first;
        this.second = second;
    }

    public final A getFirst() {
        return first;
    }

    public final B getSecond() {
        return second;
    }

    @Override
    public final boolean equals(Object o) {
        return o instanceof Pair && Objects.equals(first, ((Pair) o).first) && Objects.equals(second, ((Pair) o).second);
    }

    @Override
    public int hashCode() {
        int result = first != null ? first.hashCode() : 0;
        result = 31 * result+(second != null ? second.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "<"+first+","+second+">";
    }

    public static class NonNull<A, B> extends Pair<A,B> {
        public NonNull(@NotNull A first, @NotNull B second) {
            super(first, second);
        }
    }
}