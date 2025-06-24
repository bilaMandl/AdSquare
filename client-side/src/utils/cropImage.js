export default function getCroppedImg(imageSrc, pixelCrop, rotation = 0) {
  const createImage = (url) =>
    new Promise((resolve, reject) => {
      const image = new Image();
      image.addEventListener('load', () => resolve(image));
      image.addEventListener('error', (error) => reject(error));
      image.setAttribute('crossOrigin', 'anonymous'); // כדי למנוע בעיות CORS
      image.src = url;
    });

  function getRadianAngle(degreeValue) {
    return (degreeValue * Math.PI) / 180;
  }

  return new Promise(async (resolve, reject) => {
    const image = await createImage(imageSrc);
    const canvas = document.createElement('canvas');
    const ctx = canvas.getContext('2d');

    const rotRad = getRadianAngle(rotation);

    const sin = Math.abs(Math.sin(rotRad));
    const cos = Math.abs(Math.cos(rotRad));
    const width = image.width;
    const height = image.height;
    const boundingBoxWidth = width * cos + height * sin;
    const boundingBoxHeight = width * sin + height * cos;

    canvas.width = pixelCrop.width;
    canvas.height = pixelCrop.height;

    const tempCanvas = document.createElement('canvas');
    const tempCtx = tempCanvas.getContext('2d');
    tempCanvas.width = boundingBoxWidth;
    tempCanvas.height = boundingBoxHeight;

    tempCtx.translate(boundingBoxWidth / 2, boundingBoxHeight / 2);
    tempCtx.rotate(rotRad);
    tempCtx.drawImage(image, -width / 2, -height / 2);

    ctx.drawImage(
      tempCanvas,
      pixelCrop.x,
      pixelCrop.y,
      pixelCrop.width,
      pixelCrop.height,
      0,
      0,
      pixelCrop.width,
      pixelCrop.height
    );

    canvas.toBlob((blob) => {
      if (!blob) {
        reject(new Error('Canvas is empty'));
        return;
      }
      resolve(blob);
    }, 'image/jpeg');
  });
}
