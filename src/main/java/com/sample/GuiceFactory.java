package com.sample;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Key;
import com.google.inject.name.Names;

import static com.sample.GuiceExtension.GuiceExtProvider;

public class GuiceFactory {

    private static final Injector inj = Guice.createInjector(new GuiceModule());

    public static Injector getInjector() {
        return inj;
    }

    /**
     * Return the actor by @Named annotation
     *
     * @return master supervisor Actor
     */
    public static ActorRef getMasterActor() {
        return getInjector().getInstance(Key.get(ActorRef.class, Names.named(MasterActor.MASTER_ACTOR_NAME)));
    }

}
