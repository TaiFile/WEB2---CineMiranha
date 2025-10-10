function MovieImage({ imageUrl, altText, isBig = false }) {
  return (
    <img
      src={imageUrl}
      alt={altText}
      className={`relative rounded-md z-10 ${
        isBig
          ? "w-[208px] h-[296px] lg:w-[260px] lg:h-[370px]"
          : "w-[89px] h-[122px] lg:w-[120px] lg:h-[170px]"
      }`}
    />
  );
}

export default MovieImage;
