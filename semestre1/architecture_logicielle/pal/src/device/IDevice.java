package device;

public interface IDevice {
	// Define the value of one setting of the device :
	public void setSettingValue(String settingName, String settingValue);
	
	// Read the value of one setting of the device :
	public String getSettingValue(String settingName);	
}
