package de.Service;

import java.util.List;

import de.Domain.Resident;

/**
 * @author Stefan Betermieux
 */
public interface ResidentService {

  Resident getUniqueResident(Resident filterResident) throws ResidentServiceException;

  List<Resident> getFilteredResidentsList(Resident filterResident);
  List<Resident> addResident(Resident resident);
 // this is a comment

}