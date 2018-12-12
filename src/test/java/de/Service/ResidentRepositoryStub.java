package de.Service;

import java.util.ArrayList;
import java.util.List;

import de.Domain.Resident;
import de.Repository.ResidentRepository;

public class ResidentRepositoryStub implements ResidentRepository{
	private List<Resident> residentList = new ArrayList<Resident>();
	
	public void newResident(Resident resident) {
		residentList.add(resident);
	
					
	}

	@Override
	public List<Resident> getResidents() {
		return residentList ;
	}
	

}
