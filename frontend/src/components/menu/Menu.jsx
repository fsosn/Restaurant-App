import { useState, useEffect } from 'react';
import './Menu.css';

const Menu = ({ addToCart }) => {
    const [products, setProducts] = useState([]);
    const [showDialog, setShowDialog] = useState(false);
    const [selectedProduct, setSelectedProduct] = useState(null);

    useEffect(() => {
        fetch('http://localhost:8080/products')
            .then((response) => response.json())
            .then((data) => setProducts(data))
            .catch((error) => console.error('Error fetching products:', error));
    }, []);

    const handleShowDialog = (product) => {
        setSelectedProduct(product);
        setShowDialog(true);
    };

    const handleCloseDialog = () => {
        setSelectedProduct(null);
        setShowDialog(false);
    };

    return (
        <>
            {showDialog && selectedProduct && (
                <Dialog
                    product={selectedProduct}
                    onClose={handleCloseDialog}
                    addToCart={addToCart}
                />
            )}
            <div className="container-fluid offset">
                <h1 className={'menu-title'}>Menu</h1>
                <div className={'menu-container'}>
                    <div className="menu-items">
                        {products.map((product) => (
                            <div
                                key={product.productID}
                                className="menu-item"
                                onClick={() => {
                                    handleShowDialog(product);
                                }}
                            >
                                <div className={'item-container'}>
                                    <div className={'image-container'}>
                                        <img
                                            src={'./pizza.png'}
                                            alt={'Pizza image'}
                                            className={'pizza-image'}
                                        />
                                    </div>
                                    <div>
                                        <p className={'pizza-name'}>{product.name}</p>
                                    </div>
                                    <div className={'cont'}>
                                        <div>
                                            <p className={'price'}>${product.price.toFixed(2)}</p>
                                        </div>
                                        <div>
                                            <button
                                                className={'btn-add'}
                                                onClick={(e) => {
                                                    e.stopPropagation();
                                                    addToCart(
                                                        product.name,
                                                        product.price
                                                    );
                                                }}
                                            >
                                                <img src="./add.svg" alt="Add icon" />
                                            </button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        ))}
                    </div>
                </div>
            </div>
        </>
    );
};

export default Menu;

const Dialog = ({ product, addToCart, onClose }) => {
    return (
        <div className="dialog" onClick={onClose}>
            <div className={'image-container'}>
                <img
                    src={'./pizza.png'}
                    alt={'Pizza image'}
                    className={'pizza-image'}
                />
            </div>
            <div>
                <p className={'pizza-name'}>{product.name}</p>
            </div>
            <div>
                <p className={'pizza-description'}>{product.description}</p>
            </div>
            <div>
                <h3>Ingredients</h3>
                <p className={'pizza-ingredients'}>{product.ingredients.join(', ')}</p>
            </div>
            <div className="dialog-bottom">
                <div>
                    <p className={'price'}>${product.price.toFixed(2)}</p>
                </div>
                <button
                    className={'btn-add'}
                    onClick={() =>
                        addToCart(product.name, product.price)
                    }
                >
                    <img src="./add.svg" alt="Add icon" />
                </button>
            </div>
        </div>
    );
};