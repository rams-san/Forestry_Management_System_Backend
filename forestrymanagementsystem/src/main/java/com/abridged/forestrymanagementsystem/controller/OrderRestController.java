package com.abridged.forestrymanagementsystem.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.abridged.forestrymanagementsystem.dto.Orders;
import com.abridged.forestrymanagementsystem.dto.OrdersResponse;
import com.abridged.forestrymanagementsystem.service.OrderService;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class OrderRestController {

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		CustomDateEditor editor = new CustomDateEditor(new SimpleDateFormat("yyyy-mm-dd"), true);

		binder.registerCustomEditor(Date.class, editor);
	}

	@Autowired
	private OrderService service;

	/**
	 * This method is use to get order record.
	 * 
	 * @param orderNumber is the parameter to getOrderByOrderId method.
	 * @return orderResponse
	 */
	@GetMapping(path = "/getorder", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public OrdersResponse getOrderByOrderId(String orderNumber) {
		OrdersResponse orderResponse = new OrdersResponse();
		Orders info = service.serviceGetOrder(orderNumber, orderResponse);

		if (info != null) {
			orderResponse.setError(false);
			orderResponse.setMessage("Get the record");
			orderResponse.setOrder(info);
		} else {
			orderResponse.setError(true);
			orderResponse.setMessage("Order id is not present");
			orderResponse.setOrder(info);
		}
		return orderResponse;
	}// End of getOrderByOrderId()

	/**
	 * This method is use to add order record.
	 * 
	 * @param order is the parameter to addOrder method.
	 * @return orderResponse
	 */
	@PostMapping(path = "/addorder", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	public OrdersResponse addOrder(@RequestBody Orders order) {
		OrdersResponse orderResponse = new OrdersResponse();
		System.out.println(order);
		boolean isAdded = service.serviceAddOrder(order, orderResponse);

		if (isAdded) {
			orderResponse.setError(false);
			orderResponse.setMessage("Order record added successfully");
		} else {
			orderResponse.setError(true);
			orderResponse.setMessage("Record id not added");
		}
		return orderResponse;
	}// End of addOrder()

	/**
	 * This method is use to delete order record.
	 * 
	 * @param orderNumber is the parameter to deleteOrderById method.
	 * @return orderResponse
	 */
	@DeleteMapping(path = "/deleteorder", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public OrdersResponse deleteOrderById(String orderNumber) {
		OrdersResponse orderResponse = new OrdersResponse();
		boolean isDeleted = service.serviceDeleteOrder(orderNumber, orderResponse);

		if (isDeleted) {
			orderResponse.setError(false);
			orderResponse.setMessage("Order record deleted successfully");
		} else {
			orderResponse.setError(true);
			orderResponse.setMessage("Record id not deleted");
		}
		return orderResponse;
	}// End of deleteOrderById()

	/**
	 * This method is use to update order record.
	 * 
	 * @param order is the parameter to updateOrder method.
	 * @return orderResponse
	 */
	@PutMapping(path = "/updateorder", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	public OrdersResponse updateOrder(@RequestBody Orders order) {
		OrdersResponse orderResponse = new OrdersResponse();
		boolean isUpdated = service.serviceUpdateOrder(order, orderResponse);

		if (isUpdated) {
			orderResponse.setError(false);
			orderResponse.setMessage("Order record updated successfully");
		} else {
			orderResponse.setError(true);
			orderResponse.setMessage("Record id not updated");
		}
		return orderResponse;
	}// End of updateOrder()

	/**
	 * This method is use to get all orders record.
	 * 
	 * @return orderResponse
	 */
	@GetMapping(path = "getallordersrecord", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public OrdersResponse getAllOrderDetails() {
		List<Orders> listRecord = service.serviceGetAllOrders();
		OrdersResponse orderResponse = new OrdersResponse();
		if (listRecord != null) {
			orderResponse.setError(false);
			orderResponse.setMessage("All Customer record");
			orderResponse.setOrderListInfo(listRecord);
		} else {
			orderResponse.setError(true);
			orderResponse.setMessage("Customer record is not present");
			orderResponse.setOrderListInfo(listRecord);
		}
		return orderResponse;
	}// End of getAllOrdersRecord
}// End of the class
