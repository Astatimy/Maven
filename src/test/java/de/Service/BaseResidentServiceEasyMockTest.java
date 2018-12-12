package de.Service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.easymock.EasyMock;
import org.hamcrest.MatcherAssert;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.hamcrest.Matchers.is;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import de.Domain.Resident;
import de.Repository.ResidentRepository;

public class BaseResidentServiceEasyMockTest {
	
	MatcherAssert Assert= new MatcherAssert();

	BaseResidentService baseResidentService= new BaseResidentService();
	Resident testResident1 = new Resident("James", "Morhisson" ,"Freiburgerstrasse", "Freiburg", new Date(316828800));
	Resident testResident2= new Resident("Laura", "Morer", "street","city", null);
	Resident testResident3 =new Resident ("Jada", "Gebert" ,"Freiburgerstrasse", "Freiburg", new Date(3168288) );
	Resident testResident4 = new Resident ("Jamy", "Moore" ,"Freiburg", "Freiburg", new Date(3168288));
	Resident testResident5 =new Resident ("Mira", "Gebert" ,"bertolstrasse", "Furtwangen", new Date(3168288) );
	List<Resident> residentList= new ArrayList<Resident>();
	
	ResidentRepository residentRepositoryMock = EasyMock.createMock(ResidentRepository.class);
	
	@Rule
	public ExpectedException exception= ExpectedException.none();
	
	@Before
	public void init() {
		residentList.add(testResident1);
		residentList.add(testResident2);
		residentList.add(testResident3);
		residentList.add(testResident4);
		residentList.add(testResident5);
		EasyMock.expect(residentRepositoryMock.getResidents()).andReturn(residentList);
		EasyMock.replay(residentRepositoryMock);
		baseResidentService.setResidentRepository(residentRepositoryMock);
	}
	
	@Test
	public void testGetFilteredResidentList_filterdResidentFoundWithName() {
		Resident filterResident= new Resident();
		filterResident.setGivenName("Ja*");
		List<Resident> listResident= baseResidentService.getFilteredResidentsList(filterResident);
		EasyMock.verify(residentRepositoryMock);
		MatcherAssert.assertThat(listResident,is(notNullValue()));
		MatcherAssert.assertThat(listResident, hasSize(3));
		assertTrue(listResident.contains(testResident3));
		assertTrue(listResident.contains(testResident4));
		
	}
	
	@Test
	public void testGetFilteredResidentList_FilterdResidentFoundWithStreet() {
		Resident filterResident= new Resident();
		filterResident.setStreet("Freiburgerstrasse");
		List<Resident> listResident= baseResidentService.getFilteredResidentsList(filterResident);
		EasyMock.verify(residentRepositoryMock);
		MatcherAssert.assertThat(listResident, hasSize(2));
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
