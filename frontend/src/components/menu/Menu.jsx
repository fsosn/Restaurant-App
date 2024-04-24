import {useState, useEffect} from 'react';
import "./Menu.css";

const Menu = () => {
    const [products, setProducts] = useState([]);

    useEffect(() => {
        fetch('http://localhost:8080/products')
            .then(response => response.json())
            .then(data => setProducts(data))
            .catch(error => console.error('Error fetching products:', error));
    }, []);

    const renderPizzaMenu = () => {
        return (
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
                                        <p className={"price"}>${product.price}</p></div>
                                    <div>
                                        <button className={"btn-add"}>
                                            <img src="./add.svg" alt="Add icon"/>
                                        </button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    ))}
                </div>
            </div>
        );
    };

    return (
        <div className="container-fluid offset">
            <h1 className={"menu-title"}>Menu</h1>
            {renderPizzaMenu()}
        </div>
    );
};

export default Menu;
