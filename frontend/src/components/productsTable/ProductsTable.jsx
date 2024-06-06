import React, { useState } from "react";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faAdd, faTrash, faPenToSquare } from "@fortawesome/free-solid-svg-icons";
import "./ProductsTable.css";
import PropTypes from "prop-types";
import api from "../../services/api.jsx";

const ProductsTable = ({ products, onAdd, onEdit, onDelete }) => {
  const [showForm, setShowForm] = useState(false);
  const [newProduct, setNewProduct] = useState({
    name: "",
    description: "",
    price: 0,
    calories: 0,
  });
  const [editedProduct, setEditedProduct] = useState(null);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setNewProduct({ ...newProduct, [name]: value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const addedProduct = await api.createProduct(newProduct);
      onAdd(addedProduct);
      setShowForm(false);
      setNewProduct({
        name: "",
        description: "",
        price: 0,
        calories: 0,
      });
    } catch (error) {
      console.error("Error adding product:", error);
    }
  };

  const handleEditProduct = (productID) => {
    const editedProduct = products.find((product) => product.productID === productID);
    setEditedProduct(editedProduct);
  };

  const handleFormChange = (e) => {
    const { name, value } = e.target;
    setEditedProduct((prevState) => ({
      ...prevState,
      [name]: value,
    }));
  };

  const handleFormSubmit = async (e) => {
    e.preventDefault();
    try {
      await api.updateProduct(editedProduct.productID, editedProduct);
      onEdit(editedProduct);
      setEditedProduct(null);
    } catch (error) {
      console.error("Error updating product:", error);
    }
  };

  const handleDeleteProduct = async (productID) => {
    try {
      await api.deleteProduct(productID);
      onDelete(productID);
      
    } catch (error) {
      console.error("Error deleting product:", error);
    }
  };

  return (
    <div className={"page-content vh-100"}>
      <button className="btn add-btn" onClick={() => setShowForm(true)}>
        <FontAwesomeIcon icon={faAdd} /> Add Product
      </button>
      
      {showForm && (
        <form onSubmit={handleSubmit} className="form-font">
          <div className="form-group">
            <input
              type="text"
              name="name"
              value={newProduct.name}
              onChange={handleChange}
              className="form-control mb-3 mx-2"
              placeholder="Name"
              required
            />
          </div>
          <div className="form-group">
            <input
              type="text"
              name="description"
              value={newProduct.description}
              onChange={handleChange}
              className="form-control mb-3 mx-2"
              placeholder="Description"
              required
            />
          </div>
          <div className="form-group row">
            <div className="col-sm-6">
              <input
                type="number"
                name="price"
                value={newProduct.price}
                onChange={handleChange}
                className="form-control mx-2"
                placeholder="Price"
                required
              />
            </div>
            <div className="col-sm-6">
              <input
                type="number"
                name="calories"
                value={newProduct.calories}
                onChange={handleChange}
                className="form-control mx-2"
                placeholder="Calories"
                required
              />
            </div>
          </div>
          <button type="submit" className="btn add-btn">
            Add
          </button>
          <button
            type="button"
            className="btn btn-danger"
            onClick={() => setShowForm(false)}
          >
            Cancel
          </button>
        </form>
      )}

      <table className="table mt-3 table-hover table-responsive">
        <thead>
        <tr>
            <th>Name</th>
            <th>Description</th>
            <th>Price</th>
            <th>Calories</th>
            <th>Actions</th>
          </tr>
        </thead>
        <tbody>
            {products.map((product) => (
                <tr key={product.productID}>
                <td>{product.name}</td>
                <td>{product.description}</td>
                <td>{product.price}</td>
                <td>{product.calories}</td>
                <td>
                    <button
                    className="btn edit-btn"
                    onClick={() => handleEditProduct(product.productID)}
                    >
                    <FontAwesomeIcon icon={faPenToSquare} />
                    </button>
                    <button
                    className="btn btn-danger"
                    onClick={() => handleDeleteProduct(product.productID)}
                    >
                    <FontAwesomeIcon icon={faTrash} />
                    </button>
                </td>
                </tr>
            ))}
        </tbody>
      </table>

      {editedProduct && (
        <div>
          <h2 className="edit-prod">Edit Product</h2>
          <form onSubmit={handleFormSubmit} className="form-font">
            <div className="form-group">
              <input
                type="text"
                id="name"
                name="name"
                className="form-control mb-3 mx-2"
                value={editedProduct.name}
                onChange={handleFormChange}
              />
            </div>
            <div className="form-group">
              <input
                type="text"
                id="description"
                name="description"
                className="form-control mb-3 mx-2"
                value={editedProduct.description}
                onChange={handleFormChange}
              />
            </div>
            <div className="form-group row">
              <div className="col-sm-6">
                <input
                  type="number"
                  id="price"
                  name="price"
                  className="form-control mx-2"
                  value={editedProduct.price}
                  onChange={handleFormChange}
                />
              </div>
              <div className="col-sm-6">
                <input
                  type="number"
                  id="calories"
                  name="calories"
                  className="form-control mx-2"
                  value={editedProduct.calories}
                  onChange={handleFormChange}
                />
              </div>
            </div>
            <button type="submit" className="btn add-btn">Save Changes</button>
          </form>
        </div>
      )}
    </div>
  );
};

ProductsTable.propTypes = {
  products: PropTypes.array.isRequired,
  onAdd: PropTypes.func.isRequired,
  onEdit: PropTypes.func.isRequired,
  onDelete: PropTypes.func.isRequired,
};

export default ProductsTable;