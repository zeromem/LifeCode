import akka.actor.*;
import akka.dispatch.OnComplete;
import akka.dispatch.OnSuccess;
import akka.japi.pf.ReceiveBuilder;
import akka.util.Timeout;
import com.sun.xml.internal.bind.v2.runtime.reflect.Accessor;
import scala.concurrent.Await;
import scala.concurrent.Future;
import scala.concurrent.duration.Duration;


import java.util.concurrent.TimeoutException;
import java.util.regex.Pattern;

import static akka.pattern.Patterns.ask;

/**
 * @author zeromem
 * @date 2017/10/23
 */
public class WaitForReply {
    public static void main(String[] args) throws Exception {
        ActorSystem system = ActorSystem.create();
        ActorRef actor1 = system.actorOf(Props.create(Ball.class), "actor1");
        Timeout timeout = new Timeout(Duration.create(5, "seconds"));
        Future<Object> future = ask(actor1, "hello", timeout);
        try {

            Object result = Await.result(future, timeout.duration());
            System.out.println("reply from actor: " + result);
        } catch (TimeoutException te) {
            te.printStackTrace();
        }

//        future.onComplete(new OnComplete<Object>() {
//            @Override
//            public void onComplete(Throwable failure, Object success) throws Throwable {
//                if (failure == null) {
//                    System.out.println("actor's reply: " + success);
//                } else {
//                    failure.printStackTrace();
//                }
//            }
//        }, system.dispatcher());

    }

    public static class Ball extends AbstractActor {

        @Override
        public Receive createReceive() {
            ReceiveBuilder builder = ReceiveBuilder.create();
            builder.matchEquals("helo", (s) -> {
                System.out.println("actor received a hello message.");
                sender().tell("hello world", self());
            });
            return builder.build();
        }
    }
}
