import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import org.junit.Test;

import java.util.List;

/**
 * Created by zeromem on 2017/10/11.
 */
public class ConfigLoadTest {
	@Test
	public void load() {
		Config config = ConfigFactory.load().getConfig("paxos");
		List<String> acceptors = config.getStringList("acceptor");
		System.out.println(acceptors);

	}

	@Test
	public void override() {
		Config config = ConfigFactory.load().getConfig("paxos");
		System.out.println(config);


	}
}
