package com.controllers;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.daos.OrderDAO;
import com.daos.OrderDAOImpl;
import com.daos.TradeDAO;
import com.daos.TradeDAOImpl;
import com.pojos.Order;
import com.pojos.Orders;
import com.pojos.Trade;

public class OrderMatching {
	
	List<Order> splitOrder(String category) {
		OrderDAO orderDao = new OrderDAOImpl();
		List<Order> orders = new ArrayList<Order>();
		orders = orderDao.getAll();
		List<Order> splitList = new ArrayList<Order>();
		for (Order order : orders) {
			if(order.getCategory().equalsIgnoreCase(category)) {
				splitList.add(order);
			}
		}
		return splitList;
	}
	
	public boolean executeTrade(Order buyOrder, Order sellOrder, int quantity) {
		int buyOrderID=buyOrder.getOrderID();
		int sellOrderID=sellOrder.getOrderID();
		int buyerID=buyOrder.getTraderID();
		int sellerID=sellOrder.getTraderID();
		double price=buyOrder.getPrice(); //same as sellOrder.getPrice()
		Timestamp timestamp=new Timestamp(System.currentTimeMillis());
		int tradeID=1; //to be auto-incremented
		
		//Add the Executed Trade
		Trade trade=new Trade(tradeID, buyOrderID, sellOrderID, buyerID, sellerID, price, timestamp, quantity);
		TradeDAO tradeDAO=new TradeDAOImpl();
		tradeDAO.addTrade(trade);
		
		return true;
	}
	
	
	//Buyer placing limit order
	public boolean buyLimitOrder(List<Order> sellList, List<Order> buyList, int buyIndex) {
		Order buyOrder = buyList.get(buyIndex);
		OrderDAO orderDAO = new OrderDAOImpl();
		int sellIndex = 0;
		boolean flag = true; 	//if no seller is selling at price less than buy price.
		while(flag && sellIndex < sellList.size()) {
			Order sellOrder = sellList.get(sellIndex);
			double buyPrice = buyOrder.getPrice();
			double sellPrice = sellOrder.getPrice();
			if(buyPrice >= sellPrice) {
				if(buyOrder.getPrice()==sellOrder.getPrice()) {
					int buyQty=buyOrder.getQuantity();
					int sellQty=sellOrder.getQuantity();
					
					if(buyQty > sellQty) {
						
						if(buyOrder.getCondition().equalsIgnoreCase("minimumfill")) {
							//Buy Quantity more, so update buy order
							buyOrder.setQuantity(buyQty-sellQty);
							orderDAO.updateQuantity(buyOrder.getOrderID(), buyQty-sellQty); // Needs to be changed later
							
							//delete sell order from order.
							orderDAO.deleteOrder(sellOrder.getOrderID());
							sellList.remove(sellOrder);
							
							boolean isExecuted = executeTrade(buyOrder, sellOrder, sellQty);
							return isExecuted;
							
						}
//						else if(buyOrder.getDisclosedQuantity() > 0) {				//Iceberg order
//							
//						}
					}
					else if(buyQty < sellQty) {
						if(buyOrder.getCondition().equalsIgnoreCase("minimumfill")) {
							sellOrder.setQuantity(sellQty - buyQty);
							orderDAO.updateQuantity(sellOrder.getOrderID(), sellQty - buyQty);
							
							orderDAO.deleteOrder(buyOrder.getOrderID());
							buyList.remove(buyIndex);
							
							boolean isExecuted = executeTrade(buyOrder, sellOrder, buyQty);
							return isExecuted;
						}
					}
					else {
						orderDAO.deleteOrder(buyOrder.getOrderID());
						orderDAO.deleteOrder(sellOrder.getOrderID());
						buyList.remove(buyIndex);
						sellList.remove(sellIndex);
						boolean isExecuted = executeTrade(buyOrder, sellOrder, buyOrder.getQuantity());
						return isExecuted;
					}
				}
				else {
					flag = false;
				}
			}
		}
		return flag;
	}
	
	
	//Seller placing limit order
		public boolean sellLimitOrder(List<Order> sellList, List<Order> buyList, int sellIndex) {
			Order sellOrder = sellList.get(sellIndex);
			OrderDAO orderDAO = new OrderDAOImpl();
			int buyIndex = 0;
			boolean flag = true; 	//if no seller is selling at price less than buy price.
			while(flag && buyIndex < buyList.size()) {
				Order buyOrder = buyList.get(buyIndex);
				double sellPrice = sellOrder.getPrice();
				double buyPrice = buyOrder.getPrice();
				if(sellPrice >= buyPrice) {
					if(buyOrder.getPrice()==sellOrder.getPrice()) {
						int buyQty=buyOrder.getQuantity();
						int sellQty=sellOrder.getQuantity();
						
						if(buyQty > sellQty) {
							
							if(buyOrder.getCondition().equalsIgnoreCase("minimumfill")) {
								//Buy Quantity more, so update buy order
								buyOrder.setQuantity(buyQty-sellQty);
								orderDAO.updateQuantity(buyOrder.getOrderID(), buyQty-sellQty); // Needs to be changed later
								
								//delete sell order from order.
								orderDAO.deleteOrder(sellOrder.getOrderID());
								sellList.remove(sellOrder);
								
								boolean isExecuted = executeTrade(buyOrder, sellOrder, sellQty);
								return isExecuted;
								
							}
//							else if(buyOrder.getDisclosedQuantity() > 0) {				//Iceberg order
//								
//							}
						}
						else if(buyQty < sellQty) {
							if(buyOrder.getCondition().equalsIgnoreCase("minimumfill")) {
								sellOrder.setQuantity(sellQty - buyQty);
								orderDAO.updateQuantity(sellOrder.getOrderID(), sellQty - buyQty);
								
								orderDAO.deleteOrder(buyOrder.getOrderID());
								buyList.remove(buyIndex);
								
								boolean isExecuted = executeTrade(buyOrder, sellOrder, buyQty);
								return isExecuted;
							}
						}
						else {
							orderDAO.deleteOrder(buyOrder.getOrderID());
							orderDAO.deleteOrder(sellOrder.getOrderID());
							buyList.remove(buyIndex);
							sellList.remove(sellIndex);
							boolean isExecuted = executeTrade(buyOrder, sellOrder, buyOrder.getQuantity());
							return isExecuted;
						}
					}
					else {
						flag = false;
					}
				}
			}
			return flag;
		}
	
		
		
		
	public void OrderMatchingEngine() {
		
		List<Order> buyList = splitOrder("buy");
		List<Order> sellList = splitOrder("sell");
		
		OrderDAO orderDAO=new OrderDAOImpl();
		
		Comparator<Order> compareBuy = Comparator.comparing(Order::getPrice).reversed()
					.thenComparing(Order::getTimestamp).thenComparing(Order::getQuantity).reversed();

		Comparator<Order> compareSell = Comparator.comparing(Order::getPrice)
				.thenComparing(Order::getTimestamp).thenComparing(Order::getQuantity).reversed();
		
		Collections.sort(buyList,compareBuy);
		Collections.sort(sellList,compareSell);
		
		 int buy_index =0;
		   int buy_size = buyList.size();
		   int sell_size = sellList.size();
		   if(buy_size==0||sell_size==0)
		   {
			   System.out.println("returning");
			   return;
		   }
		 int sell_index=0;
		 boolean executed =false;
		 List<Order> orders = orderDAO.getAll();
		
		 do {
			   Order buys = buyList.get(buy_index);
			   Order sells =sellList.get(sell_index);
			   if(sells.getType().trim().equals("MARKET")&&buys.getType().trim().equals("MARKET")) 
			   {
				   if(buys.getTimestamp().after(sells.getTimestamp())) {
				   executed=orders.sellMarket(sellList, buyList, sell_index);
				   }
				   else
				   {
					   executed=orders.buyMarket(sellList, buyList, buy_index);
				   }
			   }
			   else if(sells.getType().trim().equals("MARKET"))
			   {
				   executed=orders.sellMarket(sellList, buyList, sell_index);
				   
			   }
			   else if(buys.getType().trim().equals("MARKET"))
			   {
				   executed=orders.buyMarket(sellList, buyList, buy_index);
				   
			   }
			   else 
			   {
		   
				   if(buys.getTimestamp().after(sells.getTimestamp())) {
					   if(sells.getCondition().trim().equals("ALLORNONE"))
					   {
						   executed=orders.OrderMatchingSystemSellFull(sellList, buyList, sell_index);
					   }
					   else
					   {
						   executed=orders.sellLimitOrder(sellList, buyList, sell_index);
					   }
					   if(!executed) {
						   sell_index++;
					   }
					   
				   }
				   else{
			   
					   if(buys.getCondition().trim().equals("ALLORNONE")) {
						   executed=orders.OrderMatchingSystemBuyFull(sellList, buyList, buy_index);
					   }
					   else
					   {
						   executed=orders.buyLimitOrder(sellList, buyList, buy_index);
					   }
					   if(!executed) {
						   buy_index++;
					   }
				   }
			   
			   }
		   }while (!executed&&(buy_index!=buy_size)&&(sell_index!=sell_size)&&(buy_index<3)&&(sell_index<3));
	}
	
//	public static void main(String[] args) {
//		
//		Comparator<Order> compareBuy = Comparator.comparing(Order::getPrice).reversed()
//				.thenComparing(Order::getTimestamp);
//
//	Comparator<Order> compareSell = Comparator.comparing(Order::getPrice)
//			.thenComparing(Order::getTimestamp);
//	
//		List<Order> buyList= new ArrayList<Order>();
//		Order[] o1 = new Order[4];
//		for(int i = 0; i < 4; i++) {
//			o1[i] = new Order();
//			o1[i].setPrice(100.5);
//			o1[i].setCategory("buy");
//			o1[i].setTimestamp(new Timestamp(2019, 04, 04, 01, 02 + i, 45, 2));
//			buyList.add(o1[i]);
//		}
//		
//		List<Order> sellList= new ArrayList<Order>();
//		Order[] o2 = new Order[4];		
//		for(int i = 0; i < 4; i++) {
//			o2[i] = new Order();
//			o2[i].setPrice(231.5 + i);
//			o2[i].setCategory("sell");
//			o2[i].setTimestamp(new Timestamp(2019, 05, 06, 07, 04 + i, 45, 2));
//			sellList.add(o2[i]);
//		}
//		
//		Collections.sort(buyList, compareBuy);
//		Collections.sort(sellList, compareSell);
//		
//		sellList.forEach((b)->{System.out.println(b.getPrice()+" : "+b.getTimestamp());});
//		sellList.forEach(System.out::println);
//	}
	
}
