package com.example;

/**
 * Created by suresh on 1/1/15.
 */
// Java code for JavaBotMain.java

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

public class JavaBotMain {

	public static void main(String[] args) {
		// Create the 'helloakka' actor system
		final ActorSystem system = ActorSystem.create("helloakka");

		// Create the 'AkkaBot' actor
		final ActorRef akkaBot = system.actorOf(Props.create(JavaBotMaster.class), "JavaBotMaster");

		System.out.println("JavaBotMain Actor System was created");
		final AllUrls allUrls = new AllUrls();
		allUrls.addUrl("http://209.237.251.142:9558/api/trafficStatus/get");
		allUrls.addUrl("http://209.237.251.130:9558/api/trafficStatus/get");


		//system.scheduler().scheduleOnce(Duration.create(1, TimeUnit.SECONDS),akkaBot, new JavaBotMaster.BatchDownload(allUrls),new ExecutionContextExecutorService(), ActorRef.noSender());
	akkaBot.tell(new JavaBotMaster.BatchDownload(allUrls), ActorRef.noSender());



	}

}