package com.qa.ims.persistence.domain;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import nl.jqno.equalsverifier.EqualsVerifier;

public class ItemTest {

	@Test
	public void testToString() {
		final String TITLE = "olives";
		final double PRICE = 2.00;
		final Item i = new Item(1L, TITLE, PRICE);
		String expected = "id:1 title:olives price:£2.00";
		assertEquals(expected, i.toString());
	}

	@Test
	public void testHashCode() {
		final String TITLE = "olives";
		final double PRICE = 2.00;
		final Item i = new Item(1L, TITLE, PRICE);
		int expected = -1373741584;
		assertEquals(expected, i.hashCode());
	}

	/*
	@Test
	public void testEquals() {
		EqualsVerifier.simple().forClass(Item.class).verify();
	}
	*/

	@Test
	public void testEquals() {
		final String TITLE = "olives";
		final Item i1 = new Item(1L, TITLE, 2.00);
		final Item i2 = new Item(1L, TITLE, 2L, 0L);
		assertEquals(true, i1.equals(i2));
	}

	@Test
	public void testNotEquals1() {
		final Item i1 = new Item("olives", 2.00);
		final Item i2 = new Item("grapes", 2.00);
		assertEquals(false, i1.equals(i2));
	}

	@Test
	public void testNotEquals2() {
		final Item i1 = new Item(1L, null, 2.00);
		final Item i2 = new Item(1L, null, 1.00);
		assertEquals(false, i1.equals(i2));
	}

	@Test
	public void testNotEquals3() {
		final Item i1 = new Item(1L, "olives", 2.00);
		final Item i2 = new Item(2L, "olives", 2.00);
		assertEquals(false, i1.equals(i2));
	}

	@Test
	public void testNotEqualsNull1() {
		final Item i1 = new Item(1L, "olives", 2.00);
		final Item i2 = new Item("olives", 2.00);
		assertEquals(false, i1.equals(i2));
	}

	@Test
	public void testNotEqualsNull2() {
		final Item i1 = new Item("olives", 2.00);
		final Item i2 = new Item(null, 2.00);
		assertEquals(false, i1.equals(i2));
	}

	@Test
	public void testNotEqualsNull3() {
		final Item i1 = new Item("olives", 2.00);
		final Item i2 = new Item(1L, "olives", 2.00);
		assertEquals(false, i1.equals(i2));
	}

	@Test
	public void testNotEqualsNull4() {
		final Item i1 = new Item(null, 2.00);
		final Item i2 = new Item("olives", 2.00);
		assertEquals(false, i1.equals(i2));
	}

	public void testNotEqualsNull5() {
		final Item i = new Item(null, 2.00);
		assertEquals(false, i.equals(null));
	}
}
