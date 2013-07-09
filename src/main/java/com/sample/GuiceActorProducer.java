package com.sample;

import akka.actor.Actor;
import akka.actor.IndirectActorProducer;
import com.google.inject.Injector;
import com.google.inject.Key;
import com.google.inject.TypeLiteral;

import java.lang.reflect.Type;

/**
 * An actor producer that lets Google Guice create the Actor instances.
 */
public class GuiceActorProducer implements IndirectActorProducer {

    private final Injector injector;

    private final Type actorType;

    public GuiceActorProducer(Injector injector, Type actorType) {
        this.injector = injector;
        this.actorType = actorType;
    }


    @Override
    public Actor produce() {
        return (Actor) injector.getInstance(Key.get(TypeLiteral.get(actorType)));
    }

    @Override
    public Class<? extends Actor> actorClass() {
        return (Class<Actor>) actorType;
    }
}
