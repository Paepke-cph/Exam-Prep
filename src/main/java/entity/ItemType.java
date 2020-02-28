package entity;
/*
 * author paepke
 * version 1.0
 */

import java.io.Serializable;
import javax.persistence.*;


@Entity
@Table(name = "ItemType")
@NamedQueries({
    @NamedQuery(name = "ItemType.deleteAllRows", query = "DELETE FROM ItemType"),
    @NamedQuery(name = "ItemType.getAll", query = "SELECT i FROM ItemType i")
})
public class ItemType implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private int price;

    public ItemType() {
    }

    public ItemType(String name, String description, int price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
