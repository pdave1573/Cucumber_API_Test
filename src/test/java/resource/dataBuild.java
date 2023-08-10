package resource;

import java.util.ArrayList;
import java.util.List;

import pojo.locationDetails;
import pojo.locationPostPayload;

public class dataBuild {

	public locationPostPayload addPlaceDataBuild() {
		// BODY
		locationPostPayload postData = new locationPostPayload();
		postData.setAccuracy(50);
		postData.setName("Frontline house");
		postData.setPhone_number("(+91) 983 893 3937");
		postData.setAddress("29, side layout, cohen 09");

		List<String> types = new ArrayList<String>();
		types.add("shoe park");
		types.add("shop");
		postData.setTypes(types);

		postData.setWebsite("http://google.com");
		postData.setLanguage("French-IN");

		locationDetails l = new locationDetails();
		l.setLat(-38.383494);
		l.setLng(33.427362);
		postData.setLocation(l);
		
		return postData;
	}

}
