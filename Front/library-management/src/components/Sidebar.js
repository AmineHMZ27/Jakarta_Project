import React from 'react';
import { Link } from 'react-router-dom';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faTachometerAlt, faUser, faBook, faThList, faHandHolding, faPenFancy } from '@fortawesome/free-solid-svg-icons';

const Sidebar = () => {
    return (
        <div className="sidebar">
            <h2>Library Dashboard</h2>
            <ul>
                <li><Link to="/dashboard"><FontAwesomeIcon icon={faTachometerAlt} /> Dashboard</Link></li>
                <li><Link to="/members"><FontAwesomeIcon icon={faUser} /> Members</Link></li>
                <li><Link to="/books"><FontAwesomeIcon icon={faBook} /> Books</Link></li>
                <li><Link to="/loans"><FontAwesomeIcon icon={faHandHolding} /> Loans</Link></li>
                <li><Link to="/categories"><FontAwesomeIcon icon={faThList} /> Categories</Link></li>
                <li><Link to="/authors"><FontAwesomeIcon icon={faPenFancy} /> Authors</Link></li>
            </ul>
        </div>
    );
};

export default Sidebar;
