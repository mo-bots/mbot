package ncdc.bow.util;

import org.springframework.web.client.RestTemplate;

import ncdc.bow.model.GameSettings;

public class ApiUtil {
	
	public static final String URL = "http://localhost:8080";
	
	private static <T> T getForObject(String endpoint, Class<T> responseClass) {
		RestTemplate restTemplate = new RestTemplate();
		return restTemplate.getForObject(endpoint, responseClass);
	}

	public static Integer[][] getMap() {
		return getForObject(URL + "/getMap", Integer[][].class);
	}

	public static GameSettings getGameSettings() {
		return getForObject(URL + "/getGameSettings", GameSettings.class);
	}

}
