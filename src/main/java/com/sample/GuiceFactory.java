package com.sample;

import com.google.inject.Guice;
import com.google.inject.Injector;

public class GuiceFactory {

    private static final Injector inj = Guice.createInjector(new GuiceModule());

    public static Injector getInjector() {
        return inj;
    }
}
