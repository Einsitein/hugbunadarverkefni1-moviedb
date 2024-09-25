import React, { useState, useEffect } from "react";
import { jwtDecode } from "jwt-decode";

export default function HomePage() {
    const [email, setEmail] = useState(null);
    
    useEffect(() => {
        const token = localStorage.getItem("token");
        if (token) {
            console.log(token);
            const decoded = jwtDecode(token);
            console.log(decoded);
            const email = decoded.sub;
            setEmail(email);
        }
    }, []);
    
    return (
        <div>
        <h1>Welcome, {email ? email : "Stranger"}!</h1>
        </div>
    );
}