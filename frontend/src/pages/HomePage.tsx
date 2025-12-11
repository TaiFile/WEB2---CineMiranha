import React from "react";
import MovieCarousel from "../components/MovieCarousel/MovieCarousel";
import { useMovies } from "../hooks/useMovies";
import { MovieStatus } from "../types/enums";
import { Movie } from "../types/Movie";

const HomePage: React.FC = () => {
  const { movies, loading, error } = useMovies();

  if (loading) {
    return <div className="text-white">Carregando...</div>;
  }

  if (error) {
    return <div className="text-red-500">{error}</div>;
  }

  const filmesEmCartaz: Movie[] = movies.filter(
    (movie) => movie.status === MovieStatus.NOW_PLAYING
  );

  const filmesEmBreve: Movie[] = movies.filter(
    (movie) => movie.status === MovieStatus.COMING_SOON
  );


  return (
    <div className="flex flex-col min-h-screen bg-[#282C31] pb-8 pt-6">
      {filmesEmCartaz.length > 0 && (
        <MovieCarousel title="Filmes em cartaz:" movies={filmesEmCartaz} />
      )}
      
      {filmesEmBreve.length > 0 && (
        <MovieCarousel title="Em breve:" movies={filmesEmBreve} />
      )}
    </div>
  );
}


export default HomePage;
