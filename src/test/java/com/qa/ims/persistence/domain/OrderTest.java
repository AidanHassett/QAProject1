package com.qa.ims.persistence.domain;

import static org.junit.Assert.assertEquals;

import java.sql.Timestamp;

import org.junit.Test;

import nl.jqno.equalsverifier.EqualsVerifier;

public class OrderTest {

	@Test
	public void testToString() {
		final Order o = new Order(1L, 1L, 1L);
		o.addOrderItem(1L, 1L);
		String expected = "id:1 customerId:1 timePlaced:1 Jan 1970, 01:00:00\r\nItems:\r\n  orderId:1 itemId:1 quantity:1";
		assertEquals(expected, o.toString());
	}

	@Test
	public void testHashCode() {
		final Order o = new Order(1L, 1L, 1L);
		int expected = 954304;
		assertEquals(expected, o.hashCode());
	}
	
	@Test
	public void testAddOrderItem() {
		final Order o = new Order(1L, 1L, 1L);
		o.addOrderItem(1L, 1L);
		assertEquals(new OrderItem(1L, 1L, 1L), o.getOrderItem(1L));
	}
	
	@Test
	public void testAddOrderItemTwice() {
		final Order o = new Order(1L, 1L, 1L);
		o.addOrderItem(1L, 1L);
		o.addOrderItem(1L, 1L);
		assertEquals(new OrderItem(1L, 1L, 2L), o.getOrderItem(1L));
	}
	
	@Test
	public void testGetOrderItemNull() {
		final Order o = new Order(1L, 1L, 1L);
		assertEquals(null, o.getOrderItem(1L));
	}
	
	@Test
	public void testDeleteOrderItem() {
		final Order o = new Order(1L, 1L, 1L);
		o.addOrderItem(1L, 1L);
		o.deleteOrderItem(1L);
		assertEquals(null, o.getOrderItem(1L));
	}
	
	@Test
	public void testReplaceOrderItem() {
		final Order o = new Order(1L, 1L, 1L);
		o.addOrderItem(1L, 1L);
		o.replaceOrderItem(1L, 2L);
		assertEquals(new OrderItem(1L, 1L, 2L), o.getOrderItem(1L));
	}
	
	@Test
	public void testReplaceOrderItemZero() {
		final Order o = new Order(1L, 1L, 1L);
		o.addOrderItem(1L, 1L);
		o.replaceOrderItem(1L, 0L);
		assertEquals(null, o.getOrderItem(1L));
	}

	/*
	@Test
	public void testEquals() {
		EqualsVerifier.simple().forClass(Order.class).verify();
	}
	*/

	@Test
	public void testEquals() {
		final Order o1 = new Order(1L, 1L, 1L);
		final Order o2 = new Order(1L, 1L, new Timestamp(1L));
		assertEquals(true, o1.equals(o2));
	}
	
	@Test
	public void testEqualsWithItems() {
		final Order o1 = new Order(1L, 1L, 1L);
		final Order o2 = new Order(1L, 1L, new Timestamp(1L));
		o1.addOrderItem(1L, 1L);
		o2.addOrderItem(1L, 1L);
		assertEquals(true, o1.equals(o2));
	}

	@Test
	public void testNotEquals1() {
		final Order o1 = new Order(1L, 1L, (Long) null);
		final Order o2 = new Order(2L, 1L, (Long) null);
		assertEquals(false, o1.equals(o2));
	}

	@Test
	public void testNotEquals2() {
		final Order o1 = new Order(1L, 1L);
		final Order o2 = new Order(2L, 1L);
		assertEquals(false, o1.equals(o2));
	}

	@Test
	public void testNotEquals3() {
		final Order o1 = new Order(1L, (Long) null, 1L);
		final Order o2 = new Order(1L, (Long) null, 2L);
		assertEquals(false, o1.equals(o2));
	}

	@Test
	public void testNotEqualsNull1() {
		final Order o1 = new Order(1L, 1L, 1L);
		final Order o2 = new Order(null, 1L, 1L);
		assertEquals(false, o1.equals(o2));
	}

	@Test
	public void testNotEqualsNull2() {
		final Order o1 = new Order(1L, 1L, 1L);
		final Order o2 = new Order(1L, (Long) null, 1L);
		assertEquals(false, o1.equals(o2));
	}

	@Test
	public void testNotEqualsNull3() {
		final Order o1 = new Order(1L, 1L, 1L);
		final Order o2 = new Order(1L, 1L, (Long) null);
		assertEquals(false, o1.equals(o2));
	}

	@Test
	public void testNotEqualsNull4() {
		final Order o1 = new Order(null, 1L, 1L);
		final Order o2 = new Order(1L, 1L, 1L);
		assertEquals(false, o1.equals(o2));
	}

	@Test
	public void testNotEqualsNull5() {
		final Order o1 = new Order(1L, (Long) null, 1L);
		final Order o2 = new Order(1L, 1L, 1L);
		assertEquals(false, o1.equals(o2));
	}

	@Test
	public void testNotEqualsNull6() {
		final Order o1 = new Order(1L, 1L, (Long) null);
		final Order o2 = new Order(1L, 1L, 1L);
		assertEquals(false, o1.equals(o2));
	}

	@Test
	public void testNotEqualsNull7() {
		final Order o = new Order(1L, 1L, 1L);
		assertEquals(false, o.equals(null));
	}
}
