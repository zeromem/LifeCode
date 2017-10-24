import akka.actor.ActorSystem;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

import java.util.List;
import java.util.stream.Collectors;

import static org.zeromem.lifecode.paxos.Constants.AKKA_SYS_PAXOS_PREFIX;

/**
 * @author zeromem
 * @date 2017/10/24
 */
public class RandomTest {
    public static void main(String[] args) {
        ActorSystem system = ActorSystem.create();
        Config config = ConfigFactory.load();
        List<String> learners = config.getStringList("learners");
        final Integer port = config.getInt("learner.port");
        List collect = learners.stream().map(host -> {
            String sb = AKKA_SYS_PAXOS_PREFIX +
                    host +
                    ":" +
                    port +
                    "/user/learner-*";
            return system.actorSelection(sb);
        }).collect(Collectors.toList());
        System.out.println(collect);
    }
}
