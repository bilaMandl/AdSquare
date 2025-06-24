import { useState } from "react";
import AdArea from "./AdArea";
import "./AdCircleLayout.css";
import AuthCard from "./AuthCard";
import { AnimatePresence, motion } from "framer-motion";


function AdCircleLayout() {
    const [showAuthModal, setShowAuthModal] = useState(false);
    const [selectedArea, setSelectedArea] = useState(null);

    const handleRequestLogin = (areaNumber) => {
        setSelectedArea(areaNumber);
        setShowAuthModal(true);
    };

    const handleAuthSuccess = () => {
        setShowAuthModal(false);
    };

    return (
        <div className="circle-layout">
            <div className="ad ad-top-left">
                <AdArea areaNumber={1} selectedArea={selectedArea} onRequestLogin={() => handleRequestLogin(1)} />
            </div>
            <div className="ad ad-top-right">
                <AdArea areaNumber={2} selectedArea={selectedArea} onRequestLogin={() => handleRequestLogin(2)} />
            </div>
            <div className="ad ad-bottom-left">
                <AdArea areaNumber={3} selectedArea={selectedArea} onRequestLogin={() => handleRequestLogin(3)} />
            </div>
            <div className="ad ad-bottom-right">
                <AdArea areaNumber={4} selectedArea={selectedArea} onRequestLogin={() => handleRequestLogin(4)} />
            </div>

            <div className="logo-circle">
                <motion.img
                    id="cir"
                    src="/logo.png"
                    alt="Logo"
                    initial={{ scale: 0, opacity: 0, rotate: -90 }}
                    animate={{ scale: 1, opacity: 1, rotate: 0 }}
                    transition={{ duration: 1, ease: "easeOut" }}
                />
            </div>
            <AnimatePresence>
                {showAuthModal && (
                    <div className="auth-modal" onClick={() => setShowAuthModal(false)}>
                        <motion.div
                            className="auth-modal-content"
                            onClick={(e) => e.stopPropagation()}
                            initial={{ opacity: 0, scale: 0.8, y: -30 }}
                            animate={{ opacity: 1, scale: 1, y: 0 }}
                            exit={{ opacity: 0, scale: 0.8, y: -30 }}
                            transition={{ duration: 0.5, ease: "easeOut" }}
                            >
                            <AuthCard onSuccess={handleAuthSuccess} />
                    </motion.div>
                    </div>)
}
            </AnimatePresence >
        </div >
    );
}

export default AdCircleLayout;
