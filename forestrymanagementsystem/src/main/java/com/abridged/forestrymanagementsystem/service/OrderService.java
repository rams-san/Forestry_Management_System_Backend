package com.abridged.forestrymanagementsystem.service;

import java.util.List;

import com.abridged.forestrymanagementsystem.dto.Orders;
import com.abridged.forestrymanagementsystem.dto.OrdersResponse;


public interface OrderService {

	public Orders serviceGetOrder(String orderNumber, OrdersResponse orderResponse);

	public boolean serviceAddOrder(Orders order,OrdersResponse orderResponse);

	public boolean serviceUpdateOrder(Orders order, OrdersResponse orderResponse);

	public boolean serviceDeleteOrder(String orderNumber, OrdersResponse orderResponse);
	
	public List<Orders> serviceGetAllOrders();
}
