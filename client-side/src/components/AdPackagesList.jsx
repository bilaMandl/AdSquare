import { useEffect } from "react";
import axiosClient from "../api/axiosClient";

function AdPackagesList({ onPackagesLoaded }) {
  useEffect(() => {
    axiosClient.get("/pack/")
      .then(res => {
        const data = Array.isArray(res.data) ? res.data : [];
        onPackagesLoaded(data);
      })
      .catch(() => onPackagesLoaded([]));
  }, [onPackagesLoaded]);

  return null; 
}

export default AdPackagesList;
