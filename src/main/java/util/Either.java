package util;

import java.util.function.Function;

/**
 * The <code>Either</code> type represents a value of one of two possible types (a disjoint union).
 * The data constructors; <code>Left</code> and <code>Right</code> represent the two possible
 * values. The <code>Either</code> type is often used as an alternative to
 * <code>scala.Option</code> where <code>Left</code> represents failure (by convention) and
 * <code>Right</code> is akin to <code>Some</code>.
 */
public abstract class Either<V> {

	public abstract boolean isLeft();

	public abstract boolean isRight();

	public abstract V get();

	public <U> Either<U> map(Function<V, Either<U>> f) {
		return f.apply(get());
	}

	public String toString() {
		return isLeft() ? "Left(" + get() + ")" : "Right(" + get() + ")";
	}

	public static <V> Either<V> left(V v) {
		return new Either.Left<>(v);
	}

	public static <V> Either<V> right(V v) {
		return new Either.Right<>(v);
	}

	public static <T, R> Function<T, Either> lift(CheckedFunction<T, R> function) {
		return v -> {
			try {
				return right(function.apply(v));
			}
			catch (Exception ex) {
				return left(ex);
			}
		};
	}

	private static final class Left<L> extends Either<L> {
		private final L value;

		Left(final L value) {
			this.value = value;
		}

		public boolean isLeft() {
			return true;
		}

		public boolean isRight() {
			return false;
		}

		@Override
		public L get() {
			return value;
		}

	}

	private static final class Right<R> extends Either<R> {
		private final R value;

		Right(final R value) {
			this.value = value;
		}

		public boolean isLeft() {
			return false;
		}

		public boolean isRight() {
			return true;
		}

		@Override
		public R get() {
			return value;
		}

	}

	@FunctionalInterface
	interface CheckedFunction<T, R> {
		R apply(T t) throws Exception;
	}

}