package com.spring.restaurant.entity;

import com.spring.restaurant.entity.auth.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@Table(name = "order_Item")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String code;


    private long totalPrice;

    private long totalQuantity;


    @ManyToMany( fetch = FetchType.LAZY)
            @JoinTable(
                    name = "orderItem_product",
                    joinColumns = @JoinColumn(name = "orderItem_id"),
                    inverseJoinColumns = @JoinColumn(name = "product_id")
            )
    List<Product> products;


    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
}
