package de.Service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import de.Domain.Resident;
import de.Repository.ResidentRepository;

public class BaseResidentServiceTest {
	
	BaseResidentService baseResidentService= new BaseResidentService();
	ResidentRepository stub = new ResidentRepositoryStub();
	Resident testResident1 = new Resident("James", "Morhisson" ,"Freiburgerstrasse", "Freiburg", new Date(316828800));
	Resident testResident2= new Resident("Laura", "Morer", "street","city", null);
	Resident testResident3 =new Resident ("Jada", "Gebert" ,"Freiburgerstrasse", "Freiburg", new Date(3168288) );
	Resident testResident4 = new Resident ("Jamy", "Moore" ,"Freiburg", "Freiburg", new Date(3168288));
	Resident testResident5 =new Resident ("Mira", "Gebert" ,"bertolstrasse", "Furtwangen", new Date(3168288) );
	
	@Rule
	public ExpectedException exception= ExpectedException.none();
	
	@Before
	public void init(){
		((ResidentRepositoryStub) stub).newResident(testResident1);
		((ResidentRepositoryStub) stub).newResident(testResident2);
		((ResidentRepositoryStub) stub).newResident(testResident3);
		((ResidentRepositoryStub) stub).newResident(testResident4);
		((ResidentRepositoryStub) stub).newResident(testResident5);
		
		 baseResidentService.setResidentRepository(stub);	 
	}
	
	@Test
	public void testGetFilteredResidentList_filterdResidentFoundWithName() {
		Resident filterResident= new Resident();
		filterResident.setGivenName("Ja*");
		List<Resident> listResident= baseResidentService.getFilteredResidentsList(filterResident);
		assertNotNull(listResident);
		assertEquals(3, listResident.size());
		assertTrue(listResident.contains(testResident1));
		assertTrue(listResident.contains(testResident3));
		assertTrue(listResident.contains(testResident4));
	}
	
	
	@Test
	public void testGetFilteredResidentList_FilterdResidentFoundWithStreet() {
		Resident filterResident= new Resident();
		filterResident.setStreet("Freiburgerstrasse");
		List<Resident> listResident= baseResidentService.getFilteredResidentsList(filterResident);
		assertNotNull(listResident);
		assertEquals(2, listResident.size());
	}
	
	
	
	@Test
	public void testGetFilteredResidentList_filteredResidentFoundWithSecondeName() {
		Resident filterResident= new Resident();
		filterResident.setFamilyName("Mo*");
		List<Resident> listResident= baseResidentService.getFilteredResidentsList(filterResident);
		assertNotNull(listResident);
		assertEquals(3, listResident.size());
	}
	
	@Test
	public void testGetFilteredResidentList_filteredResidentNotFound() {
		Resident filterResident = new Resident();
		filterResident.setGivenName("Peter");
		List<Resident> listResident= baseResidentService.getFilteredResidentsList(filterResident);
		assertNotNull(listResident);
		assertEquals(0, listResident.size());
		assertFalse(listResident.contains(filterResident));
	
	}
	
	@Test
	public void testGetUniqueFilterdResident_moreThan1() throws ResidentServiceException {
	  exception.expect(ResidentServiceException.class);
	  exception.expectMessage("Suchanfrage lieferte kein eindeutiges Ergebnis!");
		Resident filterResident = new Resident();
		Resident result= new Resident();
		filterResident.setFamilyName("Gebert");
		result=baseResidentService.getUniqueResident(filterResident);
	}
	
	@Test
	public void testGetUniqueFilteredResident_Wildcard() throws ResidentServiceException {
		 exception.expect(ResidentServiceException.class);
		  exception.expectMessage("Wildcards (*) sind nicht erlaubt!");
			Resident filterResident = new Resident();
			Resident result= new Resident();
			filterResident.setFamilyName("ja*");
			result=baseResidentService.getUniqueResident(filterResident);
	}
	
	@Test
	public void testGetUniqueFilterdResidentService_uniqueResidentFound() throws ResidentServiceException {
			Resident filterResident = new Resident();
			Resident result= new Resident();
			filterResident.setGivenName("James");
			result=baseResidentService.getUniqueResident(filterResident);
			assertNotNull(result);
			assertEquals(testResident1, result);
	}
	
}
	
