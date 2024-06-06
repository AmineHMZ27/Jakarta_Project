import React from 'react';
import { BrowserRouter as Router, Route, Routes, Navigate } from 'react-router-dom';
import Sidebar from './components/Sidebar';
import Dashboard from './components/Dashboard';
import Members from './components/Members';
import Books from './components/Books';
import Categories from './components/Categories';
import Loans from './components/Loans';
import Authors from './components/Authors';
import './App.css';

const App = () => {
    return (
        <Router>
            <div className="app">
                <Sidebar />
                <div className="content">
                    <Routes>
                        <Route path="/" element={<Navigate to="/dashboard" />} />
                        <Route path="/dashboard" element={<Dashboard />} />
                        <Route path="/members" element={<Members />} />
                        <Route path="/books" element={<Books />} />
                        <Route path="/categories" element={<Categories />} />
                        <Route path="/loans" element={<Loans />} />
                        <Route path="/authors" element={<Authors />} />
                    </Routes>
                </div>
            </div>
        </Router>
    );
};

export default App;
