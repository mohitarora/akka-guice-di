package com.sample;

import akka.actor.ActorRef;
import akka.util.Timeout;
import org.junit.Before;
import org.junit.Test;
import scala.concurrent.Await;
import scala.concurrent.Future;
import scala.concurrent.duration.FiniteDuration;

import java.util.concurrent.TimeUnit;

import static akka.pattern.Patterns.ask;
import static org.junit.Assert.assertEquals;

public class TestDI {

    private ActorRef masterActor;

    @Before
    public void setUp() {
        masterActor = GuiceFactory.getMasterActor();
    }

    @Test
    public void testDependencyInjection() throws Exception {
        // tell it to count three times
        masterActor.tell(new CountingActor.Count(), null);
        masterActor.tell(new CountingActor.Count(), null);
        masterActor.tell(new CountingActor.Count(), null);

        // check that it has counted correctly
        FiniteDuration duration = FiniteDuration.create(3, TimeUnit.SECONDS);
        Future<Object> result = ask(masterActor, new CountingActor.Get(),
                Timeout.durationToTimeout(duration));
        assertEquals(3, Await.result(result, duration));

    }
}
