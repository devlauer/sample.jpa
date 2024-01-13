package de.elnarion.sample.jpa.domain.unidirectional;

import javax.persistence.*;

@Entity
@Table(name = "ORDER_TABLE")
@SequenceGenerator(name = "order_seq",sequenceName = "order_seq",allocationSize = 1)
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "order_seq")
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column
    private String orderName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }
}
