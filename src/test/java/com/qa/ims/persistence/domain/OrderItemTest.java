package com.qa.ims.persistence.domain;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import nl.jqno.equalsverifier.EqualsVerifier;

public class OrderItemTest {

	@Test
	public void testToString() {
		final OrderItem oi = new OrderItem(1L, 1L, 1L);
		String expected = "orderId:1 itemId:1 quantity:1";
		assertEquals(expected, oi.toString());
	}

	@Test
	public void testHashCode() {
		final OrderItem oi = new OrderItem(1L, 1L, 1L);
		int expected = 30784;
		assertEquals(expected, oi.hashCode());
	}

	/*
	@Test
	public void testEquals() {
		EqualsVerifier.simple().forClass(OrderItem.class).verify();
	}
	*/

	@Test
	public void testEquals() {
		final OrderItem oi1 = new OrderItem(1L, 1L, 1L);
		final OrderItem oi2 = new OrderItem(1L, 1L, 1L);
		assertEquals(true, oi1.equals(oi2));
	}

	@Test
	public void testNotEquals1() {
		final OrderItem oi1 = new OrderItem(1L, 1L, null);
		final OrderItem oi2 = new OrderItem(2L, 1L, null);
		assertEquals(false, oi1.equals(oi2));
	}

	@Test
	public void testNotEquals2() {
		final OrderItem oi1 = new OrderItem(null, 1L, 1L);
		final OrderItem oi2 = new OrderItem(null, 2L, 1L);
		assertEquals(false, oi1.equals(oi2));
	}

	@Test
	public void testNotEquals3() {
		final OrderItem oi1 = new OrderItem(1L, null, 1L);
		final OrderItem oi2 = new OrderItem(1L, null, 2L);
		assertEquals(false, oi1.equals(oi2));
	}

	@Test
	public void testNotEqualsNull1() {
		final OrderItem oi1 = new OrderItem(1L, 1L, 1L);
		final OrderItem oi2 = new OrderItem(null, 1L, 1L);
		assertEquals(false, oi1.equals(oi2));
	}

	@Test
	public void testNotEqualsNull2() {
		final OrderItem oi1 = new OrderItem(1L, 1L, 1L);
		final OrderItem oi2 = new OrderItem(1L, null, 1L);
		assertEquals(false, oi1.equals(oi2));
	}

	@Test
	public void testNotEqualsNull3() {
		final OrderItem oi1 = new OrderItem(1L, 1L, 1L);
		final OrderItem oi2 = new OrderItem(1L, 1L, null);
		assertEquals(false, oi1.equals(oi2));
	}

	@Test
	public void testNotEqualsNull4() {
		final OrderItem oi1 = new OrderItem(null, 1L, 1L);
		final OrderItem oi2 = new OrderItem(1L, 1L, 1L);
		assertEquals(false, oi1.equals(oi2));
	}

	@Test
	public void testNotEqualsNull5() {
		final OrderItem oi1 = new OrderItem(1L, null, 1L);
		final OrderItem oi2 = new OrderItem(1L, 1L, 1L);
		assertEquals(false, oi1.equals(null));
	}

	@Test
	public void testNotEqualsNull6() {
		final OrderItem oi1 = new OrderItem(1L, 1L, null);
		final OrderItem oi2 = new OrderItem(1L, 1L, 1L);
		assertEquals(false, oi1.equals(oi2));
	}

	@Test
	public void testNotEqualsNull7() {
		final OrderItem oi = new OrderItem(1L, 1L, 1L);
		assertEquals(false, oi.equals(null));
	}
}
