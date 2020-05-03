package com.kushwaha.Covid19Tracker.service;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import javax.annotation.PostConstruct;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kushwaha.Covid19Tracker.model.StateData;

@Service
public class Covid19DataService {

	private RestTemplate restTemplate = new RestTemplate();

	private final ObjectMapper mapper = new ObjectMapper();

	private List<StateData> stateDataList = new ArrayList<StateData>();

	private StateData totalCases;
	
	private List<StateData> stateDataWithoutTotal;

	private Map<String, StateData> stateMap = new HashMap<String, StateData>();
	
	private String lastUpdated = null;

	@PostConstruct
	@Scheduled(cron = "0 */15 * * * *")
	public void getCovid19Data() {
		String jsonString = restTemplate.getForObject("https://api.covid19india.org/data.json", String.class);

		try {
			JsonNode root = mapper.readTree(jsonString);

			JsonNode statesData = root.path("statewise");

			List<StateData> stateDataLatest = new ArrayList<StateData>();
			
			
			for (JsonNode node : statesData) {

				StateData stateData = new StateData();
				stateData.setActive(node.path("active").asText());
				stateData.setConfirmed(node.path("confirmed").asText());
				stateData.setDeaths(node.path("deaths").asText());
				stateData.setDeltaconfirmed(node.path("deltaconfirmed").asText());
				stateData.setDeltadeaths((node.path("deltadeaths").asText()));
				stateData.setDeltarecovered(node.path("deltarecovered").asText());
				stateData.setLastupdatedtime((node.path("lastupdatedtime").asText()));
				stateData.setRecovered((node.path("recovered").asText()));
				stateData.setState((node.path("state").asText()));
				stateData.setStateCode((node.path("statecode").asText()));

				stateDataLatest.add(stateData);
										
				stateMap.put((node.path("statecode").asText()), (StateData) stateData);

			}

			this.stateDataList = stateDataLatest;

			this.totalCases = stateDataList.remove(0);
			
			System.out.println("Last updated !!!!!!!!!!!" + "" +  lastUpdatedTime());
			
			/*
			 * int totalDelta = 0;
			 * 
			 * for (StateData state : stateDataList) {
			 * 
			 * int delta = Integer.parseInt(state.getDeltaconfirmed()); totalDelta =
			 * totalDelta + delta;
			 * 
			 * }
			 * 
			 * System.out.println("Cases Reporte dtoday --------" + totalDelta);
			 */

		// this will give you remaining list after removing 1st element 
		//	this.stateDataWithoutTotal = stateDataList.stream().skip(1).collect(Collectors.toList());

			System.out.println("Satrt time -->" + new Date());	
			
			System.out.println(stateDataList.get(1).getState());
			System.out.println("Confirmed cases :  " + stateDataList.get(1).getConfirmed());

			System.out.println(stateDataList.size());
			System.out.println(stateMap.size());

			/*
			 * this will print entire map having state list
			 * for (Entry<String, StateData> state : stateMap.entrySet()) {
			 * System.out.println("State code :" + " " + state.getKey() + "," +
			 * "State Data: " + state.getValue());
			 * 
			 * }
			 */

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	
	public String lastUpdatedTime() {
		
		Date d1 = null;
		Date d2 = null;
		String minDiff = null;
		String totalDiff= null;
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		formatter.setTimeZone(TimeZone.getTimeZone("IST"));
		String currentDate =formatter.format(new Date());
		
		try {
			
			 d1= formatter.parse(totalCases.getLastupdatedtime());
			 d2= formatter.parse(currentDate);
			 
			  long diff = d2.getTime() - d1.getTime();
			  long hrsDiff = diff/(1000*60*24)%24;
			  long 	minsDiff = diff/(1000*60)%60;
			   		
			  minDiff = String.valueOf(minsDiff);
			  
			  System.out.println("Difference Mili Secondsssssssss" + diff);
			  
			  System.out.println("Difference in hours >>>>>>>>>" + hrsDiff);
			  
			  
			   	
			   
			   System.out.println("Last updated date from API"  + totalCases.getLastupdatedtime());
			   System.out.println("Current date.........."  + currentDate);
			  
						  
			  if(hrsDiff==0) {
				  lastUpdated = minDiff + " " + "Minutes ago";
				  System.out.println("Total Minutes Differece...IFIFIFIFFIFIFFIIF>>>> " +lastUpdated );
				  return lastUpdated; 
			  }else {
				  totalDiff = hrsDiff + "hrs" + "," + minDiff + "mins";
				 // lastUpdated = totalDiff;
				  lastUpdated = minDiff + " " + "minutes ago";
				  System.out.println("Total time Differece ELSE ELSE......>>>> " +totalDiff );
				  return lastUpdated;
			  }
				  
		
			
		
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return lastUpdated;		
	}

	public List<StateData> getStateDataList() {
		return stateDataList;
	}

	public void setStateDataList(List<StateData> stateDataList) {
		this.stateDataList = stateDataList;
	}

	public Map<String, StateData> getStateMap() {
		return stateMap;
	}

	public void setStateMap(Map<String, StateData> stateMap) {
		this.stateMap = stateMap;
	}

	public StateData getTotalCases() {
		return totalCases;
	}

	public void setTotalCases(StateData totalCases) {
		this.totalCases = totalCases;
	}

	public List<StateData> getStateDataWithoutTotal() {
		return stateDataWithoutTotal;
	}

	public void setStateDataWithoutTotal(List<StateData> stateDataWithoutTotal) {
		this.stateDataWithoutTotal = stateDataWithoutTotal;
	}


	public String getLastUpdated() {
		return lastUpdated;
	}


	public void setLastUpdated(String lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

}
