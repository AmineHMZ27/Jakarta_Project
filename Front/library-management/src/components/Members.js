import React, { useState, useEffect } from 'react';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faPlus, faEdit, faTrash } from '@fortawesome/free-solid-svg-icons';
import FormModal from './FormModal';

const Members = () => {
    const [members, setMembers] = useState([]);
    const [isModalOpen, setIsModalOpen] = useState(false);
    const [modalData, setModalData] = useState(null);

    useEffect(() => {
        fetchMembers();
    }, []);

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

    const handleAddMember = async (newMember) => {
        try {
            const response = await fetch('http://localhost:8080/api/client', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(newMember),
            });
            if (!response.ok) {
                throw new Error('Failed to add member');
            }
            fetchMembers();
            setIsModalOpen(false);
        } catch (error) {
            console.error('Error adding member:', error);
        }
    };

    const handleEditMember = async (updatedMember) => {
        try {
            const response = await fetch(`http://localhost:8080/api/client/${updatedMember.id}`, {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(updatedMember),
            });
            if (!response.ok) {
                throw new Error('Failed to update member');
            }
            fetchMembers();
            setIsModalOpen(false);
        } catch (error) {
            console.error('Error updating member:', error);
        }
    };

    const handleDeleteMember = async (id) => {
        try {
            const response = await fetch(`http://localhost:8080/api/client/${id}`, {
                method: 'DELETE',
            });
            if (!response.ok) {
                throw new Error('Failed to delete member');
            }
            fetchMembers();
        } catch (error) {
            console.error('Error deleting member:', error);
        }
    };

    const openModal = (member = null) => {
        setModalData(member);
        setIsModalOpen(true);
    };

    return (
        <div className="members">
            <h1>Members</h1>
            <button className="button button-add" onClick={() => openModal()}><FontAwesomeIcon icon={faPlus} /> Add Member</button>
            <table className="table">
                <thead>
                    <tr>
                        <th>First Name</th>
                        <th>Last Name</th>
                        <th>Email</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    {members.map(member => (
                        <tr key={member.id}>
                            <td>{member.nom || 'Unknown'}</td>
                            <td>{member.prenom || 'Unknown'}</td>
                            <td>{member.email || 'Unknown'}</td>
                            <td>
                                <button className="button button-edit" onClick={() => openModal(member)}><FontAwesomeIcon icon={faEdit} /> Edit</button>
                                <button className="button button-delete" onClick={() => handleDeleteMember(member.id)}><FontAwesomeIcon icon={faTrash} /> Delete</button>
                            </td>
                        </tr>
                    ))}
                </tbody>
            </table>
            {isModalOpen && (
                <FormModal
                    title={modalData ? "Edit Member" : "Add Member"}
                    fields={[
                        { name: 'nom', label: 'First Name', value: modalData ? modalData.nom : '' },
                        { name: 'prenom', label: 'Last Name', value: modalData ? modalData.prenom : '' },
                        { name: 'email', label: 'Email', type: 'email', value: modalData ? modalData.email : '' },
                    ]}
                    onSubmit={modalData ? handleEditMember : handleAddMember}
                    onClose={() => setIsModalOpen(false)}
                />
            )}
        </div>
    );
};

export default Members;
