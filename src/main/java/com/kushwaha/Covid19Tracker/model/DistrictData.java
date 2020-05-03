package com.kushwaha.Covid19Tracker.model;

public class DistrictData {

	private String active;
	private String confirmed;
	private String deceased;
	private String recovered;
	private String districtName;
	
	public DistrictData() {

	}
	
	public DistrictData(String active, String confirmed, String deceased, String recovered, String districtName) {
		super();
		this.active = active;
		this.confirmed = confirmed;
		this.deceased = deceased;
		this.recovered = recovered;
		this.districtName = districtName;
	}

	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	public String getConfirmed() {
		return confirmed;
	}

	public void setConfirmed(String confirmed) {
		this.confirmed = confirmed;
	}

	public String getDeceased() {
		return deceased;
	}

	public void setDeceased(String deceased) {
		this.deceased = deceased;
	}

	public String getRecovered() {
		return recovered;
	}

	public void setRecovered(String recovered) {
		this.recovered = recovered;
	}

	public String getDistrictName() {
		return districtName;
	}

	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}
	
}
