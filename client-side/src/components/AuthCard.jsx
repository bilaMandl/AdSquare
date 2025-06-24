import { useState } from "react";
import CustomerLogin from "./CustomerLogin";
import CustomerRegister from "./CustomerRegister";
import "./AuthCard.css";

function AuthCard({ onSuccess }) {
    const [activeTab, setActiveTab] = useState("login");

    return (
        <>
            <div className="auth-box">
                <div className="auth-box-inner">
                    <div className="tab-header">
                        <button className={`tab ${activeTab === "login" ? "active" : ""}`} onClick={() => setActiveTab("login")}>
                            התחברות
                        </button>
                        <button className={`tab ${activeTab === "register" ? "active" : ""}`} onClick={() => setActiveTab("register")}>
                            הרשמה
                        </button>
                    </div>

                    <div className={`form-slider ${activeTab}`}>
                        <div className="form-slide">
                            <CustomerLogin onSuccess={onSuccess} />
                        </div>
                        <div className="form-slide">
                            <CustomerRegister onSuccess={onSuccess} />
                        </div>
                    </div>
                </div>
            </div>
        </>
    );
}

export default AuthCard;
