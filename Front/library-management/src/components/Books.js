import React, { useState, useEffect } from 'react';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faPlus, faTrash } from '@fortawesome/free-solid-svg-icons';
import FormModal from './FormModal';

const Books = () => {
    const [books, setBooks] = useState([]);
    const [authors, setAuthors] = useState([]);
    const [categories, setCategories] = useState([]);
    const [isModalOpen, setIsModalOpen] = useState(false);
    const [modalData, setModalData] = useState(null);

    useEffect(() => {
        fetchBooks();
        fetchAuthors();
        fetchCategories();
    }, []);

    const fetchBooks = async () => {
        try {
            const response = await fetch('http://localhost:8080/api/livre');
            if (!response.ok) {
                throw new Error('Failed to fetch books');
            }
            const data = await response.json();
            console.log('Books fetched:', data);
            setBooks(data);
        } catch (error) {
            console.error('Error fetching books:', error);
        }
    };

    const fetchAuthors = async () => {
        try {
            const response = await fetch('http://localhost:8080/api/auteur');
            if (!response.ok) {
                throw new Error('Failed to fetch authors');
            }
            const data = await response.json();
            console.log('Authors fetched:', data);
            setAuthors(data);
        } catch (error) {
            console.error('Error fetching authors:', error);
        }
    };

    const fetchCategories = async () => {
        try {
            const response = await fetch('http://localhost:8080/api/category');
            if (!response.ok) {
                throw new Error('Failed to fetch categories');
            }
            const data = await response.json();
            console.log('Categories fetched:', data);
            setCategories(data);
        } catch (error) {
            console.error('Error fetching categories:', error);
        }
    };

    const handleAddBook = async (newBook) => {
        try {
            const bookToAdd = {
                titre: newBook.titre,
                auteur: { nom: newBook.auteur },
                category: { nom: newBook.category }
            };

            const response = await fetch('http://localhost:8080/api/livre', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(bookToAdd),
            });
            if (!response.ok) {
                const errorMessage = await response.text();
                throw new Error(errorMessage);
            }
            await fetchBooks();
            await fetchAuthors();
            await fetchCategories();
            setIsModalOpen(false);
        } catch (error) {
            console.error('Error adding book:', error);
            alert(`Error adding book: ${error.message}`);
        }
    };

    const handleDeleteBook = async (id) => {
        try {
            const response = await fetch(`http://localhost:8080/api/livre/${id}`, {
                method: 'DELETE',
            });
            if (!response.ok) {
                const errorMessage = await response.text();
                throw new Error(errorMessage);
            }
            fetchBooks();
        } catch (error) {
            console.error('Error deleting book:', error);
            alert(`Error deleting book: ${error.message}`);
        }
    };

    const openModal = (book = null) => {
        setModalData(book);
        setIsModalOpen(true);
    };

    return (
        <div className="books">
            <h1>Books</h1>
            <button className="button button-add" onClick={() => openModal()}><FontAwesomeIcon icon={faPlus} /> Add Book</button>
            <table className="table">
                <thead>
                    <tr>
                        <th>Title</th>
                        <th>Author</th>
                        <th>Category</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    {books.map(book => {
                        const authorName = authors.find(a => a.livres.some(livre => livre.id === book.id))?.nom || 'Unknown Author';
                        const categoryName = categories.find(c => c.livres.some(livre => livre.id === book.id))?.nom || 'Unknown Category';
                        return (
                            <tr key={book.id}>
                                <td>{book.titre || 'Unknown Title'}</td>
                                <td>{authorName}</td>
                                <td>{categoryName}</td>
                                <td>
                                    <button className="button button-delete" onClick={() => handleDeleteBook(book.id)}><FontAwesomeIcon icon={faTrash} /> Delete</button>
                                </td>
                            </tr>
                        );
                    })}
                </tbody>
            </table>
            {isModalOpen && (
                <FormModal
                    title={modalData ? "Edit Book" : "Add Book"}
                    fields={[
                        { name: 'titre', label: 'Title', value: modalData ? modalData.titre : '' },
                        { name: 'auteur', label: 'Author', type: 'select', options: authors.map(author => ({ value: author.nom, label: author.nom })), value: modalData ? modalData.auteur?.nom : '' },
                        { name: 'category', label: 'Category', type: 'select', options: categories.map(category => ({ value: category.nom, label: category.nom })), value: modalData ? modalData.category?.nom : '' },
                    ]}
                    onSubmit={handleAddBook}
                    onClose={() => setIsModalOpen(false)}
                />
            )}
        </div>
    );
};

export default Books;
