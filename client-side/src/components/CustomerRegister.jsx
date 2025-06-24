import React, { useState } from "react";
import axiosClient from "../api/axiosClient";
import { useAuth } from "../context/AuthContext";

function CustomerRegister({ onSuccess }) {
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [message, setMessage] = useState("");
    const { setCustomerId } = useAuth();

    const handleSubmit = async (e) => {
        e.preventDefault();
        if (!isValidEmail(email)) {
            setMessage("כתובת המייל אינה תקינה.");
            return;
        }
        if (!isValidPassword(password)) {
            setMessage("הסיסמה חייבת להכיל לפחות 8 תווים, אות גדולה, אות קטנה, מספר ותו מיוחד.");
            return;
        }
        try {
            const res = await axiosClient.post("/cust/add", { email, password });
            if (res.data && !isNaN(res.data)) {
                const r = await axiosClient.post("/cust/", { email });
                setCustomerId(r.data.id);
                if (onSuccess) onSuccess();
            } else {
                setMessage("המייל של ך כבר קיים. נסה מייל אחר.");
            }
        } catch (err) {
            setMessage("Error: " + err.message);
        }
    };
    const emailRegex = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
    const isValidEmail = (email) => emailRegex.test(email);

    const passwordRegex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[\W_]).{8,}$/;
    const isValidPassword = (password) => passwordRegex.test(password);


    return (
        <form onSubmit={handleSubmit}>
            <input type="email" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,}$" required placeholder="Email" value={email} onChange={e => setEmail(e.target.value)} />
            <input minLength="8" placeholder="Password" type="password" value={password} onChange={e => setPassword(e.target.value)} />
            <button type="submit">Register</button>
            <p>{message}</p>
        </form>
    );
}

export default CustomerRegister;