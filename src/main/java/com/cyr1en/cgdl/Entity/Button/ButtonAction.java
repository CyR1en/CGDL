package com.cyr1en.cgdl.Entity.Button;

import java.util.function.Consumer;

public class ButtonAction<T> {
    private T value;

    public ButtonAction(T t) {
        value = t;
    }

    public void process(Consumer<T> consumer) {
        consumer.accept(value);
    }
}
