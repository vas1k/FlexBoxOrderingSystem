package fos;

import java.util.ArrayList;

public class OrderItemsList { 

	protected static ArrayList<Order> orders; // declare an array list

	public OrderItemsList() {
		orders = new ArrayList<>(); // initialise the array list to hold the user list of orders
	}

	protected void addOrder(Order order) // add the user order to the array list
	{
		orders.add(order);
	}

	protected void removeOrder(Order order) // remove an order from the array list
	{
		orders.remove(order);
	}

	protected void removeOrderByID(int orderID) // remove a specific order from the array list based o its ID
	{
		orders.remove(orderID);
	}

	protected boolean clearOrders() { // clear the array list 

		if(orders.size() != -1) {
			for(int i=0; i < orders.size(); i++){
				orders.clear();
			}
		}
		else {
			orders.isEmpty();
		}
		return orders.isEmpty();
	}

	protected double cost() { // calculate total price by adding all orders prices

		double cost = 0;

		for(Order order:orders) {

			cost += order.cost();
		}

		return cost;
	}

}