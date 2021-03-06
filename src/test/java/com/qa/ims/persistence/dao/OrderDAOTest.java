package com.qa.ims.persistence.dao;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.qa.ims.persistence.domain.Order;
import com.qa.ims.utils.DBUtils;

public class OrderDAOTest {

	private final OrderDAO DAO = new OrderDAO();

	@Before
	public void setup() {
		DBUtils.connect();
		DBUtils.getInstance().init("src/test/resources/sql-schema.sql", "src/test/resources/sql-data.sql");
	}
	/*
	@Test
	public void testCreate() {
		final Order created = new Order(2L, 1L, 1L);
		created.addOrderItem(1L, 1L);
		Order returned = DAO.create(created);
		assertEquals(created, returned);
		assertEquals(created.getAllOrderItems(), returned.getAllOrderItems());
	}

	@Test
	public void testReadAll() {
		List<Order> expected = new ArrayList<>();
		expected.add(new Order(1L, 1L, 1L));
		expected.get(0).addOrderItem(1L, 1L);
		assertEquals(expected, DAO.readAll());
	}

	@Test
	public void testReadLatest() {
		final Order expected = new Order(1L, 1L, 1L);
		expected.addOrderItem(1L, 1L);
		assertEquals(expected, DAO.readLatest());
	}

	@Test
	public void testRead() {
		final long ID = 1L;
		final Order expected = new Order(ID, 1L, 1L);
		expected.addOrderItem(1L, 1L);
		assertEquals(expected, DAO.read(ID));
	}

	@Test
	public void testUpdate() {
		final Order updated = new Order(1L, 1L, 1L);
		updated.addOrderItem(1L, 2L);
		assertEquals(updated, DAO.update(updated));
	}

	@Test
	public void testDelete() {
		assertEquals(1, DAO.delete(1));
	}

	@Test
	public void testReadNonExistent() {
		final long ID = 2L;
		assertEquals(null, DAO.read(ID));
	}

	@Test
	public void testUpdateNonExistent() {
		final Order updated = new Order(2L, 1L, 1L);
		assertEquals(null, DAO.update(updated));

	}

	@Test
	public void testDeleteNonExistent() {
		assertEquals(0, DAO.delete(2));
	}
	*/
}
