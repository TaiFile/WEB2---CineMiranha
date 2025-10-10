import MovieImage from "../MovieImage/MovieImage";

function MovieDetails({ movie }) {

  return (
    <div className="flex justify-start w-full h-48 py-8 bg-cinema-darkPalette-700">
      <MovieImage imageUrl={movie.image} altText={movie.title} />
      <div className="flex flex-col justify-start ml-4">
        <h2 className="text-md font-bold ">{movie.title}</h2>
      </div>
    </div>
  );
}

export default MovieDetails;
