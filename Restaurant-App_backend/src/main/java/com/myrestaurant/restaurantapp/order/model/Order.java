// package com.myrestaurant.restaurantapp.order.model;

// import jakarta.persistence.*;
// import lombok.AllArgsConstructor;
// import lombok.Builder;
// import lombok.Data;
// import lombok.NoArgsConstructor;

// import java.time.LocalDate;

// @Entity
// @Table(name = "orders")
// @Data
// @NoArgsConstructor
// @AllArgsConstructor
// @Builder
// public class Order {

//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     @Column(name = "order_id")
//     private Long orderId;

//     @Column(name = "user_id")
//     private Long userId;

//     @Column(name = "date")
//     private LocalDate date;
// }

package com.myrestaurant.restaurantapp.order.model;

import com.myrestaurant.restaurantapp.user.model.User;
import com.myrestaurant.restaurantapp.order.orderItem.model.OrderItem;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderID;

    @ManyToOne
    @JoinColumn(name = "userID", referencedColumnName = "userID")
    private User user;

    private LocalDate date;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems;
}
