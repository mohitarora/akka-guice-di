package com.sample;

import akka.actor.ActorRef;
import akka.util.Timeout;
import com.google.inject.Key;
import com.google.inject.name.Named;
import com.google.inject.name.Names;
import org.junit.Before;
import org.junit.Test;
import scala.concurrent.Await;
import scala.concurrent.Future;
import scala.concurrent.duration.FiniteDuration;

import java.util.concurrent.TimeUnit;

import static akka.pattern.Patterns.ask;
import static org.junit.Assert.assertEquals;

public class TestDI {

    private ActorRef counter;

    @Before
    public void setUp() {
        counter = GuiceFactory.getActor("counter");
    }

    @Test
    public void testDependencyInjection() throws Exception {
        // tell it to count three times
        counter.tell(new CountingActor.Count(), null);
        counter.tell(new CountingActor.Count(), null);
        counter.tell(new CountingActor.Count(), null);

        // check that it has counted correctly
        FiniteDuration duration = FiniteDuration.create(3, TimeUnit.SECONDS);
        Future<Object> result = ask(counter, new CountingActor.Get(),
                Timeout.durationToTimeout(duration));
        assertEquals(3, Await.result(result, duration));

    }
}
