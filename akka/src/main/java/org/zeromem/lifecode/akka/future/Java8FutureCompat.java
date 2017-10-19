package org.zeromem.lifecode.akka.future;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import scala.concurrent.duration.Duration;

import java.util.concurrent.TimeUnit;

/**
 * @author zeromem
 * @date 2017/10/19
 */
public class Java8FutureCompat {
    public static void main(String[] args) {
        ActorSystem system = ActorSystem.create();
        /*system.actorSelection("/user/actor").resolveOne(Duration.create(1, TimeUnit.SECONDS)).onSuccess(
                ref -> {
                    System.out.println(ref);
                },
                system.dispatcher()
        );*/
    }
}
