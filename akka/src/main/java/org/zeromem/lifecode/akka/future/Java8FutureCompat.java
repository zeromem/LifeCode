package org.zeromem.lifecode.akka.future;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.dispatch.OnSuccess;
import scala.Function1;
import scala.Option;
import scala.PartialFunction;
import scala.concurrent.duration.Duration;
import scala.runtime.BoxedUnit;

import java.util.concurrent.TimeUnit;

/**
 * @author zeromem
 * @date 2017/10/19
 */
public class Java8FutureCompat {
    public static void main(String[] args) {
        ActorSystem system = ActorSystem.create();
        system.actorSelection("/user/actor").resolveOne(Duration.create(1, TimeUnit.SECONDS)).onSuccess(
                new OnSuccess<ActorRef>() {
                    @Override
                    public void onSuccess(ActorRef result) throws Throwable {
                        System.out.println("success.");
                    }
                },
                system.dispatcher()
        );
    }
}
