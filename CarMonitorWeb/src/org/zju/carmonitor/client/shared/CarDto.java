package org.zju.carmonitor.client.shared;

public class CarDto extends DtoAbstract{
	
	private String departmentId;
	private String terminalId;
	private String driverPhone;
	private String driverName;
	private String carRegNumber;
	private String carType;
	public String getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(String departmentName) {
		this.departmentId = departmentName;
	}
	public String getTerminalId() {
		return terminalId;
	}
	public void setTerminalId(String terminalName) {
		this.terminalId = terminalName;
	}
	public String getCarRegNumber() {
		return carRegNumber;
	}
	public void setCarRegNumber(String carRegNumber) {
		this.carRegNumber = carRegNumber;
	}
	public String getDriverPhone() {
		return driverPhone;
	}
	public void setDriverPhone(String driverPhone) {
		this.driverPhone = driverPhone;
	}
	public String getDriverName() {
		return driverName;
	}
	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}
	public String getCarType() {
		return carType;
	}
	public void setCarType(String carType) {
		this.carType = carType;
	}
	

}
