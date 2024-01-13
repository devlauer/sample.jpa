package de.elnarion.sample.jpa.domain.unidirectional;

import javax.persistence.*;

@Entity
@SequenceGenerator(name = "orderitem_seq",sequenceName = "orderitem_seq",allocationSize = 1)
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "orderitem_seq")
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column
    private String itemName;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "order_fk")
    private Order order;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
