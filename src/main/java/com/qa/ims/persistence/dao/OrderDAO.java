package com.qa.ims.persistence.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.domain.Order;
import com.qa.ims.persistence.domain.OrderItem;
import com.qa.ims.utils.DBUtils;

public class OrderDAO implements Dao<Order> {

	public static final Logger LOGGER = LogManager.getLogger();

	/**
	 * Adds to the order passed in all associated orderItems in the database
	 *
	 * @param order - Takes in an order object
	 * @return The same order object passed in
	 */
	public Order fillOrderItems(Order order) {
		try (
			Connection connection = DBUtils.getInstance().getConnection();
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM orderItems WHERE orderId = ?");
		) {
			statement.setLong(1, order.getId());
			try (ResultSet resultSet = statement.executeQuery();) {
				Long itemId;
				Long quantity;
				resultSet.beforeFirst();
				while (resultSet.next()) {
					itemId = resultSet.getLong("itemId");
					quantity = resultSet.getLong("quantity");
					order.replaceOrderItem(itemId, quantity);
				}
			}
		} catch (SQLException e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return order;
	}

	@Override
	public Order modelFromResultSet(ResultSet resultSet) throws SQLException {
		Long id = resultSet.getLong("id");
		Long customerId = resultSet.getLong("customerId");
		Timestamp timePlaced = resultSet.getTimestamp("timePlaced");
		return fillOrderItems(new Order(id, customerId, timePlaced));
	}

	/**
	 * Reads all orders from the database
	 *
	 * @return A list of orders
	 */
	@Override
	public List<Order> readAll() {
		try (
			Connection connection = DBUtils.getInstance().getConnection();
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM orders");
		) {
			List<Order> orders = new ArrayList<>();
			while (resultSet.next()) {
				orders.add(modelFromResultSet(resultSet));
			}
			return orders;
		} catch (SQLException e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return new ArrayList<>();
	}

	public Order readLatest() {
		try (
			Connection connection = DBUtils.getInstance().getConnection();
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM orders ORDER BY id DESC LIMIT 1");
		) {
			if (resultSet.next()) {
				return modelFromResultSet(resultSet);
			}
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	/**
	 * Creates an order in the database, then creates all associated orderItems
	 *
	 * @param order - takes in a order object. id will be ignored
	 */
	@Override
	public Order create(Order order) {
		try (
			Connection connection = DBUtils.getInstance().getConnection();
			PreparedStatement statement = connection.prepareStatement("INSERT INTO orders(customerId, timePlaced) VALUES (?, ?)");
		) {
			statement.setLong(1, order.getCustomerId());
			statement.setTimestamp(2, order.getTimePlaced());
			statement.executeUpdate();
			Long orderId = readLatest().getId();
			for (OrderItem oi : order.getAllOrderItems()) {
				try (PreparedStatement itemStatement = connection.prepareStatement("INSERT INTO orderItems(orderId, itemId, quantity) VALUES (?, ?, ?)");) {
					itemStatement.setLong(1, orderId);
					itemStatement.setLong(2, oi.getItemId());
					itemStatement.setLong(3, oi.getQuantity());
					itemStatement.executeUpdate();
				} catch (Exception e) {
					LOGGER.debug(e);
					LOGGER.error(e.getMessage());
				}
			}
			return readLatest();
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	@Override
	public Order read(Long id) {
		try (
			Connection connection = DBUtils.getInstance().getConnection();
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM orders WHERE id = ?");
		) {
			statement.setLong(1, id);
			try (ResultSet resultSet = statement.executeQuery();) {
				resultSet.next();
				return modelFromResultSet(resultSet);
			}
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	/**
	 * Updates an order in the database and its orderItems
	 * Deletes all old orderItems in the database, then adds all orderItems from the order object
	 *
	 * @param order - takes in an order object, the id field will be used to
	 *                 update that order in the database
	 * @return
	 */
	@Override
	public Order update(Order order) {
		try (
			Connection connection = DBUtils.getInstance().getConnection();
			PreparedStatement statement = connection.prepareStatement("UPDATE orders SET customerId = ?, timePlaced = ? WHERE id = ?");
		) {
			statement.setLong(1, order.getCustomerId());
			statement.setTimestamp(2, order.getTimePlaced());
			statement.setLong(3, order.getId());
			statement.executeUpdate();

			deleteOrderItems(order.getId());

			for (OrderItem oi : order.getAllOrderItems()) {
				try (PreparedStatement itemStatement = connection.prepareStatement("INSERT INTO orderItems(orderId, itemId, quantity) VALUES (?, ?, ?)");) {
					itemStatement.setLong(1, oi.getOrderId());
					itemStatement.setLong(2, oi.getItemId());
					itemStatement.setLong(3, oi.getQuantity());
					itemStatement.executeUpdate();
				} catch (Exception e) {
					LOGGER.debug(e);
					LOGGER.error(e.getMessage());
				}
			}
			return read(order.getId());
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	/**
	 * Deletes an order in the database
	 *
	 * @param id - id of the order
	 */
	@Override
	public int delete(long id) {
		deleteOrderItems(id);

		try (
			Connection connection = DBUtils.getInstance().getConnection();
			PreparedStatement statement = connection.prepareStatement("DELETE FROM orders WHERE id = ?");
		) {
			statement.setLong(1, id);
			return statement.executeUpdate();
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return 0;
	}

	public int deleteOrderItems(long orderId) {
		try (
			Connection connection = DBUtils.getInstance().getConnection();
			PreparedStatement statement = connection.prepareStatement("DELETE FROM orderItems WHERE orderId = ?");
		) {
			statement.setLong(1, orderId);
			return statement.executeUpdate();
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return 0;
	}
}
