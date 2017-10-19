import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import com.typesafe.config.ConfigValue;
import com.typesafe.config.ConfigValueFactory;
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

	@Test
	public void passon() {
        Config config = ConfigFactory.load().getConfig("paxos");
        Integer port = config.getInt(config.getString("role") + ".port");
        Config merge = config.withValue("akka.remote.netty.tcp.port", ConfigValueFactory.fromAnyRef(port));
        System.out.println(merge);
    }
}
