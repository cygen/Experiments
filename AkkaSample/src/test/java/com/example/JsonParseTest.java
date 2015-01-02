package com.example;

import com.google.gson.Gson;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by suresh on 2/1/15.
 */
public class JsonParseTest {
	@Test
	public void parseTest(){
		try{
			URL url = new URL("http://209.237.251.142:9558/api/trafficStatus/get");
			URLConnection urlConnection = null;
			urlConnection = url.openConnection();

		HttpURLConnection connection = null;
		if (urlConnection instanceof HttpURLConnection) {
			connection = (HttpURLConnection) urlConnection;
		} else {
			System.out.println("Please enter an HTTP URL.");
			return;
		}
		BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

		JsonResult obj = new JsonResult();
		Gson gson = new Gson();

		//convert java object to JSON format

		obj = gson.fromJson(in,JsonResult.class);
			System.out.println(obj.getCounterArray());


	}catch (Exception e){
			e.printStackTrace();
		}
	}
}
