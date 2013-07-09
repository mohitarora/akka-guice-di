package com.sample;

import akka.actor.ActorRef;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Key;
import com.google.inject.name.Names;

public class GuiceFactory {

    private static final Injector inj = Guice.createInjector(new GuiceModule());

    public static Injector getInjector() {
        return inj;
    }

    /**
     * Return the actor by @Named annotation
     *
     * @param actorName - value of @Named annotation
     * @return reference of Actor
     */
    public static ActorRef getActor(String actorName) {
        return getInjector().getInstance(Key.get(ActorRef.class, Names.named(actorName)));
    }
}
