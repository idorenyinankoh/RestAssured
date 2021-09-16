package resources;

public enum ApiResources {
	AddPlaceAPI("maps/api/place/add/json"),
	GetPlaceAPI("maps/api/place/get/json"),
	DeletePlaceAPI("maps/api/place/delete/json");
	private String resource;
	
	private ApiResources(String path){
		this.resource = path;
	}
	
	public String getResource () {
		return resource;
	}
	

}
