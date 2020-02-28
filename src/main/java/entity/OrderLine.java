package entity;
/*
 * author paepke
 * version 1.0
 */

import java.io.Serializable;
import javax.persistence.*;


@Entity
@Table(name = "OrderLine")
@NamedQuery(name = "OrderLine.deleteAllRows", query = "DELETE FROM OrderLine")
public class OrderLine implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int quantity;

    @ManyToOne
    @JoinColumn(name = "ORDER_ID")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "ITEMTYPE_ID")
    private ItemType itemType;

    public OrderLine() {
    }

    public OrderLine(int quantity, ItemType itemType) {
        this.quantity = quantity;
        this.itemType = itemType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Order getOrder() {
        return order;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public ItemType getItemType() {
        return itemType;
    }

    public void setItemType(ItemType itemType) {
        this.itemType = itemType;
    }

    public int getPrice() {
        return quantity * itemType.getPrice();
    }
}
