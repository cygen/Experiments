package com.example;

/**
 * Created by suresh on 2/1/15.
 */
import akka.actor.ActorRef;
import akka.actor.UntypedActor;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class JavaAkkaBot extends UntypedActor {

	public static class Url {
		public final String url;

		public String getUrl () {
			return url;
		}
		@Override
		public String toString () {
			return url;
		}
		public Url(String url) {
			this.url = url;
		}
	}

	public void onReceive(Object message) {
		if (message instanceof Url) {
			System.out.println("Downloading "+((Url) message).getUrl());
			try{
				URL url = new URL(((Url) message).getUrl());
				URLConnection urlConnection = null;
				urlConnection = url.openConnection();
				HttpURLConnection connection = null;
				if (urlConnection instanceof HttpURLConnection) {
					connection = (HttpURLConnection) urlConnection;
				} else {
					System.out.println(" HTTP URL????????");
					return;
				}
				BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				JsonResult obj = new JsonResult();
				Gson gson = new Gson();
				obj = gson.fromJson(in,JsonResult.class);
				getSender().tell(new JavaBotMaster.CountryResults(obj.getCountryCounts()), ActorRef.noSender());
			}catch (Exception e){
				e.printStackTrace();
			}
		//	getSender().tell(new C);
		}
		else {
			unhandled(message);
		}
	}
}