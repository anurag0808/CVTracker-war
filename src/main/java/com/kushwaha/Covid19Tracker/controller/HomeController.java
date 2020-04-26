package com.kushwaha.Covid19Tracker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.kushwaha.Covid19Tracker.model.StateData;
import com.kushwaha.Covid19Tracker.service.Covid19DataService;

@Controller
public class HomeController {

	@Autowired
	private Covid19DataService covid19Dataservice;

	@GetMapping("/")
	public String home(Model model) {

		List<StateData> stateDataList = covid19Dataservice.getStateDataList();

	//	StateData totalCases = stateDataList.remove(0);

		//List<StateData> stateDataWithoutTotal = stateDataList.stream().skip(1).collect(Collectors.toList());

		model.addAttribute("statesData", stateDataList);
		model.addAttribute("totalCases", covid19Dataservice.getTotalCases());

		System.out.println(stateDataList.get(0).getState());
		System.out.println(stateDataList.get(0).getConfirmed());
		System.out.println(stateDataList.get(0).getActive());

		// model.addAttribute("Name", "Test");
		return "home";

	}

}
