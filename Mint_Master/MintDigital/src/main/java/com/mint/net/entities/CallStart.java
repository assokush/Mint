package com.mint.net.entities;            
 
import java.util.Map;

public class CallStart {

	
	private boolean success;
	private Map<String, String> payloads;
	
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	
	public Map<String, String> getPayloads() {
		return payloads;
	}
	public void setPayloads(Map<String, String> payloads) {
		this.payloads = payloads;
	}
	
}
