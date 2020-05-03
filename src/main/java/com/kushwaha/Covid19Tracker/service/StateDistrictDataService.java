package com.kushwaha.Covid19Tracker.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import javax.annotation.PostConstruct;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kushwaha.Covid19Tracker.model.DistrictData;
import com.kushwaha.Covid19Tracker.util.ApplicationProperties;

@Service
public class StateDistrictDataService {

	private RestTemplate restTemplate = new RestTemplate();

	private final ObjectMapper mapper = new ObjectMapper();

	private List<DistrictData> districtDataList = new ArrayList<DistrictData>();

	private Map<String, List<DistrictData>> stateDistrictMap = new HashMap<String, List<DistrictData>>();

	// private List<Map<String, List<DistrictData>>> stateMap = new

	private Enumeration e = null;
	private static Properties prop = null;

	static {

		prop = ApplicationProperties.INSTANCE.getProperties();
			
	
	}

	@PostConstruct
//	@Scheduled(cron = "0 */20 * * * *")
	public void getSateDistrictData() {

		String jsonString = restTemplate.getForObject("https://api.covid19india.org/state_district_wise.json",
				String.class);

		String state1 = "Maharashtra";

		// System.out.println(prop.size());
		//System.out.println(prop.stringPropertyNames());

		List<DistrictData> districtDataListLatest = new ArrayList<DistrictData>();

		try {

			JsonNode root = mapper.readTree(jsonString);

			e = prop.propertyNames();
			while (e.hasMoreElements()) {
				String stateCode = (String) e.nextElement();
				String stateName = prop.getProperty(stateCode);
				// System.out.println("Key : " + key + ", Value : " + value);

				JsonNode stateNode = root.path(stateName);

				Iterator<String> fieldNames = stateNode.fieldNames();

				while (fieldNames.hasNext()) {
					
					List<DistrictData> districtDataListLatest2 = new ArrayList<DistrictData>();

					String fieldName = fieldNames.next();
					if (fieldName == "districtData") {

						JsonNode districtnode = stateNode.get(fieldName);

						Iterator<String> districtfields = districtnode.fieldNames();

						while (districtfields.hasNext()) {

							DistrictData distData = new DistrictData();

							String districtName = districtfields.next();

							// System.out.println("fieldName222222" + districtName);

							JsonNode districtData = districtnode.get(districtName);

							distData.setDistrictName(districtName);
							distData.setActive(districtData.path("active").asText());
							distData.setConfirmed(districtData.get("confirmed").asText());
							distData.setDeceased(districtData.get("deceased").asText());
							distData.setRecovered(districtData.get("recovered").asText());

							districtDataListLatest2.add(distData);
							
							stateDistrictMap.put(stateName, districtDataListLatest2);

						
							// System.out.println("fielddata2222222" + districtData);
						}
					}

					//System.out.println("Districtdatalist size>>>>>>>" + districtDataListLatest.size());

					/*System.out.println("fieldName test::::" + fieldName);

					JsonNode field = stateNode.get(fieldName);

					System.out.println("field test::::" + field);*/
				}

				this.districtDataList = districtDataListLatest;

				


			}
			

			
			/*
			 * Iterator<Entry<String, List<DistrictData>>> it =
			 * stateDistrictMap.entrySet().iterator(); while (it.hasNext()) {
			 * Map.Entry<String, List<DistrictData>> pair = (Map.Entry<String,
			 * List<DistrictData>>) it.next(); System.out.println(pair.getKey() + " = " +
			 * pair.getValue()); }
			 */
			
			/*
			 * for (Map.Entry<String, List<DistrictData>> state :
			 * stateDistrictMap.entrySet()) {
			 * 
			 * String localState= state.getKey(); System.out.println("Staename>>>>>>" + "  "
			 * + localState);
			 * 
			 * List<DistrictData> dstList1 = state.getValue();
			 * 
			 * for (DistrictData distUnique : dstList1) {
			 * 
			 * System.out.println("Districtnames" + "  " + distUnique.getDistrictName());
			 * 
			 * }
			 * 
			 * //System.out.println("state name  >>>>>>>>>>" + state.getKey() + " " +
			 * "Districts names <<<<<" + state.getValue());
			 * 
			 * }
			 */
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// return districtDataList;

	}

	/*
	 * public List<DistrictData> getDistrictDataList(String state) {
	 * 
	 * districtDataList = getSateDistrictData(state);
	 * 
	 * return districtDataList; }
	 */

	public List<DistrictData> getDistrictDataList() {

		return districtDataList;
	}

	public void setDistrictDataList(List<DistrictData> districtDataList) {
		this.districtDataList = districtDataList;
	}

	public Map<String, List<DistrictData>> getStateDistrictMap() {
		return stateDistrictMap;
	}

	public void setStateDistrictMap(Map<String, List<DistrictData>> stateDistrictMap) {
		this.stateDistrictMap = stateDistrictMap;
	}

}
