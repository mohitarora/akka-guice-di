package com.sample;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;

import static com.sample.GuiceExtension.GuiceExtProvider;

public class GuiceModule extends AbstractModule {

    private static final String ACTOR_SYSTEM = "AkkaJavaGuiceDI";

    @Override
    protected void configure() {
    }

    @Provides
    public ActorSystem actorSystem() {
        ActorSystem actorSystem = ActorSystem.create(ACTOR_SYSTEM);
        // initialize the guice injector in the Akka Guice Extension.
        GuiceExtProvider.get(actorSystem).initialize(GuiceFactory.getInjector());
        return actorSystem;
    }

    @Provides
    public ActorRef countingActor(ActorSystem actorSystem) {
        return actorSystem.actorOf(
                GuiceExtProvider.get(actorSystem).props(CountingActor.class), "directCounter");
    }

}
