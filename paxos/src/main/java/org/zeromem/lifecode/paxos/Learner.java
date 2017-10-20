package org.zeromem.lifecode.paxos;

import akka.actor.AbstractActor;
import akka.actor.Props;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import akka.japi.pf.ReceiveBuilder;
import akka.remote.DisassociatedEvent;
import org.zeromem.lifecode.paxos.message.Message;

import java.util.HashMap;

/**
 * @author zeromem
 * @date 2017/10/20
 */
public class Learner extends AbstractActor {
    private LoggingAdapter log = Logging.getLogger(context().system(), this);

    /** props to create this actor
     *
     */
    static public Props props() {
        return Props.create(Learner.class, () -> new Learner());
    }

    private final HashMap<String, Value> map;

    public Learner() {
        map = new HashMap<>();
    }


    @Override
    public void preStart() throws Exception {
        super.preStart();
        context().system().eventStream().subscribe(self(), akka.remote.DisassociatedEvent.class);
    }

    @Override
    public Receive createReceive() {
        ReceiveBuilder builder = ReceiveBuilder.create();
        builder.match(Message.Decide.class, decide -> {
            // TODO: 2017/10/20 should check validation again here.
            map.put(decide.key, decide.value);
        });

        builder.match(DisassociatedEvent.class, event -> {
            log.warning(event.toString());
        });
        return null;
    }
}
