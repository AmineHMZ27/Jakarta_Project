import React, { useState, useEffect } from 'react';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faPlus, faTrash } from '@fortawesome/free-solid-svg-icons';
import FormModal from './FormModal';

const Loans = () => {
    const [loans, setLoans] = useState([]);
    const [members, setMembers] = useState([]);
    const [books, setBooks] = useState([]);
    const [isModalOpen, setIsModalOpen] = useState(false);
    const [modalData, setModalData] = useState(null);

    useEffect(() => {
        fetchLoans();
        fetchMembers();
        fetchBooks();
    }, []);

    const fetchLoans = async () => {
        try {
            const response = await fetch('http://localhost:8080/api/pret');
            if (!response.ok) {
                throw new Error('Failed to fetch loans');
            }
            const data = await response.json();
            console.log('Fetched loans:', data);
            setLoans(data);
        } catch (error) {
            console.error('Error fetching loans:', error);
        }
    };

    const fetchMembers = async () => {
        try {
            const response = await fetch('http://localhost:8080/api/client');
            if (!response.ok) {
                throw new Error('Failed to fetch members');
            }
            const data = await response.json();
            console.log('Fetched members:', data);
            setMembers(data);
        } catch (error) {
            console.error('Error fetching members:', error);
        }
    };

    const fetchBooks = async () => {
        try {
            const response = await fetch('http://localhost:8080/api/livre');
            if (!response.ok) {
                throw new Error('Failed to fetch books');
            }
            const data = await response.json();
            console.log('Fetched books:', data);
            setBooks(data);
        } catch (error) {
            console.error('Error fetching books:', error);
        }
    };

    const handleAddLoan = async (newLoan) => {
        try {
            const bookTitle = books.find(book => book.id === parseInt(newLoan.book, 10))?.titre;
            const memberName = members.find(member => member.id === parseInt(newLoan.member, 10))?.nom;

            const response = await fetch(`http://localhost:8080/api/pret?titreLivre=${bookTitle}&nomClient=${memberName}`, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
            });
            if (!response.ok) {
                const errorMessage = await response.text();
                throw new Error(errorMessage);
            }
            fetchLoans();
            setIsModalOpen(false);
        } catch (error) {
            console.error('Error adding loan:', error);
            alert(`Error adding loan: ${error.message}`);
        }
    };

    const handleDeleteLoan = async (id) => {
        try {
            const response = await fetch(`http://localhost:8080/api/pret/${id}`, {
                method: 'DELETE',
            });
            if (!response.ok) {
                const errorMessage = await response.text();
                throw new Error(errorMessage);
            }
            fetchLoans();
        } catch (error) {
            console.error('Error deleting loan:', error);
            alert(`Error deleting loan: ${error.message}`);
        }
    };

    const openModal = (loan = null) => {
        setModalData(loan);
        setIsModalOpen(true);
    };

    return (
        <div className="loans">
            <h1>Loans</h1>
            <button className="button button-add" onClick={() => openModal()}><FontAwesomeIcon icon={faPlus} /> Add Loan</button>
            <table className="table">
                <thead>
                    <tr>
                        <th>Member</th>
                        <th>Book</th>
                        <th>Start Date</th>
                        <th>End Date</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    {loans.map(loan => {
                        const member = loan.client;
                        const memberName = member ? `${member.nom} ${member.prenom}` : 'Unknown Member';
                        const book = loan.livre;
                        const bookTitle = book ? book.titre : 'Unknown Book';
                        return (
                            <tr key={loan.id}>
                                <td>{memberName}</td>
                                <td>{bookTitle}</td>
                                <td>{loan.dateDebut || 'No Start Date'}</td>
                                <td>{loan.dateFin || 'No End Date'}</td>
                                <td>
                                    <button className="button button-delete" onClick={() => handleDeleteLoan(loan.id)}><FontAwesomeIcon icon={faTrash} /> Delete</button>
                                </td>
                            </tr>
                        );
                    })}
                </tbody>
            </table>
            {isModalOpen && (
                <FormModal
                    title={modalData ? "Edit Loan" : "Add Loan"}
                    fields={[
                        { name: 'member', label: 'Member', type: 'select', options: members.map(member => ({ value: member.id, label: `${member.nom} ${member.prenom}` })), value: modalData ? modalData.client?.id : '' },
                        { name: 'book', label: 'Book', type: 'select', options: books.map(book => ({ value: book.id, label: book.titre })), value: modalData ? modalData.livre?.id : '' },
                    ]}
                    onSubmit={handleAddLoan}
                    onClose={() => setIsModalOpen(false)}
                />
            )}
        </div>
    );
};

export default Loans;
