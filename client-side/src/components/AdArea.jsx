import { useEffect, useState } from "react";
import axios from "axios";
import AdUploadForm from "./AdUploadForm";
import { useAuth } from "../context/AuthContext";

function AdArea({ areaNumber, selectedArea, onRequestLogin }) {
  const [imageUrl, setImageUrl] = useState(null);
  const [error, setError] = useState(null);
  const { customerId } = useAuth();
  const [showForm, setShowForm] = useState(false);

  useEffect(() => {
    if (!areaNumber) return;
    fetchAdvertisement();
  }, [areaNumber]);

  useEffect(() => {
    if (customerId && selectedArea === areaNumber) {
      setShowForm(true);
    } else {
      setShowForm(false);
    }
  }, [customerId, selectedArea, areaNumber]);

  const fetchAdvertisement = async () => {
    try {
      const res = await axios.get(`http://localhost:8080/adv/${areaNumber}`);
      if (res.data) {
        setImageUrl(`http://localhost:8080/image/${res.data}`);
      } else {
        setImageUrl("");
      }
      setError(null);
    } catch (err) {
      setError("Failed to load advertisement image");
      setImageUrl("");
    }
  };

  if (error) return <p>{error}</p>;
  if (imageUrl === null) return <p>Loading advertisement...</p>;

  return (
    <div>
      {imageUrl === "" ? (
        <div>
          {!showForm && <div className="button-wrap">
            <button id="Add-Adv" onClick={() => onRequestLogin(areaNumber)}>
              <span>פרסם כאן</span>
            </button>
            <div className="button-shadow"></div>
          </div>}
        </div>
      ) : (
        <img
          src={imageUrl}
          alt={`Advertisement for area ${areaNumber}`}
          style={{ width: "70%", height: "70%", objectFit: "cover", display: "block" }}
        />
      )}
      <div>
        {showForm && (
          <AdUploadForm
            customerId={customerId}
            areaNumber={areaNumber}
            onUploadSuccess={() => {
              fetchAdvertisement();
              setShowForm(false);
            }}
          />
        )}
      </div>
    </div>
  );
}

export default AdArea;
