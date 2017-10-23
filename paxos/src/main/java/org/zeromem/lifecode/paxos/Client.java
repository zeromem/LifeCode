package org.zeromem.lifecode.paxos;

import akka.actor.*;
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
import java.util.Scanner;
import java.util.concurrent.TimeoutException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static akka.pattern.Patterns.ask;
import static org.zeromem.lifecode.paxos.Constants.*;

/**
 * @author zeromem
 * @date 2017/10/23
 */
public class Client extends AbstractActor {
    private static Pattern pattern = Pattern.compile("(GET)\\s+(\\w+)\\s*|(SET)\\s+(\\w+)\\s+(\\w+)\\s*");

    private LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);

    private static Props props(final Config config) {
        return Props.create(Client.class, () -> new Client(config));
    }

    private Random random;
    private List<ActorSelection> learners;
    private List<ActorSelection> proposers;
    private Timeout timeout;

    public Client(Config config) {
        random = new Random(47);
        timeout = new Timeout(Duration.create(5, "seconds"));
        init(config);
    }

    private void init(Config config) {
        List<String> learners = config.getStringList("learners");
        final Integer port = config.getInt("learner.port");
        this.learners = learners.stream().map(host -> {
            String sb = AKKA_SYS_PAXOS_PREFIX +
                    host +
                    ":" +
                    port +
                    "/user/learner-*";
            return context().actorSelection(sb);
        }).collect(Collectors.toList());

        List<String> proposers = config.getStringList("proposers");
        final Integer port2 = config.getInt("proposer.port");
        this.proposers = proposers.stream().map(host -> {
            String sb = AKKA_SYS_PAXOS_PREFIX +
                    host +
                    ":" +
                    port2 +
                    "/user/proposer-*";
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

        builder.match(Message.ClientRequest.class, request -> {
            randomProposer().tell(request, self());
        });
        return null;
    }

    public ActorSelection randomLearner() {
        return learners.get(random.nextInt() % learners.size());
    }

    public ActorSelection randomProposer() {
        return proposers.get(random.nextInt() % proposers.size());
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

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String line = scanner.nextLine();
            Matcher matcher = pattern.matcher(line);
            if (matcher.find()) {
                String get = matcher.group(1);
                String set = matcher.group(3);
                if ("GET".equalsIgnoreCase(get)) {
                    String key = matcher.group(2);
                    client.tell(new Message.Fetch(key), ActorRef.noSender());
                } else if ("SET".equalsIgnoreCase(set)) {
                    String key = matcher.group(4);
                    String value = matcher.group(5);
                    client.tell(new Message.ClientRequest(key, Value.of(value)), ActorRef.noSender());
                }
            }
        }
    }
}
