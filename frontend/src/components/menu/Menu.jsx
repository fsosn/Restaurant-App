import {useState, useEffect} from 'react';
import "./Menu.css";

const Menu = ({addToCart}) => {
    const [products, setProducts] = useState([]);

    useEffect(() => {
        fetch('http://localhost:8080/products')
            .then(response => response.json())
            .then(data => setProducts(data))
            .catch(error => console.error('Error fetching products:', error));
    }, []);

    return (
        <div className="container-fluid offset">
            <h1 className={"menu-title"}>Menu</h1>
            <div className={"menu-container"}>
                <div className="menu-items">
                    {products.map((product) => (
                        <div key={product.productID} className="menu-item">
                            <div className={"item-container"}>
                                <div className={"image-container"}>
                                    <img src={"./pizza.png"} alt={"Pizza image"} className={"pizza-image"}/>
                                </div>
                                <div>
                                    <p className={"pizza-name"}>{product.name}</p>
                                </div>
                                <div className={"cont"}>
                                    <div>
                                        <p className={"price"}>${product.price.toFixed(2)}</p>
                                    </div>
                                    <div>
                                        <button className={"btn-add"}
                                                onClick={() => addToCart(product.name, product.price)}>
                                            <img src="./add.svg" alt="Add icon"/>
                                        </button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    ))}
                </div>
            </div>
        </div>
    );
};

export default Menu;
