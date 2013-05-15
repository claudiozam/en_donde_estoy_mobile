package edu.palermo.dondeestoy.bo;

public class FindLocationsResponse extends BaseResponse{

	private Location[] list = null;
	
	public Location[] getList() {
		return list;
	}

	public void setList(Location[] list) {
		this.list = list;
	}
	
}
