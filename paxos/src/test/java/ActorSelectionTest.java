import akka.actor.ActorPath;
import akka.actor.ActorSelection;
import akka.actor.ActorSystem;
import akka.actor.Address;
import com.typesafe.config.ConfigFactory;
import org.junit.Test;

/**
 * Created by zeromem on 2017/10/12.
 */
public class ActorSelectionTest {
	public static void main(String[] args) {
		ActorSystem system = ActorSystem.create("test", ConfigFactory.load().getConfig("paxos.acceptor"));
		String host = "10.9.59.151";
		int port = 2553;
		ActorSelection selection = system.actorSelection(String.format("akka.tcp://paxos@%s:%d/user/acceptor-*", host, port));
		System.out.println(selection);
	}

	@Test
	public void testAddress() {
		Address address = new Address("tcp", "paxos", "10.9.59.151", 2553);
		System.out.println(address);
	}
}
