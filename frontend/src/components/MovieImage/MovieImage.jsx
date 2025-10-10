function MovieImage({ imageUrl, altText, isBig = false }) {
  let width, height;

  if (isBig) {
    width = 208;
    height = 296;
  } else {
    width = 89;
    height = 122;
  }

  return (
    <img
      src={imageUrl}
      alt={altText}
      width={width}
      height={height}
      className="relative rounded-md z-10"
    />
  );
}

export default MovieImage;
