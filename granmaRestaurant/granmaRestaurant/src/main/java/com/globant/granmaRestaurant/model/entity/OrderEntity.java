package com.globant.granmaRestaurant.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders")
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_order")
    private Integer idOrder;

    @Column(name = "UUID")
    private String uuid;

    @Column(name = "creation_date_time", nullable = false)
    private Timestamp creationDateTime;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id", referencedColumnName = "id_customer")
    private CustomerEntity customer;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "combo_id", referencedColumnName = "id_combo")
    private ComboEntity combo;


    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Column(name = "extra_information")
    private String extraInformation;

    @Column(name = "subtotal", nullable = false)
    private Double subtotal;

    @Column(name = "vat_tax", nullable = false )
    private Double vatTax;

    @Column(name = "grand_total", nullable = false)
    private Double grandTotal;

    @Column(name = "delivered", nullable = false)
    private Boolean delivered;

    @Column(name = "delivered_date")
    private Timestamp deliveredDate;

    @Column(name = "active", nullable = false)
    private Boolean active = true;

    @Column(name = "combo_uuid", nullable = false)
    private String comboUuid;

    @Column(name = "customer_document", nullable = false)
    private String customerDocument;
}
