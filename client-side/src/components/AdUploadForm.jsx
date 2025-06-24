import { useEffect, useState } from "react";
import axios from "axios";
import { useAuth } from "../context/AuthContext";
import axiosClient from "../api/axiosClient";
import "./AdUploadForm.css";
import AdPackagesList from "./AdPackagesList";
import ImageUploader from "./ImageUploader";


function AdUploadForm({ areaNumber, onUploadSuccess }) {
    const { customerId } = useAuth();
    const clicks = 0;
    const [adPackageId, setAdPackageId] = useState("");
    const [file, setFile] = useState(null);
    const [message, setMessage] = useState("");
    const [packages, setPackages] = useState([]);

    useEffect(() => {
        axiosClient.get("/pack/")
            .then(res => setPackages(res.data))
            .catch(() => setPackages([]));
    }, []);

    const handleFileChange = (file) => {
        setFile(file);
    };

    const handleSubmit = async (e) => {
        e.preventDefault();

        if (!file || !adPackageId) {
            setMessage("יש לבחור קובץ וחבילת פרסום.");
            return;
        }

        const formData = new FormData();
        formData.append("file", file);
        formData.append("clicks", clicks);
        formData.append("areaNumber", areaNumber);
        formData.append("adPackageId", adPackageId);
        formData.append("customerId", customerId);

        try {
            const res = await axios.post("http://localhost:8080/adv/", formData, {
                headers: { "Content-Type": "multipart/form-data" },
            });
            if (res.data === true) {
                setMessage("הפרסומת נוצרה בהצלחה!");
                if (onUploadSuccess) onUploadSuccess();
            } else {
                setMessage("העלאת הפרסומת נכשלה.");
            }
        } catch (error) {
            setMessage("שגיאה: " + error.message);
        }
    };

    return (<>
        <AdPackagesList onPackagesLoaded={setPackages} />
        <form className="ad-form" onSubmit={handleSubmit}>
            <h3 className="ad-form-title">פרסום פרסומת</h3>
            <select
                className="ad-input"
                value={adPackageId}
                onChange={(e) => setAdPackageId(e.target.value)}
                required
            >
                <option value="">בחר חבילת פרסום</option>
                {packages.map((pack) => (
                    <option key={pack.id} value={pack.id}>
                        {`קליקים: ${pack.numberOfClicks}, ימים: ${pack.numberOfDays}`}
                    </option>
                ))}
            </select>
            <div className="file-upload">
                <ImageUploader onFileSelect={handleFileChange}></ImageUploader>
                {file && <span className="file-name">{file.name}</span>}
            </div>
            <button className="ad-button" type="submit">
                העלאת הפרסומת
            </button>

            {message && <p className="ad-message">{message}</p>}
        </form>
    </>);
}

export default AdUploadForm;
