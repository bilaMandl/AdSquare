import React, { useCallback, useState } from 'react';
import { useDropzone } from 'react-dropzone';
import Cropper from 'react-easy-crop';
import getCroppedImg from '../utils/cropImage';
import './ImageUploader.css';

function ImageUploader({ onFileSelect }) {
  const [crop, setCrop] = useState({ x: 0, y: 0 });
  const [zoom, setZoom] = useState(1);
  const [rotation, setRotation] = useState(0);
  const aspect = 16 / 9; 
  const [croppingImage, setCroppingImage] = useState(null);
  const [croppedAreaPixels, setCroppedAreaPixels] = useState(null);

  const onDrop = useCallback((acceptedFiles) => {
    const imageFile = acceptedFiles[0];
    imageFile.preview = URL.createObjectURL(imageFile);
    setCroppingImage(imageFile);
  }, []);

  const { getRootProps, getInputProps, isDragActive } = useDropzone({
    onDrop,
    accept: 'image/*',
    multiple: false,
  });

  const onCropComplete = useCallback((_, croppedAreaPixels) => {
    setCroppedAreaPixels(croppedAreaPixels);
  }, []);

  const handleCropSave = async () => {
    try {
      const croppedBlob = await getCroppedImg(
        croppingImage.preview,
        croppedAreaPixels,
        rotation
      );
      const croppedFile = new File([croppedBlob], croppingImage.name, {
        type: croppingImage.type,
      });

      if (onFileSelect) onFileSelect(croppedFile);
      setCroppingImage(null);
      URL.revokeObjectURL(croppingImage.preview);
    } catch (e) {
      console.error('Crop failed', e);
    }
  };

  return (
    <>
      <div className="image-uploader">
        <div {...getRootProps()} className="upload-box">
          <input {...getInputProps()} />
          {isDragActive ? (
            <p>שחררי כאן את הקובץ...</p>
          ) : (
            <p>גרור ושחרר תמונה או לחץ לבחירה</p>
          )}
          <p className="file-note">📎 ניתן להעלות קובץ תמונה (JPG, PNG) עד 2MB</p>
        </div>
      </div>

      {croppingImage && (
        <div className="crop-modal-wrapper">
          <div className="crop-modal">
            <div className="crop-container">
              <Cropper
                image={croppingImage.preview}
                crop={crop}
                zoom={zoom}
                rotation={rotation}
                aspect={aspect}
                onCropChange={setCrop}
                onZoomChange={setZoom}
                onCropComplete={onCropComplete}
              />
            </div>

            <div className="crop-tools">
              <button onClick={() => setRotation((r) => (r + 90) % 360)}>🔄 סובב</button>

              <input
                type="range"
                min={1}
                max={3}
                step={0.1}
                value={zoom}
                onChange={(e) => setZoom(Number(e.target.value))}
              />
            </div>

            <div className="crop-actions">
              <button onClick={handleCropSave}>שמור</button>
              <button onClick={() => setCroppingImage(null)}>בטל</button>
            </div>
          </div>
        </div>
      )}
    </>
  );
}

export default ImageUploader;
