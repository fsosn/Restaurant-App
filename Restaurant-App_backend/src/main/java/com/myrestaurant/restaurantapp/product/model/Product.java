// package com.myrestaurant.restaurantapp.product.model;

// import jakarta.persistence.*;
// import lombok.AllArgsConstructor;
// import lombok.Data;
// import lombok.NoArgsConstructor;

// import java.math.BigDecimal;

// @Entity
// @Table(name = "products")
// @Data
// @NoArgsConstructor
// @AllArgsConstructor
// public class Product {
//     @Id
//     @SequenceGenerator(
//             name = "products_sequence",
//             sequenceName = "products_sequence",
//             allocationSize = 1
//     )
//     @GeneratedValue(
//             strategy = GenerationType.SEQUENCE,
//             generator = "products_sequence"
//     )
//     private Long productID;

//     @Column(unique = true)
//     private String name;

//     private String description;

//     private BigDecimal price;

//     @Enumerated(EnumType.STRING)
//     private Ingredients ingredients;

//     private String imageLink;
// }


package com.myrestaurant.restaurantapp.product.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "products")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productID;

    private String name;

    private String description;

    private Double price;

    private Ingredients ingredients;

    private String imageLink;
}
