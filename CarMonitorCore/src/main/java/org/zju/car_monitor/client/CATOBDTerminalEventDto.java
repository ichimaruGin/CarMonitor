package org.zju.car_monitor.client;


public class CATOBDTerminalEventDto extends DtoAbstract{
	
	private String powerErrMessage = "";
	private String bodyErrMessage = "";
	private String chassisErrMessage = "";
	private String networkErrMessage = "";
	
	public void addMessage(String message)  {
		char m = message.charAt(0);
		switch(m) {
			case 'P': powerErrMessage = powerErrMessage + message + " "; break;
			case 'C': chassisErrMessage = chassisErrMessage + message + " "; break;
			case 'B': bodyErrMessage = bodyErrMessage + message + " "; break;
			case 'U': networkErrMessage = networkErrMessage + message + " "; break;
		}
	}

	public String getPowerErrMessage() {
		return powerErrMessage.toString();
	}

	public String getBodyErrMessage() {
		return bodyErrMessage.toString();
	}

	public String getChassisErrMessage() {
		return chassisErrMessage.toString();
	}

	public String getNetworkErrMessage() {
		return networkErrMessage.toString();
	}
	

}
