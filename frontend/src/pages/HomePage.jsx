import { useState, useEffect } from "react";
import MovieCarousel from "../components/MovieCarousel/MovieCarousel";
import { useMovies } from "../hooks/useMovies";
import { MovieStatus } from "../types/enums";

function HomePage() {
  const [location, setLocation] = useState(null);
  const [geoError, setGeoError] = useState(null);

  const { movies, loading, error } = useMovies();

  useEffect(() => {
    const storedLocation = localStorage.getItem("userLocation");
    if (storedLocation) {
      setLocation(JSON.parse(storedLocation));
    } else {
      if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(
          (position) => {
            const newLocation = {
              latitude: position.coords.latitude,
              longitude: position.coords.longitude,
            };
            setLocation(newLocation);
            localStorage.setItem("userLocation", JSON.stringify(newLocation));
          },
          (err) => {
            setGeoError(err.message);
          }
        );
      } else {
        setGeoError("Geolocation is not supported by this browser.");
      }
    }
  }, []);

  if (loading) {
    return (
      <div className="flex items-center justify-center min-h-screen bg-[#282C31]">
        <div className="text-white text-xl">Carregando filmes...</div>
      </div>
    );
  }

  if (error) {
    return (
      <div className="flex items-center justify-center min-h-screen bg-[#282C31]">
        <div className="text-red-500 text-xl">Erro ao carregar filmes: {error}</div>
      </div>
    );
  }

  // Filtra filmes por status
  const filmesEmCartaz = movies.filter(
    (movie) => movie.status === MovieStatus.NOW_SHOWING
  );
  
  const filmesEmBreve = movies.filter(
    (movie) => movie.status === MovieStatus.UPCOMING
  );

  return (
    <div className="flex flex-col min-h-screen bg-[#282C31] pb-8 pt-6">
      {filmesEmCartaz.length > 0 && (
        <MovieCarousel title="Filmes em cartaz:" movies={filmesEmCartaz} />
      )}
      
      {filmesEmBreve.length > 0 && (
        <MovieCarousel title="Em breve:" movies={filmesEmBreve} />
      )}

      {filmesEmCartaz.length === 0 && filmesEmBreve.length === 0 && (
        <div className="text-white text-center mt-10">
          Nenhum filme disponível no momento.
        </div>
      )}
    </div>
  );
}

export default HomePage;
