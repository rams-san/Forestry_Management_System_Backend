package com.abridged.forestrymanagementsystem.dao;

import java.util.List;

import com.abridged.forestrymanagementsystem.dto.Orders;

public interface OrderDao {

	public Orders getOrder(String orderNumber);

	public boolean addOrder(Orders order);

	public boolean updateOrder(Orders order);

	public boolean deleteOrder(String orderNumber);
	
	public List<Orders> getAllOrders();
}
