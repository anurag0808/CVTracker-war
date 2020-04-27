package com.kushwaha.Covid19Tracker.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

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

}
