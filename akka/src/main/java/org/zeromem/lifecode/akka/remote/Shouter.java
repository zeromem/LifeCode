package org.zeromem.lifecode.akka.remote;

import akka.actor.*;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import akka.japi.pf.ReceiveBuilder;
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
		ReceiveBuilder builder = ReceiveBuilder.create();
		return builder.build();
	}

	public static void main(String[] args) throws InterruptedException {
		ActorSystem system = ActorSystem.create("remote", ConfigFactory.load().getConfig("shouter"));
		ActorRef shouter = system.actorOf(Shouter.props(), "shouter");
		ActorSelection echo = system.actorSelection("akka.tcp://remote@10.9.54.111:2552/user/echo");

		Thread.sleep(2000);
		echo.tell(new Message("hello world"), shouter);
	}
}
