import akka.actor.ActorRef;
import akka.actor.ActorSelection;
import akka.actor.ActorSystem;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;


/**
 * Created by zeromem on 2017/10/11.
 */
public class ActorPathTest {
	public static void main(String[] args) throws InterruptedException {
		Config config = ConfigFactory.load().getConfig("test");
		System.out.println(config);
		ActorSystem system = ActorSystem.create("paxos", config);

		ActorSelection proposer = system.actorSelection("akka.tcp://paxos@10.9.59.151:2552/user/proposer-1");

		for (int i = 0; i < 10; i++) {
			proposer.tell("hello world", ActorRef.noSender());
			Thread.sleep(3000);
		}

		system.terminate();

	}
}
