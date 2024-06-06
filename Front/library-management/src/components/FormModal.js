import React, { useState, useEffect } from 'react';

const FormModal = ({ title, fields, onSubmit, onClose }) => {
    const [formData, setFormData] = useState(() => {
        const initialData = {};
        fields.forEach(field => {
            initialData[field.name] = field.value || '';
        });
        return initialData;
    });

    useEffect(() => {
        const initialData = {};
        fields.forEach(field => {
            initialData[field.name] = field.value || '';
        });
        setFormData(initialData);
    }, [fields]);

    const handleChange = (e) => {
        setFormData({
            ...formData,
            [e.target.name]: e.target.value,
        });
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        onSubmit(formData);
    };

    return (
        <div className="form-modal">
            <h2>{title}</h2>
            <form onSubmit={handleSubmit}>
                {fields.map(field => (
                    <div key={field.name}>
                        <label htmlFor={field.name}>{field.label}</label>
                        {field.type === 'select' ? (
                            <select
                                name={field.name}
                                value={formData[field.name]}
                                onChange={handleChange}
                                required
                            >
                                <option value="">Select {field.label}</option>
                                {field.options.map(option => (
                                    <option key={option.value} value={option.value}>
                                        {option.label}
                                    </option>
                                ))}
                            </select>
                        ) : (
                            <input
                                type={field.type || 'text'}
                                name={field.name}
                                value={formData[field.name]}
                                onChange={handleChange}
                                required
                            />
                        )}
                    </div>
                ))}
                <button type="submit">Submit</button>
                <button type="button" onClick={onClose}>Cancel</button>
            </form>
        </div>
    );
};

export default FormModal;
