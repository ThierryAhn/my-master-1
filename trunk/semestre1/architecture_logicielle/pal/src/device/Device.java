package device;

import java.util.*;

public class Device implements IDevice {
	Map<String, String> values;

	public Device() {
		values = new HashMap<String, String>();
		
		String section = "general.";
		values.put(section+"name", "SysLink");
		values.put(section+"firmWareVersion", "1.1b");

		section = "lan.eth1.";
		values.put(section+"enabled", "true");
		values.put(section+"negociation", "false");
		values.put(section+"mac", "00:FF:66:11:33");

		section = "lan.eth2.";
		values.put(section+"enabled", "true");
		values.put(section+"negociation", "false");
		values.put(section+"mac", "00:FF:66:22:33");

		section = "lan.eth3.";
		values.put(section+"enabled", "true");
		values.put(section+"negociation", "false");
		values.put(section+"mac", "00:FF:66:33:33");

		section = "lan.eth4.";
		values.put(section+"enabled", "true");
		values.put(section+"negociation", "true");
		values.put(section+"mac", "00:FF:66:4:33");

		values.put("wifi.channel", "2");
		values.put("wifi.ssid", "edu-roam");
		values.put("wifi.rant.enabled", "true");
		values.put("wifi.lant.enabled", "true");
	
		values.put("accesspoint.type", "2");
		values.put("accesspoint.routing.enabled", "true");
		values.put("accesspoint.gateway", "192.168.1.1");
	}
	
	
	// Define the value of one setting of the device :
	public void setSettingValue(String settingName, String settingValue) throws IllegalArgumentException {
		if (values.get(settingName) != null) {
			System.out.println("Device : value of "+settingName+" changed from "+values.get(settingName)+" to "+settingValue);
			values.put(settingName, settingValue);
		}
		else throw new IllegalArgumentException("Device : the key "+settingName+" does not exist.");
	}
	
	// Read the value of one setting of the device :
	public String getSettingValue(String settingName) {
		return values.get(settingName);
	}
}
