import React, { useState, useEffect } from 'react';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faPlus, faTrash } from '@fortawesome/free-solid-svg-icons';
import FormModal from './FormModal';

const Categories = () => {
    const [categories, setCategories] = useState([]);
    const [isModalOpen, setIsModalOpen] = useState(false);
    const [modalData, setModalData] = useState(null);

    useEffect(() => {
        fetchCategories();
    }, []);

    const fetchCategories = async () => {
        try {
            const response = await fetch('http://localhost:8080/api/category');
            if (!response.ok) {
                throw new Error('Failed to fetch categories');
            }
            const data = await response.json();
            console.log('Fetched categories:', data);
            setCategories(data);
        } catch (error) {
            console.error('Error fetching categories:', error);
        }
    };

    const handleAddCategory = async (newCategory) => {
        try {
            const response = await fetch('http://localhost:8080/api/category', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(newCategory),
            });
            if (!response.ok) {
                throw new Error('Failed to add category');
            }
            fetchCategories();
            setIsModalOpen(false);
        } catch (error) {
            console.error('Error adding category:', error);
        }
    };

    const handleDeleteCategory = async (id) => {
        try {
            const response = await fetch(`http://localhost:8080/api/category/${id}`, {
                method: 'DELETE',
            });
            if (!response.ok) {
                throw new Error('Failed to delete category');
            }
            fetchCategories();
        } catch (error) {
            console.error('Error deleting category:', error);
        }
    };

    const openModal = (category = null) => {
        setModalData(category);
        setIsModalOpen(true);
    };

    return (
        <div className="categories">
            <h1>Categories</h1>
            <button className="button button-add" onClick={() => openModal()}><FontAwesomeIcon icon={faPlus} /> Add Category</button>
            <table className="table">
                <thead>
                    <tr>
                        <th>Name</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    {categories.map(category => (
                        <tr key={category.id}>
                            <td>{category.nom}</td>
                            <td>
                                <button className="button button-delete" onClick={() => handleDeleteCategory(category.id)}><FontAwesomeIcon icon={faTrash} /> Delete</button>
                            </td>
                        </tr>
                    ))}
                </tbody>
            </table>
            {isModalOpen && (
                <FormModal
                    title={modalData ? "Edit Category" : "Add Category"}
                    fields={[
                        { name: 'nom', label: 'Name', value: modalData ? modalData.nom : '' },
                    ]}
                    onSubmit={handleAddCategory}
                    onClose={() => setIsModalOpen(false)}
                />
            )}
        </div>
    );
};

export default Categories;
