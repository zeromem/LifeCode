package org.zeromem.lifecode.paxos;

import akka.actor.*;

import static akka.pattern.Patterns.ask;

import akka.event.Logging;
import akka.event.LoggingAdapter;
import akka.japi.pf.ReceiveBuilder;
import akka.util.Timeout;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import com.typesafe.config.ConfigValueFactory;
import org.zeromem.lifecode.paxos.message.Message;
import scala.concurrent.Await;
import scala.concurrent.Future;
import scala.concurrent.duration.Duration;

import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeoutException;
import java.util.stream.Collectors;

import static org.zeromem.lifecode.paxos.Constants.*;

/**
 * @author zeromem
 * @date 2017/10/23
 */
public class Client extends AbstractActor {
    private LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);

    private static Props props(final Config config) {
        return Props.create(Client.class, () -> new Client(config));
    }

    private Random random;
    private List<ActorSelection> learners;
    private Timeout timeout;

    public Client(Config config) {
        random = new Random(47);
        timeout = new Timeout(Duration.create(5, "seconds"));
        init(config);
    }

    private void init(Config config) {
        final Integer port = config.getInt("learner.port");

        this.learners = learners.stream().map(host -> {
            String sb = AKKA_SYS_PAXOS_PREFIX +
                    host +
                    ":" +
                    port +
                    "/user/learner-*";
            return context().actorSelection(sb);
        }).collect(Collectors.toList());
    }

    @Override
    public Receive createReceive() {
        ReceiveBuilder builder = ReceiveBuilder.create();
        builder.match(Message.Fetch.class, fetch -> {
            Future<Object> ask = ask(randomLearner(), fetch, timeout);
            try {
                Object result = Await.result(ask, timeout.duration());
                log.info("reply for [{}] - [{}].", fetch, result);
            } catch (TimeoutException te) {
                log.error("wait for [{}] timeout!", fetch);
            }

        });
        return null;
    }

    public ActorSelection randomLearner() {
        return learners.get(random.nextInt() % learners.size());
    }

    public static void main(String[] args) {
        Config rawConf = ConfigFactory.load().getConfig("paxos");
        String role = rawConf.getString(LITERAL_ROLE);
        if (!LITERAL_CLIENT.equals(role)) {
            throw new IllegalStateException(
                    "only client can launch Client process! set [role] to client in application.conf");
        }
        Integer id = rawConf.getInt("id");
        Integer port = rawConf.getInt(role + ".port");
        Config config = rawConf.withValue("akka.remote.netty.tcp.port", ConfigValueFactory.fromAnyRef(port));

        ActorSystem system = ActorSystem.create("paxos", config);
        ActorRef client = system.actorOf(Client.props(config), "client-" + id);
    }

}
