import { useState } from "react";
import axiosClient from "../api/axiosClient";
import { useAuth } from "../context/AuthContext";

function CustomerLogin({ onSuccess }) {
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [message, setMessage] = useState("");
    const { setCustomerId } = useAuth();

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            const res = await axiosClient.post("/cust/login", { email, password });
            if (res.data && !isNaN(res.data)) {
                const r = await axiosClient.post("/cust/", { email });
                setCustomerId(r.data.id);
                if (onSuccess) onSuccess();
            } else {
                setMessage("המייל שלך או הסיסמה שגויים. נסה שוב.");
            }
        } catch (err) {
            setMessage("Error: " + err.message);
        }
    };


    return (
        <form onSubmit={handleSubmit}>
            <input type="text" placeholder="Email" value={email} onChange={e => setEmail(e.target.value)} />
            <input placeholder="Password" type="password" value={password} onChange={e => setPassword(e.target.value)} />
            <button type="submit">Login</button>
            <p>{message}</p>
        </form>
    );
}

export default CustomerLogin;