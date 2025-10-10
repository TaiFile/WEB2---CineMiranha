function MovieDetails({ movie, session }) {
  return (
    <div className="flex justify-start w-full min-h-48 py-4 px-4 bg-cinema-darkPalette-700">
      <img
        src={movie.image}
        alt={movie.title}
        className="rounded-md w-22 h-32 md:w-32 md:h-48"
      />

      <div className="flex flex-col gap-3 justify-start ml-4">
        <h2 className="text-md font-bold ">{movie.title}</h2>
        <h3 className="text-sm font-semibold">{movie.duration}</h3>
        <div className="grid grid-cols-4 gap-2">
          <p className="text-sm">{session?.date}</p>
          <p className="text-sm">{session?.time}</p>
          <p className="text-sm">{session?.format}</p>
          <p className="text-sm">{session?.lang}</p>
        </div>
      </div>
    </div>
  );
}

export default MovieDetails;
