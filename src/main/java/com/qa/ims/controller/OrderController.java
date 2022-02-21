package com.qa.ims.controller;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.dao.OrderDAO;
import com.qa.ims.persistence.domain.Order;
import com.qa.ims.persistence.domain.OrderItem;
import com.qa.ims.utils.Utils;

/**
 * Takes in order details for CRUD functionality
 *
 */
public class OrderController implements CrudController<Order> {

	public static final Logger LOGGER = LogManager.getLogger();

	private OrderDAO orderDAO;
	private Utils utils;

	public OrderController(OrderDAO orderDAO, Utils utils) {
		super();
		this.orderDAO = orderDAO;
		this.utils = utils;
	}

	/**
	 * Reads all orders to the logger
	 */
	@Override
	public List<Order> readAll() {
		List<Order> orders = orderDAO.readAll();
		for (Order order : orders) {
			LOGGER.info(order);
		}
		return orders;
	}

	/**
	 * Creates a order by taking in user input
	 */
	@Override
	public Order create() {
		LOGGER.info("Please enter a customerId");
		Long customerId = utils.getLong();
		Order order = new Order(customerId, Timestamp.from(Instant.now()));
		order = orderDAO.create(orderItemActions(order));
		LOGGER.info("Order created");
		return order;
	}

	/**
	 * Updates an existing order by taking in user input
	 */
	@Override
	public Order update() {
		LOGGER.info("Please enter the id of the order you would like to update");
		Order order = orderDAO.read(utils.getLong());

		if (order == null) {
			LOGGER.info("Order not found. Would you like to create an order?");
			YesNo.printYesNo();
			if (YesNo.getYesNo(utils)) {
				return create();
			} else {
				return null;
			}
		} else {
			LOGGER.info(order.toString());
			LOGGER.info("Please enter a customerId");
			order.setCustomerId(utils.getLong());
			order = orderDAO.update(orderItemActions(order));
			LOGGER.info("Order updated");
			return order;
		}
	}

	/**
	 * Deletes an existing order by the id of the order
	 *
	 * @return
	 */
	@Override
	public int delete() {
		LOGGER.info("Please enter the id of the order you would like to delete");
		Long id = utils.getLong();
		return orderDAO.delete(id);
	}

	private Order orderItemActions(Order order) {
		boolean done = false;
		do {
			LOGGER.info("What would you like to do with the items in this order:");

			Action.printActions();
			Action action = Action.getAction(utils);

			if (action == Action.RETURN) {
				done = true;
			} else {
				doOrderItemAction(order, action);
			}
		} while (!done);
		return order;
	}

	private void doOrderItemAction(Order order, Action action) {
		switch (action) {
		case CREATE:
			createOrderItem(order);
			break;
		case READ:
			readAllOrderItems(order);
			break;
		case UPDATE:
			updateOrderItem(order);
			break;
		case DELETE:
			deleteOrderItem(order);
			break;
		case RETURN:
			break;
		default:
			break;
		}
	}

	private void createOrderItem(Order order) {
		LOGGER.info("Please enter a product ID");
		long productId = utils.getLong();
		LOGGER.info("Please enter a quantity");
		long quantity = utils.getLong();
		order.addOrderItem(productId, quantity);
		LOGGER.info("Item added");
	}

	private void readAllOrderItems(Order order) {
		LOGGER.info(order.toString());
	}

	private void updateOrderItem(Order order) {
		LOGGER.info("Please enter a product ID");
		long productId = utils.getLong();
		OrderItem oi = order.getOrderItem(productId);
		if (oi == null) {
			LOGGER.info("None of this item are in this order. Would you like to add them?");
			YesNo.printYesNo();
			if (YesNo.getYesNo(utils)) {
				LOGGER.info("Please enter a quantity");
				long quantity = utils.getLong();
				order.addOrderItem(productId, quantity);
				LOGGER.info("Item added");
			}
		} else {
			LOGGER.info("Current quantity: " + oi.getQuantity());
			LOGGER.info("Please enter the new quantity");
			oi.setQuantity(utils.getLong());
			LOGGER.info("Item edited");
		}
	}

	private void deleteOrderItem(Order order) {
		LOGGER.info("Please enter a product ID");
		order.deleteOrderItem(utils.getLong());
		LOGGER.info("Item removed");
	}
}
