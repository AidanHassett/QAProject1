package com.qa.ims.controllers;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.qa.ims.controller.OrderController;
import com.qa.ims.persistence.dao.OrderDAO;
import com.qa.ims.persistence.domain.Order;
import com.qa.ims.utils.Utils;

@RunWith(MockitoJUnitRunner.class)
public class OrderControllerTest {

	@Mock
	private Utils utils;

	@Mock
	private OrderDAO dao;

	@InjectMocks
	private OrderController controller;

	@Test
	public void testCreate() {
		final Long CUSTOMER_ID = 1L;
		final Timestamp TIME_PLACED = new Timestamp(1L);
		final Order created = new Order(CUSTOMER_ID, TIME_PLACED);

		Mockito.when(this.utils.getLong()).thenReturn(CUSTOMER_ID, 1L, 1L, 1L, 2L, 1L);
		Mockito.when(this.utils.getString()).thenReturn("CREATE", "UPDATE", "DELETE", "RETURN");
		Mockito.when(this.dao.create(any(Order.class))).thenReturn(created);
		//Mockito.when(this.dao.read(1L)).thenReturn(created);

		assertEquals(created, controller.create());

		Mockito.verify(this.utils, Mockito.times(6)).getLong();
		Mockito.verify(this.dao, Mockito.times(1)).create(any(Order.class));
	}

	@Test
	public void testReadAll() {
		List<Order> orders = new ArrayList<>();
		orders.add(new Order(1L, 1L, 1L));

		Mockito.when(dao.readAll()).thenReturn(orders);

		assertEquals(orders, controller.readAll());

		Mockito.verify(dao, Mockito.times(1)).readAll();
	}

	@Test
	public void testUpdate() {
		Order updated = new Order(1L, 1L, 1L);

		Mockito.when(this.utils.getLong()).thenReturn(1L, updated.getCustomerId());
		Mockito.when(this.utils.getString()).thenReturn("RETURN");
		Mockito.when(this.dao.read(1L)).thenReturn(updated);
		Mockito.when(this.dao.update(updated)).thenReturn(updated);

		assertEquals(updated, this.controller.update());

		Mockito.verify(this.utils, Mockito.times(2)).getLong();
		Mockito.verify(this.dao, Mockito.times(1)).update(updated);
	}

	@Test
	public void testDelete() {
		final long ID = 1L;

		Mockito.when(utils.getLong()).thenReturn(ID);
		Mockito.when(dao.delete(ID)).thenReturn(1);

		assertEquals(1L, this.controller.delete());

		Mockito.verify(utils, Mockito.times(1)).getLong();
		Mockito.verify(dao, Mockito.times(1)).delete(ID);
	}

}
