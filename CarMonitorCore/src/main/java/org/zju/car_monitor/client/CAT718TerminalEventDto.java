package org.zju.car_monitor.client;


public class CAT718TerminalEventDto extends DtoAbstract{
	
	private String eventType = null;
	private String updatedTime = null;
	private String currentSpeed = null;
	private String currentRpm = null;
	private String currentWaterTemp = null;
	private String currentLatitude = null;

    public String getCurrentLongitude() {
        return currentLongitude;
    }

    public void setCurrentLongitude(String currentLongitude) {
        this.currentLongitude = currentLongitude;
    }

    private String currentLongitude = null;
	
	public String getEventType() {
		return eventType;
	}
	public void setEventType(String eventType) {
		this.eventType = eventType;
	}
	public String getUpdatedTime() {
		return updatedTime;
	}
	public void setUpdatedTime(String updatedTime) {
		this.updatedTime = updatedTime;
	}
	public String getCurrentSpeed() {
		return currentSpeed;
	}
	public void setCurrentSpeed(String currentSpeed) {
		this.currentSpeed = currentSpeed;
	}
	public String getCurrentRpm() {
		return currentRpm;
	}
	public void setCurrentRpm(String currentRpm) {
		this.currentRpm = currentRpm;
	}
	public String getCurrentWaterTemp() {
		return currentWaterTemp;
	}
	public void setCurrentWaterTemp(String currentWaterTemp) {
		this.currentWaterTemp = currentWaterTemp;
	}
	public String getCurrentLatitude() {
		return currentLatitude;
	}
	public void setCurrentLatitude(String currentLatitude) {
		this.currentLatitude = currentLatitude;
	}
	

}
