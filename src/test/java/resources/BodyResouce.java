package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.Location;
import pojo.serialization;

public class BodyResouce{

	public serialization addLocation(String name, String language, String address, String website) {

		serialization s = new serialization();
		s.setAccuracy(50);
		s.setAddress(address);
		s.setLanguage(language);
		s.setName(name);
		s.setPhone_number("08408048541");
		s.setWebsite(website);

		Location l = new Location();
		l.setLat(-34.2343);
		l.setLng(32.342211);
		s.setLocation(l);
		List<String> tList = new ArrayList<String>();
		tList.add("nike park");
		tList.add("ysl shirt");
		s.setTypes(tList);
		return s;

	}
	public String getDeleteBody (String body) {
		return "{\r\n" + 
		"    \"place_id\":\""+body+"\"\r\n" + 
		"}";
		
	}

}
