package entity;
/*
 * author paepke
 * version 1.0
 */

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;


@Entity
@Table(name = "CustomerOrder")
@NamedQueries({
    @NamedQuery(name = "Order.deleteAllRows", query = "DELETE FROM Order"),
    @NamedQuery(name = "Order.getAll", query = "SELECT o FROM Order o"),
    @NamedQuery(name = "Order.getByCustomer", query = "SELECT o From Order o WHERE o.customer.id = :id")
})
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ORDER_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "CUSTOMER_ID")
    private Customer customer;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderLine> orderLines = new ArrayList<>();

    public Order() {}

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void addOrderLine(OrderLine orderLine) {
        orderLine.setOrder(this);
        if(!orderLines.contains(orderLine)) {
            orderLines.add(orderLine);
        }
    }

    public int getPrice() {
        int sum = 0;
        for(OrderLine orderLine: orderLines) {
            sum += orderLine.getPrice();
        }
        return sum;
    }
}
