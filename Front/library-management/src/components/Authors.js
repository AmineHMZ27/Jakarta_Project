import React, { useState, useEffect } from 'react';

const Authors = () => {
    const [authors, setAuthors] = useState([]);

    useEffect(() => {
        fetchAuthors();
    }, []);

    const fetchAuthors = async () => {
        try {
            const response = await fetch('http://localhost:8080/api/auteur');
            if (!response.ok) {
                throw new Error('Failed to fetch authors');
            }
            const data = await response.json();
            console.log('Fetched authors:', data);
            setAuthors(data);
        } catch (error) {
            console.error('Error fetching authors:', error);
        }
    };

    return (
        <div className="authors">
            <h1>Authors</h1>
            <table className="table">
                <thead>
                    <tr>
                        <th>Name</th>
                        <th>Books</th>
                    </tr>
                </thead>
                <tbody>
                    {authors.map(author => (
                        <tr key={author.id}>
                            <td>{author.nom}</td>
                            <td>
                                <ul>
                                    {author.livres.map(livre => (
                                        <li key={livre.id}>{livre.titre}</li>
                                    ))}
                                </ul>
                            </td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
    );
};

export default Authors;
