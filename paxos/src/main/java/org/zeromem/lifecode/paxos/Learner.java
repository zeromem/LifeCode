package org.zeromem.lifecode.paxos;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import akka.japi.pf.ReceiveBuilder;
import akka.remote.DisassociatedEvent;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import com.typesafe.config.ConfigValueFactory;
import org.zeromem.lifecode.paxos.message.Message;

import java.util.HashMap;

import static org.zeromem.lifecode.paxos.Constants.LITERAL_LEARNER;
import static org.zeromem.lifecode.paxos.Constants.LITERAL_ROLE;

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
            log.info("An new Decision. [{}]", decide);
            map.put(decide.key, decide.value);
        });

        builder.match(Message.Fetch.class, fetch -> {
            sender().tell(map.getOrDefault(fetch.key, Value.NULL), self());
        });

        builder.match(DisassociatedEvent.class, event -> {
            log.warning(event.toString());
        });
        return builder.build();
    }

    public static void main(String[] args) {
        Config rawConf = ConfigFactory.load().getConfig("paxos");
        String role = rawConf.getString(LITERAL_ROLE);
        if (!LITERAL_LEARNER.equals(role)) {
            throw new IllegalStateException(
                    "only learner can launch Learner process! set [role] to learner in application.conf");
        }
        Integer id = rawConf.getInt("id");
        Integer port = rawConf.getInt(role + ".port");
        Config config = rawConf.withValue("akka.remote.netty.tcp.port", ConfigValueFactory.fromAnyRef(port));

        ActorSystem system = ActorSystem.create("paxos", config);
        ActorRef learner = system.actorOf(Learner.props(), "learner-" + id);
    }
}
