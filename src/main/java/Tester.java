import entity.Customer;
import entity.ItemType;
import entity.Order;
import entity.OrderLine;
import facade.CustomerFacade;
import facade.ItemTypeFacade;
import facade.OrderFacade;
import utils.EMF_Creator;

import javax.persistence.EntityManagerFactory;
import java.util.List;

public class Tester {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = EMF_Creator.createEntityManagerFactory(EMF_Creator.DbSelector.DEV, EMF_Creator.Strategy.DROP_AND_CREATE);
        CustomerFacade customerFacade = CustomerFacade.getCustomerFacade(entityManagerFactory);
        ItemTypeFacade itemTypeFacade = ItemTypeFacade.getItemTypeFacade(entityManagerFactory);
        OrderFacade orderFacade = OrderFacade.getOrderFacade(entityManagerFactory);

        Customer customer1 = new Customer("Niels", "niller@123.dk");
        Customer customer2 = new Customer("Peter", "pet@123.dk");
        ItemType itemType1 = new ItemType("Item 1", "This is the first item", 10);
        ItemType itemType2 = new ItemType("Item 2", "This is some other item", 20);

        customerFacade.create(customer1);
        customerFacade.create(customer2);
        itemTypeFacade.create(itemType1);
        itemTypeFacade.create(itemType2);


        Order order1 = new Order();
        OrderLine orderLine1 = new OrderLine(10, itemType1);
        OrderLine orderLine2 = new OrderLine(41, itemType2);
        order1.addOrderLine(orderLine1);
        order1.addOrderLine(orderLine2);

        customer1.addOrder(order1);
        orderFacade.create(order1);
        List<Order> orders = orderFacade.getByCustomer(customer1.getId());
        int sum = 0;
        for(Order order: orders) {
            sum += order.getPrice();
        }
        System.out.println("Total price for customer: " + sum);
    }
}
