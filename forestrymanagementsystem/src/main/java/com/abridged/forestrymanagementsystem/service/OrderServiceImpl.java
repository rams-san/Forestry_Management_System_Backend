package com.abridged.forestrymanagementsystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.abridged.forestrymanagementsystem.dao.OrderDao;
import com.abridged.forestrymanagementsystem.dto.Orders;
import com.abridged.forestrymanagementsystem.dto.OrdersResponse;
import com.abridged.forestrymanagementsystem.validation.ValidationImpl;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderDao dao;

	static ValidationImpl validation = new ValidationImpl();

	boolean flag1 = false;
	boolean flag2 = false;
	boolean flag3 = false;
	boolean flag4 = false;
	boolean flag5 = false;
	boolean flag6 = false;
	boolean flag7 = false;
	boolean flag8 = false;

	@Override
	public Orders serviceGetOrder(String orderNumber, OrdersResponse orderResponse) {
		if (validation.idValidation(orderNumber)) {
			orderResponse.setStatus(200);
			return dao.getOrder(orderNumber);
		} else {
			orderResponse.setStatus(401);
			orderResponse.setMessage1("Please enter valid order number (e.g range{1,4})");
		}
		return null;
	}

	@Override
	public boolean serviceAddOrder(Orders order, OrdersResponse orderResponse) {
		if (validation.idValidation(order.getContractNumber())) {
			flag1 = true;
		} else {
			orderResponse.setMessage1("Please enter valid contract number (e.g range{1,4})");
		}
		if (validation.idValidation(order.getCustomerId())) {
			flag2 = true;
		} else {
			orderResponse.setMessage2("Please enter valid customer id (e.g range{1,4})");
		}
		if (validation.idValidation(order.getProductId())) {
			flag3 = true;
		} else {
			orderResponse.setMessage3("Please enter valid product id (e.g range{1,4})");
		}
		if (validation.townValidation(order.getDeliveryPlace())) {
			flag4 = true;
		} else {
			orderResponse.setMessage4("Please enter valid delivery place (e.g Mumbai)");
		}
		if (validation.dateValidation(order.getDeliveryDate())) {
			flag5 = true;
		} else {
			orderResponse.setMessage5("Please enter valid delivery date (e.g 2020-10-30)");
		}
		if (validation.quantityValidation(order.getQuantity())) {
			flag6 = true;
		} else {
			orderResponse.setMessage6("Please enter valid quantity (e.g 20)");
		}
		if (validation.idValidation(order.getSchedulerId())) {
			flag7 = true;
		} else {
			orderResponse.setMessage7("Please enter valid scheduler id (e.g range{1,4})");
		}
		if (validation.idValidation(order.getContractNumber())) {
			flag8 = true;
		} else {
			orderResponse.setMessage7("Please enter valid contract number (e.g range{1,4})");
		}
		
		if (flag1 && flag2 && flag3 && flag4 && flag4 && flag5 && flag6 && flag7 && flag8) {
			orderResponse.setStatus(200);
			return dao.addOrder(order);
		} else {
			orderResponse.setStatus(401);
			orderResponse.setMessage8("please check contract properties");
		}
		return false;
	}

	@Override
	public boolean serviceUpdateOrder(Orders order, OrdersResponse orderResponse) {
		if (validation.idValidation(order.getContractNumber())) {
			flag1 = true;
		} else {
			orderResponse.setMessage1("Please enter valid contract number (e.g range{1,4})");
		}
		if (validation.idValidation(order.getCustomerId())) {
			flag2 = true;
		} else {
			orderResponse.setMessage2("Please enter valid customer id (e.g range{1,4})");
		}
		if (validation.idValidation(order.getProductId())) {
			flag3 = true;
		} else {
			orderResponse.setMessage3("Please enter valid product id (e.g range{1,4})");
		}
		if (validation.townValidation(order.getDeliveryPlace())) {
			flag4 = true;
		} else {
			orderResponse.setMessage4("Please enter valid delivery place (e.g Mumbai)");
		}
		if (validation.dateValidation(order.getDeliveryDate())) {
			flag5 = true;
		} else {
			orderResponse.setMessage5("Please enter valid delivery date (e.g 2020-10-30)");
		}
		if (validation.quantityValidation(order.getQuantity())) {
			flag6 = true;
		} else {
			orderResponse.setMessage6("Please enter valid quantity (e.g 20)");
		}
		if (validation.idValidation(order.getSchedulerId())) {
			flag7 = true;
		} else {
			orderResponse.setMessage7("Please enter valid scheduler id (e.g range{1,4})");
		}
		if (validation.idValidation(order.getContractNumber())) {
			flag8 = true;
		} else {
			orderResponse.setMessage7("Please enter valid contract number (e.g range{1,4})");
		}
		
		if (flag1 && flag2 && flag3 && flag4 && flag4 && flag5 && flag6 && flag7 && flag8) {
			orderResponse.setStatus(200);
			return dao.updateOrder(order);
		} else {
			orderResponse.setStatus(401);
			orderResponse.setMessage8("please check contract properties");
		}
		return false;
	}

	@Override
	public boolean serviceDeleteOrder(String orderNumber, OrdersResponse orderResponse) {
		if (validation.idValidation(orderNumber)) {
			orderResponse.setStatus(200);
			return dao.deleteOrder(orderNumber);
		} else {
			orderResponse.setStatus(401);
			orderResponse.setMessage1("Please enter valid order number (e.g range{1,4})");
		}
		return false;
	}

	@Override
	public List<Orders> serviceGetAllOrders() {
		return dao.getAllOrders();
	}

}
