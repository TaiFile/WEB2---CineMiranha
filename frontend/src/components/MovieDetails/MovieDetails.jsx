function MovieDetails({ movie }) {
  return (
    <div className="flex justify-start w-full min-h-48 py-8 bg-cinema-darkPalette-700">
      <img
        src={movie.image}
        alt={movie.title}
        className="rounded-md w-22 h-32 md:w-32 md:h-48"
      />

      <div className="flex flex-col justify-start ml-4">
        <h2 className="text-md font-bold ">{movie.title}</h2>
      </div>
    </div>
  );
}

export default MovieDetails;
