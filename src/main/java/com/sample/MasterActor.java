package com.sample;

import akka.actor.ActorRef;
import akka.actor.UntypedActor;

import static com.sample.GuiceExtension.GuiceExtProvider;

/**
 * Master Actor of application which acts as root supervisor for actors
 */
public class MasterActor extends UntypedActor {

    public static final String MASTER_ACTOR_NAME = "masterActor";

    ActorRef countingActor = getContext().actorOf(GuiceExtProvider.get(getContext().system()).props(CountingActor.class), "countingActor");

    @Override
    public void onReceive(Object message) throws Exception {
        if (message instanceof CountingActor.Count) {
            countingActor.forward(message, context());
        } else if (message instanceof CountingActor.Get) {
            countingActor.forward(message, context());
        } else {
            unhandled(message);
        }
    }
}
