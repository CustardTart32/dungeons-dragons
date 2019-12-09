package unsw.dungeon.tests;

import unsw.dungeon.*;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PortalTest {

	@Test
	void checkPortal() {
		Portal p1 = new Portal(2,5,1,null);
		Portal p2 = new Portal(5,9,1,null);
		
		assertEquals(p1.getX(),2);
		assertEquals(p1.getY(),5);
		assertEquals(p1.getId(),1);
		
		assertEquals(p2.getX(),5);
		assertEquals(p2.getY(),9);
		assertEquals(p2.getId(),1);
	}
	
	@Test
	void checkAltPortal() {
		Portal p1 = new Portal(2,5,1,null);
		Portal p2 = new Portal(5,9,1,null);
		
		assertEquals(p1.isOppositePortal(p2), true, "Portals should be linked to each other");
		
		p1.setAltPortal(p2);
		p2.setAltPortal(p1);
		
		assertEquals(p1.getAltPosition()[0], 5);
		assertEquals(p1.getAltPosition()[1], 9);

		assertEquals(p2.getAltPosition()[0], 2);
		assertEquals(p2.getAltPosition()[1], 5);
	}
	
	@Test
	void checkDiffPortalId() {
		Portal p1 = new Portal(2,5,1,null);
		Portal p2 = new Portal(5,9,2,null);
		
		assertEquals(p1.isOppositePortal(p2), false, "Portals have seperate id's");
	}
	
	@Test
	void checkSamePortalPosition() {
		Portal p1 = new Portal(2,5,1,null);
		Portal p2 = new Portal(2,5,1,null);
		
		assertEquals(p1.isOppositePortal(p2), false, "Portals with the same id and position are essentially the same portal");
	}
	
	@Test
	void checkPortalSameAxis() {
		Portal p1 = new Portal(2,5,1,null);
		Portal p2 = new Portal(2,9,1,null);
		
		assertEquals(p1.isOppositePortal(p2), true, "Portals with same id but on same axis should be alternates");
	}
	
	@Test
	void checkPortalSameAxisDiffID() {
		Portal p1 = new Portal(2,5,1,null);
		Portal p2 = new Portal(2,9,2,null);
		
		assertEquals(p1.isOppositePortal(p2), false, "Portals with diff id but on same axis should be false");
	}


}
