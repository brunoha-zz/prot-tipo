package com.github.utils;

import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

/**
 * Based on class
 * https://stackoverflow.com/questions/26162407/is-there-an-equivalent-of-scalas-either-in-java-8
 *
 * @param <L>
 * @param <R>
 */
@SuppressWarnings("unchecked")
public abstract class Either<L, R> {
    public static <L, R> Either<L, R> left(L value) {
        return new Either<L, R>() {
            @Override
            public <T> T map(Function<? super L, ? extends T> lFunc,
                             Function<? super R, ? extends T> rFunc) {
                try {
                    return lFunc.apply(value);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        };
    }

    /**
     * When does not need to pass any parameter. Completes the call passing null as a parameter.
     *
     * @param <L>
     * @param <R>
     * @return
     */
    public static <L, R> Either<L, R> right() {
        return right(null);
    }

    public static <L, R> Either<L, R> right(R value) {
        return new Either<L, R>() {
            @Override
            public <T> T map(Function<? super L, ? extends T> lFunc,
                             Function<? super R, ? extends T> rFunc) {
                try {
                    return rFunc.apply(value);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }

        };
    }

    private Either() {
    }

    public abstract <T> T map(
            Function<? super L, ? extends T> lFunc, Function<? super R, ? extends T> rFunc);

    public <T> Either<T, R> mapLeft(Function<? super L, ? extends T> lFunc) {
        return this.map(t -> left(lFunc.apply(t)), t -> (Either<T, R>) this);
    }

    public <T> Either<L, T> mapRight(Function<? super R, ? extends T> lFunc) {
        return this.map(t -> (Either<L, T>) this, t -> right(lFunc.apply(t)));
    }

    public void apply(Consumer<? super L> lFunc, Consumer<? super R> rFunc) {
        map(consume(lFunc), consume(rFunc));
    }

    private <T> Function<T, Void> consume(Consumer<T> c) {
        return t -> {
            c.accept(t);
            return null;
        };
    }

    @Override
    public boolean equals(Object obj) {
        try {
            Object other = obj.getClass().getDeclaredField("val$value").get(obj);
            Object thizz = this.getClass().getDeclaredField("val$value").get(this);

            if (thizz == null && other == null) return true;
            else
                return thizz.equals(other);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public String toString() {
        try {
            return this.getClass().getDeclaredField("val$value").get(this).toString();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        return super.toString();
    }
}