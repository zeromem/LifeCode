package org.zeromem.lifecode.akka.remote;

import akka.actor.*;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import scala.concurrent.duration.Duration;

import java.util.concurrent.TimeUnit;

/**
 * @author zeromem
 * @date 2017/10/18
 */
public class Shouter extends AbstractActor {
	private LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);

	/** props to create this actor
	 *
	 */
	static public Props props() {
		return Props.create(Shouter.class, Shouter::new);
	}


	@Override
	public void preStart() throws Exception {
		super.preStart();
	}

	@Override
	public Receive createReceive() {
		return null;
	}

	public static void main(String[] args) {
		ActorSystem system = ActorSystem.create("remote", ConfigFactory.load().getConfig("shouter"));
		ActorRef shouter = system.actorOf(Props.create(Shouter.class), "shouter");
		ActorSelection echo = system.actorSelection("akka.tcp://remote@10.9.54.111:2552/user/echo");

		system.scheduler().scheduleOnce(
				Duration.create(5, TimeUnit.SECONDS),
				echo.anchor(),
				new Message("hello world"),
				system.dispatcher(),
				shouter
		);


	}

}
