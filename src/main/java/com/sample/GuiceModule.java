package com.sample;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.name.Named;

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

    /**
     * This actor is created so that actor system gets initialized with all the actors.
     *
     * @param actorSystem
     * @return
     */
    @Provides
    @Named(MasterActor.MASTER_ACTOR_NAME)
    @Singleton
    public ActorRef masterActor(ActorSystem actorSystem) {
        return actorSystem.actorOf(
                GuiceFactory.getActorExtension().props(MasterActor.class), MasterActor.MASTER_ACTOR_NAME);
    }

}
