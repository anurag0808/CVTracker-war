package com.kushwaha.Covid19Tracker.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kushwaha.Covid19Tracker.model.DistrictData;
import com.kushwaha.Covid19Tracker.model.StateData;
import com.kushwaha.Covid19Tracker.service.Covid19DataService;
import com.kushwaha.Covid19Tracker.service.StateDistrictDataService;

@Controller
public class HomeController {

	@Autowired
	private Covid19DataService covid19Dataservice;

	@Autowired
	private StateDistrictDataService districtDataService;
	
	private String lastUpdated =null;

	@GetMapping("/")
	public String home(Model model) {

		List<StateData> stateDataList = covid19Dataservice.getStateDataList();
		
		 lastUpdated = covid19Dataservice.getLastUpdated();

		// StateData totalCases = stateDataList.remove(0);

		// List<StateData> stateDataWithoutTotal =
		// stateDataList.stream().skip(1).collect(Collectors.toList());

		model.addAttribute("lastUpdated", lastUpdated);
		model.addAttribute("statesData", stateDataList);
		model.addAttribute("totalCases", covid19Dataservice.getTotalCases());

		System.out.println("from home controller lastUpdated  " +lastUpdated);
		
		return "home";

	}

	@RequestMapping("/getStateDistrictData")
	public String getStateDistrictData(@RequestParam(value = "stateName") String stateName, Model model) {

		System.out.println("Getting data from model form request----->" + stateName);

		Map<String, List<DistrictData>> stateDistrictMap = districtDataService.getStateDistrictMap();

		List<DistrictData> districtDataList = stateDistrictMap.get(stateName);

		for (DistrictData districtData : districtDataList) {
			System.out.println("HomeController>>>>>>>>>>>>" + "  " + districtData.getDistrictName());

		}

		lastUpdated = covid19Dataservice.getLastUpdated();
		model.addAttribute("lastUpdated", lastUpdated);
		model.addAttribute("districtsData", districtDataList);
		model.addAttribute("stateName", stateName);
		System.out.println("from home controller lastUpdated  " +lastUpdated);

		return "districtDetails";
	}

}
