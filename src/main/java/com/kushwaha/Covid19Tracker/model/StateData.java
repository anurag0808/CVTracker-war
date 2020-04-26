package com.kushwaha.Covid19Tracker.model;

public class StateData {

	private String active;
	private String confirmed;
	private String deaths;
	private String deltaconfirmed;
	private String deltadeaths;
	private String deltarecovered;
	private String lastupdatedtime;
	private String recovered;
	private String state;
	private String stateCode;

	public StateData() {

	}

	public StateData(String active, String confirmed, String deaths, String deltaconfirmed, String deltadeaths,
			String deltarecovered, String lastupdatedtime, String recovered, String state, String stateCode) {
		super();
		this.active = active;
		this.confirmed = confirmed;
		this.deaths = deaths;
		this.deltaconfirmed = deltaconfirmed;
		this.deltadeaths = deltadeaths;
		this.deltarecovered = deltarecovered;
		this.lastupdatedtime = lastupdatedtime;
		this.recovered = recovered;
		this.state = state;
		this.stateCode = stateCode;
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

	public String getDeaths() {
		return deaths;
	}

	public void setDeaths(String deaths) {
		this.deaths = deaths;
	}

	public String getDeltaconfirmed() {
		return deltaconfirmed;
	}

	public void setDeltaconfirmed(String deltaconfirmed) {
		this.deltaconfirmed = deltaconfirmed;
	}

	public String getDeltadeaths() {
		return deltadeaths;
	}

	public void setDeltadeaths(String deltadeaths) {
		this.deltadeaths = deltadeaths;
	}

	public String getDeltarecovered() {
		return deltarecovered;
	}

	public void setDeltarecovered(String deltarecovered) {
		this.deltarecovered = deltarecovered;
	}

	public String getLastupdatedtime() {
		return lastupdatedtime;
	}

	public void setLastupdatedtime(String lastupdatedtime) {
		this.lastupdatedtime = lastupdatedtime;
	}

	public String getRecovered() {
		return recovered;
	}

	public void setRecovered(String recovered) {
		this.recovered = recovered;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getStateCode() {
		return stateCode;
	}

	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}

	@Override
	public String toString() {
		return "SateWise [active=" + active + ", confirmed=" + confirmed + ", deaths=" + deaths + ", deltaconfirmed="
				+ deltaconfirmed + ", deltadeaths=" + deltadeaths + ", deltarecovered=" + deltarecovered
				+ ", lastupdatedtime=" + lastupdatedtime + ", recovered=" + recovered + ", state=" + state
				+ ", stateCode=" + stateCode + "]";
	}

}
