package com.example;

/**
 * Created by suresh on 2/1/15.
 */

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;

import java.util.HashMap;

public class JavaBotMaster extends UntypedActor {
public static HashMap<String,Long> totalResults = new HashMap<String, Long>();
public HashMap<String,Long> tempTotalResults = new HashMap<String, Long>();
public int actorsWorking =0;
	public HashMap<String,Long> mergeCounts(HashMap<String,Long> mainResult , HashMap<String,Long> serverResult){

		for(String key: serverResult.keySet()){
			Long temp;
			if((temp = mainResult.get(key))==null){
				mainResult.put(key,serverResult.get(key));
			}
			else{
				mainResult.put(key,temp+serverResult.get(key));
			}
		}
		return  mainResult;

	}
	public JavaBotMaster() {
		for (int indx = 0; indx < 10; indx++) {
			//System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAA");
			context().actorOf(Props.create(JavaAkkaBot.class));
		}
	}

	public void onReceive(Object message) {

		if (message instanceof BatchDownload) {
			int i=0;
			this.tempTotalResults = new HashMap<String, Long>();
			for (ActorRef child : getContext().getChildren()) {
				if(((BatchDownload) message).allUrls.allUrls.size()>i)
				{
					child.tell(new JavaAkkaBot.Url(((BatchDownload) message).allUrls.allUrls.get(i)), getContext().self());
					actorsWorking = actorsWorking +1;
				}
				i++;
			}
			System.out.println("Master has passed urls");
		}
		else if(message instanceof CountryResults){
			actorsWorking = actorsWorking - 1;
			this.tempTotalResults = mergeCounts(this.tempTotalResults,((CountryResults) message).getCountryResults());
			if(actorsWorking == 0){
				totalResults = this.tempTotalResults;
				System.out.println("IN total bids:"+totalResults.get("IN"));
			}

		}
		else {
			unhandled(message);
		}
	}
	public static class CountryResults{
		public HashMap<String, Long> countryResults = new HashMap<String, Long>();

		public CountryResults (HashMap<String, Long> countryResults) {
			this.countryResults = countryResults;
		}

		public HashMap<String, Long> getCountryResults () {
			return countryResults;
		}

		public void setCountryResults (HashMap<String, Long> countryResults) {
			this.countryResults = countryResults;
		}
	}

	public static class BatchDownload {
		public AllUrls allUrls;
		public BatchDownload(AllUrls allUrls){
			this.allUrls = allUrls;
		}
	}
}