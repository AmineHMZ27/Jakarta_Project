import React, { useState, useEffect } from 'react';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faUserPlus, faBookOpen, faClipboardList } from '@fortawesome/free-solid-svg-icons';

const Dashboard = () => {
    const [memberCount, setMemberCount] = useState(0);
    const [bookCount, setBookCount] = useState(0);
    const [loanCount, setLoanCount] = useState(0);

    useEffect(() => {
        fetchCounts();
    }, []);

    const fetchCounts = async () => {
        try {
            const [membersResponse, booksResponse, loansResponse] = await Promise.all([
                fetch('http://localhost:8080/api/client'),
                fetch('http://localhost:8080/api/livre'),
                fetch('http://localhost:8080/api/pret')
            ]);

            if (!membersResponse.ok || !booksResponse.ok || !loansResponse.ok) {
                throw new Error('Failed to fetch data');
            }

            const members = await membersResponse.json();
            const books = await booksResponse.json();
            const loans = await loansResponse.json();

            setMemberCount(members.length);
            setBookCount(books.length);
            setLoanCount(loans.length);
        } catch (error) {
            console.error('Error fetching counts:', error);
        }
    };

    return (
        <div className="dashboard">
            <h1>Dashboard</h1>
            <div className="dashboard-cards">
                <div className="card">
                    <h2><FontAwesomeIcon icon={faUserPlus} /> Members</h2>
                    <p>{memberCount}</p>
                </div>
                <div className="card">
                    <h2><FontAwesomeIcon icon={faBookOpen} /> Books</h2>
                    <p>{bookCount}</p>
                </div>
                <div className="card">
                    <h2><FontAwesomeIcon icon={faClipboardList} /> Loans</h2>
                    <p>{loanCount}</p>
                </div>
            </div>
        </div>
    );
};

export default Dashboard;
