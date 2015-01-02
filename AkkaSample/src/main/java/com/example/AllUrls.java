package com.example;

import java.util.ArrayList;

/**
 * Created by suresh on 2/1/15.
 */
public class AllUrls {
	public ArrayList<String> allUrls;
	public AllUrls(){
		allUrls = new ArrayList<String>();
	}
	public void addUrl(String url){
		allUrls.add(url);
	}
	public void removeUrl(String url){
		allUrls.remove(url);
	}
}
