package resource;

public enum apiEndPointResources {
	GetPlaceAPI("get/json/"),
	AddPlaceAPI("add/json/"),
	UpdatePlaceAPI("update/json/"),
	DeletePlaceAPI("delete/json/");
	
	private String resource;
	
	apiEndPointResources(String resource) {
		this.resource = resource;
	}
	
	public String getResource() {
		return resource;
	}
}
