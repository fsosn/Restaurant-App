/*package com.myrestaurant.restaurantapp;

import com.myrestaurant.restaurantapp.user.model.Role;
import com.myrestaurant.restaurantapp.user.model.User;
import com.myrestaurant.restaurantapp.user.repository.UserRepository;
import com.myrestaurant.restaurantapp.order.model.Order;
import com.myrestaurant.restaurantapp.order.repository.OrderRepository;
import com.myrestaurant.restaurantapp.order.orderItem.model.OrderItem;
import com.myrestaurant.restaurantapp.order.orderItem.repository.OrderItemRepository;
import com.myrestaurant.restaurantapp.product.model.Product;
import com.myrestaurant.restaurantapp.product.model.Ingredients;
import com.myrestaurant.restaurantapp.product.repository.ProductRepository;
import com.myrestaurant.restaurantapp.shoppingCart.model.ShoppingCart;
import com.myrestaurant.restaurantapp.shoppingCart.repository.ShoppingCartRepository;
import com.myrestaurant.restaurantapp.shoppingCart.cartItem.model.CartItem;
import com.myrestaurant.restaurantapp.shoppingCart.cartItem.repository.CartItemRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderItemRepository orderItemRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ShoppingCartRepository shoppingCartRepository;
    @Autowired
    private CartItemRepository cartItemRepository;

    @Override
    public void run(String... args) throws Exception {
        // Load Users
        
        User admin1 = new User(null, "Karol", "password1", Role.ADMIN);
        User admin2 = new User(null, "Krzysztof", "password2", Role.ADMIN);
        User admin3 = new User(null, "Filip", "password3", Role.ADMIN);
        User admin4 = new User(null, "Bartłomiej", "password4", Role.ADMIN);
        User admin5 = new User(null, "Kacper", "password5", Role.ADMIN);
        User user1 = new User(null, "user1", "haslo1", Role.USER);
        User user2 = new User(null, "user2", "haslo2", Role.USER);
        User user3 = new User(null, "user3", "haslo3", Role.USER);
        User user4 = new User(null, "user4", "haslo4", Role.USER);
        User user5 = new User(null, "user5", "haslo5", Role.USER);
    
        userRepository.saveAll(Arrays.asList(admin1, admin2,admin3,admin4,admin5,user1, user2,user3,user4,user5));

        // Load Products
        Product product1 = new Product(null, "Margherita Pizza", "Nasza sygnaturowa Margherita to hołd dla klasycznej włoskiej kuchni, z ręcznie robionym sosem pomidorowym, świeżą mozzarellą i listkami bazylii z naszego ogrodu.", 12.50, Arrays.asList(Ingredients.cheese, Ingredients.tomatoes, Ingredients.basil), 560, "link_to_image");
        Product product2 = new Product(null, "Pepperoni Pizza", "Soczyste plastry pepperoni, przyprawione wyselekcjonowanymi ziołami, na puszystym cieście z dodatkiem bogatej mozzarelli, idealne dla miłośników ostrzejszych smaków.", 14.95, Arrays.asList(Ingredients.cheese, Ingredients.pepperoni), 620, "link_to_image");
        Product product3 = new Product(null, "Hawaiian Pizza", "Egzotyczne połączenie słodkich kawałków ananasa i aromatycznej szynki na cienkim cieście tworzy niezapomniane doznania dla tych, którzy lubią połączenie słodko-słonych smaków.", 15.75, Arrays.asList(Ingredients.cheese, Ingredients.ham, Ingredients.pineapple), 540, "link_to_image");
        Product product4 = new Product(null, "Meat Lovers Pizza", "Nasza pizza dla miłośników mięsa to prawdziwa uczta: kombinacja pepperoni, włoskiej kiełbasy, bekonu i szynki na bogatym sosie pomidorowym.", 17.90, Arrays.asList(Ingredients.cheese, Ingredients.pepperoni, Ingredients.sausage, Ingredients.bacon, Ingredients.ham), 720, "link_to_image");
        Product product5 = new Product(null, "Veggie Pizza", "Pełna świeżych, sezonowych warzyw z lokalnych upraw, ta pizza zachwyci każdego, kto ceni sobie lekkość i świeżość składników.", 13.50, Arrays.asList(Ingredients.cheese, Ingredients.tomatoes, Ingredients.olives, Ingredients.mushrooms, Ingredients.onions, Ingredients.pepper), 430, "link_to_image");
        Product product6 = new Product(null, "BBQ Chicken Pizza", "Kawałki grillowanego kurczaka marynowanego w domowym sosie BBQ, z dodatkiem czerwonej cebuli i świeżej kolendry, tworzą wyrazisty i niezapomniany smak.", 16.20, Arrays.asList(Ingredients.cheese, Ingredients.chicken, Ingredients.bbq_sauce, Ingredients.onions), 580, "link_to_image");
        Product product7 = new Product(null, "Buffalo Chicken Pizza", "Pikantny sos buffalo i kawałki kurczaka, podkreślone sosem z niebieskiego sera, sprawiają, że ta pizza jest idealna dla poszukiwaczy silnych wrażeń smakowych.", 16.00, Arrays.asList(Ingredients.cheese, Ingredients.chicken, Ingredients.buffalo_sauce, Ingredients.onions), 600, "link_to_image");
        Product product8 = new Product(null, "Seafood Pizza", "Świeże owoce morza, w tym krewetki, kałamarnica i małże, na cienkim i chrupiącym cieście, z delikatnym sosem czosnkowym, idealna dla smakoszy morskich przysmaków.", 18.45, Arrays.asList(Ingredients.cheese, Ingredients.seafood), 510, "link_to_image");
        Product product9 = new Product(null, "Four Cheese Pizza", "Kremowa mieszanka czterech serów: mozzarelli, parmezanu, gorgonzoli i ricotty, rozpływa się w ustach, dostarczając bogatego i głębokiego smaku.", 14.00, Arrays.asList(Ingredients.mozzarella, Ingredients.parmesan, Ingredients.gorgonzola, Ingredients.ricotta), 480, "link_to_image");
        Product product10 = new Product(null, "Mushroom Truffle Pizza", "Nasza luksusowa pizza z dodatkiem aromatycznych pieczarek i oleju truflowego to prawdziwy przysmak dla koneserów nietuzinkowych kombinacji.", 19.30, Arrays.asList(Ingredients.cheese, Ingredients.mushrooms, Ingredients.truffle_oil), 450, "link_to_image");
        productRepository.saveAll(Arrays.asList(product1, product2,product3,product4,product5,product6, product7,product8,product9,product10));


        
        // Load Orders
        Order order1 = new Order(null, user1, LocalDate.now(), Collections.emptyList());
        Order order2 = new Order(null, user2, LocalDate.now(), Collections.emptyList());
        Order order3 = new Order(null, user3, LocalDate.now(), Collections.emptyList());
        Order order4 = new Order(null, user4, LocalDate.now(), Collections.emptyList());
        Order order5 = new Order(null, user5, LocalDate.now(), Collections.emptyList());
        orderRepository.save(order1);
        orderRepository.save(order2);
        orderRepository.save(order3);
        orderRepository.save(order4);
        orderRepository.save(order5);


        // Load Order Items
        OrderItem orderItem1 = new OrderItem(null, order1, product10, 1);
        OrderItem orderItem2 = new OrderItem(null, order1, product9, 2);
        orderItemRepository.save(orderItem1);
        orderItemRepository.save(orderItem2);
        order1.setOrderItems(Arrays.asList(orderItem1,orderItem2));
        orderRepository.save(order1);
        OrderItem orderItem3 = new OrderItem(null, order2, product8, 2);
        OrderItem orderItem4 = new OrderItem(null, order2, product7, 1);
        orderItemRepository.save(orderItem3);
        orderItemRepository.save(orderItem4);
        order2.setOrderItems(Arrays.asList(orderItem3,orderItem4));
        orderRepository.save(order2);
        OrderItem orderItem5 = new OrderItem(null, order3, product6, 1);
        OrderItem orderItem6 = new OrderItem(null, order3, product5, 2);
        orderItemRepository.save(orderItem5);
        orderItemRepository.save(orderItem6);
        order3.setOrderItems(Arrays.asList(orderItem5,orderItem6));
        orderRepository.save(order3);
        OrderItem orderItem7 = new OrderItem(null, order4, product4, 2);
        OrderItem orderItem8 = new OrderItem(null, order4, product3, 1);
        orderItemRepository.save(orderItem7);
        orderItemRepository.save(orderItem8);
        order4.setOrderItems(Arrays.asList(orderItem7,orderItem8));
        orderRepository.save(order4);
        OrderItem orderItem9 = new OrderItem(null, order5, product2, 1);
        OrderItem orderItem10 = new OrderItem(null, order5, product1, 2);
        orderItemRepository.save(orderItem9);
        orderItemRepository.save(orderItem10);
        order5.setOrderItems(Arrays.asList(orderItem9,orderItem10));
        orderRepository.save(order5);

        // Load Shopping Carts
        ShoppingCart shoppingCart1 = new ShoppingCart(null, user5, Collections.emptyList());
        ShoppingCart shoppingCart2 = new ShoppingCart(null, user4, Collections.emptyList());
        ShoppingCart shoppingCart3 = new ShoppingCart(null, user3, Collections.emptyList());
        ShoppingCart shoppingCart4 = new ShoppingCart(null, user2, Collections.emptyList());
        ShoppingCart shoppingCart5 = new ShoppingCart(null, user1, Collections.emptyList());
        shoppingCartRepository.saveAll(Arrays.asList(shoppingCart1,shoppingCart2,shoppingCart3,shoppingCart4,shoppingCart5));

        // Load Cart Items
        CartItem cartItem5 = new CartItem(null, shoppingCart1, product10, 3);
        cartItemRepository.save(cartItem5);
        shoppingCart1.setCartItems(Collections.singletonList(cartItem5));
        shoppingCartRepository.save(shoppingCart1);

        CartItem cartItem4 = new CartItem(null, shoppingCart2, product7, 2);
        cartItemRepository.save(cartItem4);
        shoppingCart2.setCartItems(Collections.singletonList(cartItem4));
        shoppingCartRepository.save(shoppingCart2);

        CartItem cartItem3 = new CartItem(null, shoppingCart3, product5, 9);
        cartItemRepository.save(cartItem3);
        shoppingCart3.setCartItems(Collections.singletonList(cartItem3));
        shoppingCartRepository.save(shoppingCart3);

        CartItem cartItem2 = new CartItem(null, shoppingCart4, product2, 4);
        cartItemRepository.save(cartItem2);
        shoppingCart4.setCartItems(Collections.singletonList(cartItem2));
        shoppingCartRepository.save(shoppingCart4);

        CartItem cartItem1 = new CartItem(null, shoppingCart5, product1, 1);
        cartItemRepository.save(cartItem1);
        shoppingCart5.setCartItems(Collections.singletonList(cartItem1));
        shoppingCartRepository.save(shoppingCart5);
    }
}
*/