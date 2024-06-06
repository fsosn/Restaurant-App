import React, { useState, useEffect } from "react";
import Page from "./template/Page.jsx";
import ProductsTable from "../components/productsTable/ProductsTable.jsx";
import api from "../services/api.jsx";

const ProductsPage = ({ toggleCartVisibility, cartItems, isCartVisible, updateCartItems }) => {
    const [products, setProducts] = useState([]);

    const fetchData = async () => {
                try {
                    const productsData = await api.getAllProducts();
                    setProducts(productsData);
                } catch (error) {
                    console.error("Error fetching products:", error);
                }
            }
            fetchData();

    useEffect(() => {
        fetchData();
    }, []);

    const handleAddProduct = (newProduct) => {
        setProducts([...products, newProduct]);
    };

    const handleEditProduct = (editedProduct) => {
        const updatedProducts = products.map((product) =>
            product.productId === editedProduct.productId ? editedProduct : product
        );
        setProducts(updatedProducts);
    };

    const handleDeleteProduct = (productId) => {
        const updatedProducts = products.filter(product => product.productId !== productId);
        setProducts(updatedProducts);
        fetchData();
    };

    return (
        <Page pageTitle={"PizzaPal"} toggleCartVisibility={toggleCartVisibility} cartItems={cartItems} isCartVisible={isCartVisible} updateCartItems={updateCartItems}>
            <ProductsTable products={products} onAdd={handleAddProduct} onEdit={handleEditProduct} onDelete={handleDeleteProduct}/>
        </Page>
    );
};

export default ProductsPage;