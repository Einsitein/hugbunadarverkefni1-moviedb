import React, { useEffect, useState } from 'react';

export default function LogoutPage() {
    useEffect(() => {
        localStorage.removeItem('token');
        window.location.href = '/';
    }, []);
    
    return (
        <div>
        </div>
    );
}