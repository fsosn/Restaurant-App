import React, { useState } from "react";
import Page from "./template/Page.jsx";
import ProductsTable from "../components/productsTable/ProductsTable.jsx";

const ProductsPage = () => {
    const [products, setProducts] = useState([
        { id: 1, name: "Margherita", description: "Simplest Pizza", price: 12.99, calories: 2000 },
        { id: 2, name: "Pepperoni", description: "Meat Pizza", price: 14.99, calories: 3000 },
        { id: 3, name: "Havaiian", description: "Sweet Pizza", price: 13.99, calories: 2500 },
    ]);

    const handleAddProduct = (newProduct) => {
        setProducts([...products, newProduct]);
    };

    const [editedProduct, setEditedProduct] = useState(null);

    const handleEditProduct = (product) => {
        setEditedProduct(product);
    };

    const handleDeleteProduct = (productId) => {
        const updatedProducts = products.filter(product => product.id !== productId);
        setProducts(updatedProducts);
    };

    return (
        <Page pageTitle={"PizzaPal"}>
            <ProductsTable products={products} onAdd={handleAddProduct} onEdit={handleEditProduct} onDelete={handleDeleteProduct}/>
        </Page>
    );
};

export default ProductsPage;
