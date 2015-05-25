package test;
import model.*;

import org.junit.Test;

import static org.junit.Assert.*;

public class CoordinateTest {
	@Test
	public void testEquals() {
		Coordinate a = new Coordinate(0, 0);
		assertEquals(true, a.equals(new Coordinate(0, 0)));
		
		a = new Coordinate(5000, 0);
		assertEquals(true, a.equals(new Coordinate(5000, 0)));
		
		a = new Coordinate(5000, 0);
		assertEquals(false, a.equals(new Coordinate(2000, 0)));
	}
}
