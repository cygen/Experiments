package com.example;

import java.util.Arrays;
import java.util.HashMap;

/**
 * Created by suresh on 2/1/15.
 */
public class JsonResult {
		private String[] counterArray;
	public String getCounterArray () {
		return Arrays.toString(counterArray);
	}
	public HashMap<String,Long> getCountryCounts () {
		HashMap<String,Long> retval = new HashMap<String,Long>();
		for(String s: counterArray){
			Long temp;
			if((temp = retval.get(s.split(",")[2]))==null){
				retval.put(s.split(",")[2],Long.parseLong(s.split(",")[5]));
			}
			else{
				retval.put(s.split(",")[2],temp+Long.parseLong(s.split(",")[5]));
			}
		}
		return retval;
	}
}