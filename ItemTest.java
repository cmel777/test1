package com.tel.vo.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

import com.tel.vo.Item;



public class ItemTest {

	private Item valueObject;

	@Before
	public void setUp() {
		valueObject = new Item();
	}


	@Test
	public void shouldHandlesEqualsMethodCallCorrectlyOnItemSameReference() {
		Item same = valueObject;
		assertTrue(valueObject.equals(same));
	}

	@Test
	public void shouldHandlesEqualsMethodCallCorrectlyOnItemNull() {
		assertFalse(valueObject.equals(null));
	}

	@Test
	public void shouldHandlesEqualsMethodCallCorrectlyOnItemDifferentClass() {
		assertFalse(valueObject.equals(new String()));
	}


	@Test
	public void testToStringAllNull() {
		assertEquals(
				"Item [name=null, price=null, type=null, temperature=null]",
				valueObject.toString());
	}
}