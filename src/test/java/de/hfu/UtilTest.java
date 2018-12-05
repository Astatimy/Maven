package de.hfu;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class UtilTest {
	
	@Rule 
	public ExpectedException exception= ExpectedException.none();
	
	@Test
	public void testIstErstesHalbJeahr_ValidMonth() {
		int month=5;
		assertTrue(Util.istErstesHalbjahr(month));
	}
	
	@Test
	public void testIstErstesHalbJahr_unvalidMonth() {
		int month=8;
		assertFalse(Util.istErstesHalbjahr(month));
	}

	@Test
	public void testIstErstesHalbJahr_MonthInferior1(){
		exception.expect(IllegalArgumentException.class);
		int month=-1;
		boolean erstesHalJahr = Util.istErstesHalbjahr(month);
	}
	
	@Test
	public void testIstErstesHalbJahr_MonthSuperior12() {
		exception.expect(IllegalArgumentException.class);
		//exception.expectMessage("");
		int month=15;
		boolean erstesHalJahr = Util.istErstesHalbjahr(month);
	}
 
	@Test
	public void testIstErtsesHalbJahr_invalidMonthIs7() {
		//exception.expect(IllegalArgumentException.class);
		//exception.expectMessage("");
		int month=7;
		assertFalse(Util.istErstesHalbjahr(month));
	
	}
	
}
