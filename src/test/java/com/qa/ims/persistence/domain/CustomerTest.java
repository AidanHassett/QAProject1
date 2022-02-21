package com.qa.ims.persistence.domain;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import nl.jqno.equalsverifier.EqualsVerifier;

public class CustomerTest {
	
	@Test
	public void testToString() {
		final String EMAIL = "barrys@gmail.com", F_NAME = "barry", L_NAME = "scott";
		final Customer c = new Customer(1L, EMAIL, F_NAME, L_NAME);
		String expected = "id:1 email:barrys@gmail.com first name:barry surname:scott";
		assertEquals(expected, c.toString());
	}
	
	@Test
	public void testHashCode() {
		final String EMAIL = "barrys@gmail.com", F_NAME = "barry", L_NAME = "scott";
		final Customer c = new Customer(1L, EMAIL, F_NAME, L_NAME);
		int expected = 657204911;
		assertEquals(expected, c.hashCode());
	}
	
	/*
	@Test
	public void testEquals() {
		EqualsVerifier.simple().forClass(Customer.class).verify();
	}
	*/
	
	@Test
	public void testEquals() {
		final String EMAIL = "barrys@gmail.com", F_NAME = "barry", L_NAME = "scott";
		final Customer c1 = new Customer(EMAIL, F_NAME, L_NAME);
		final Customer c2 = new Customer(EMAIL, F_NAME, L_NAME);
		assertEquals(true, c1.equals(c2));
	}
	
	@Test
	public void testNotEquals1() {
		final Customer c1 = new Customer("barrys@gmail.com", "barry", "scott");
		final Customer c2 = new Customer("barrys@gmail.com", "larry", "scott");
		assertEquals(false, c1.equals(c2));
	}
	
	@Test
	public void testNotEquals2() {
		final Customer c1 = new Customer(1L, "barrys@gmail.com", null, "scott");
		final Customer c2 = new Customer(1L, "larrys@gmail.com", null, "scott");
		assertEquals(false, c1.equals(c2));
	}
	
	@Test
	public void testNotEquals3() {
		final Customer c1 = new Customer(1L, "barrys@gmail.com", "barry", null);
		final Customer c2 = new Customer(2L, "barrys@gmail.com", "barry", null);
		assertEquals(false, c1.equals(c2));
	}
	
	@Test
	public void testNotEquals4() {
		final Customer c1 = new Customer(1L, null, "barry", "scott");
		final Customer c2 = new Customer(1L, null, "barry", "cott");
		assertEquals(false, c1.equals(c2));
	}
	
	@Test
	public void testNotEqualsNull1() {
		final Customer c1 = new Customer("barrys@gmail.com", "barry", "scott");
		final Customer c2 = new Customer("barrys@gmail.com", null, "scott");
		assertEquals(false, c1.equals(c2));
	}
	
	@Test
	public void testNotEqualsNull2() {
		final Customer c1 = new Customer(1L, "barrys@gmail.com", null, "scott");
		final Customer c2 = new Customer(1L, null, null, "scott");
		assertEquals(false, c1.equals(c2));
	}
	
	@Test
	public void testNotEqualsNull3() {
		final Customer c1 = new Customer(1L, "barrys@gmail.com", "barry", null);
		final Customer c2 = new Customer("barrys@gmail.com", "barry", null);
		assertEquals(false, c1.equals(c2));
	}
	
	@Test
	public void testNotEqualsNull4() {
		final Customer c1 = new Customer(1L, null, "barry", "scott");
		final Customer c2 = new Customer(1L, null, "barry", null);
		assertEquals(false, c1.equals(c2));
	}
	
	@Test
	public void testNotEqualsNull5() {
		final Customer c1 = new Customer(1L, null, "barry", "scott");
		assertEquals(false, c1.equals(null));
	}
	
	@Test
	public void testNotEqualsNull6() {
		final Customer c1 = new Customer("barrys@gmail.com", null, "scott");
		final Customer c2 = new Customer("barrys@gmail.com", "barry", "scott");
		assertEquals(false, c1.equals(c2));
	}
	
	@Test
	public void testNotEqualsNull7() {
		final Customer c1 = new Customer(1L, null, null, "scott");
		final Customer c2 = new Customer(1L, "barrys@gmail.com", null, "scott");
		assertEquals(false, c1.equals(c2));
	}
	
	@Test
	public void testNotEqualsNull8() {
		final Customer c1 = new Customer("barrys@gmail.com", "barry", null);
		final Customer c2 = new Customer(1L, "barrys@gmail.com", "barry", null);
		assertEquals(false, c1.equals(c2));
	}
	
	@Test
	public void testNotEqualsNull9() {
		final Customer c1 = new Customer(1L, null, "barry", null);
		final Customer c2 = new Customer(1L, null, "barry", "scott");
		assertEquals(false, c1.equals(c2));
	}
}
