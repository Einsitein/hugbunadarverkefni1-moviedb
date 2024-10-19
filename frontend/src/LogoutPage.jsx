import React, { useEffect } from 'react';

export default function LogoutPage() {
    useEffect(() => {
        localStorage.removeItem('token');
        globalThis.location.href = '/';
    }, []);
    
    return (
        <div>
        </div>
    );
}
