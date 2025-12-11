import React, { useState, useEffect } from "react";
import MovieCarousel from "../components/MovieCarousel/MovieCarousel";
import TheaterDropdown from "../components/TheaterDropdown/TheaterDropdown";
import TheaterMap from "../components/TheaterMap/TheaterMap";
import Modal from "../components/Modal/Modal";
import { theaterService } from "../services/theaterService";
import { movieService } from "../services/movieService";
import { MovieStatus } from "../types/enums";
import { Movie } from "../types/Movie";
import { Theater } from "../types/Theater";
import { FaMapMarkerAlt } from "react-icons/fa";

const HomePage: React.FC = () => {
  const [selectedTheater, setSelectedTheater] = useState<Theater | null>(null);
  const [movies, setMovies] = useState<Movie[]>([]);
  const [comingSoonMovies, setComingSoonMovies] = useState<Movie[]>([]);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState<string | null>(null);
  const [isMapModalOpen, setIsMapModalOpen] = useState(false);

  // Fetch coming soon movies (global, not tied to a theater)
  useEffect(() => {
    const fetchComingSoon = async () => {
      try {
        const data = await movieService.getAllMovies(MovieStatus.COMING_SOON);
        setComingSoonMovies(data);
      } catch (err) {
        console.error("Erro ao carregar filmes em breve", err);
      }
    };

    fetchComingSoon();
  }, []);

  // Fetch movies by selected theater
  useEffect(() => {
    const fetchMovies = async () => {
      if (!selectedTheater) return;

      try {
        setLoading(true);
        const data = await theaterService.getMoviesByTheaterId(selectedTheater.id);
        setMovies(data);
        setError(null);
      } catch (err) {
        setError("Erro ao carregar filmes");
        console.error(err);
      } finally {
        setLoading(false);
      }
    };

    fetchMovies();
  }, [selectedTheater]);

  const filmesEmCartaz: Movie[] = movies.filter(
    (movie) => movie.status === MovieStatus.NOW_PLAYING
  );

  return (
    <div className="flex flex-col min-h-screen bg-[#282C31] pb-8 pt-6">
      <div className="px-6 mb-6 flex items-center gap-4">
        <div className="flex-1">
          <TheaterDropdown
            selectedTheater={selectedTheater}
            onTheaterSelect={setSelectedTheater}
          />
        </div>
        {selectedTheater && (
          <button
            onClick={() => setIsMapModalOpen(true)}
            className="flex items-center gap-2 text-red-500 hover:text-red-400 transition-colors cursor-pointer"
          >
            <FaMapMarkerAlt />
            <span className="underline">Ver localização</span>
          </button>
        )}
      </div>

      {loading ? (
        <div className="flex items-center justify-center py-12">
          <div className="w-8 h-8 border-4 border-gray-600 border-t-white rounded-full animate-spin"></div>
        </div>
      ) : error ? (
        <div className="text-red-500 px-6">{error}</div>
      ) : !selectedTheater ? (
        <div className="text-gray-400 px-6">Selecione um cinema para ver os filmes disponíveis</div>
      ) : (
        <>
          {filmesEmCartaz.length > 0 && (
            <MovieCarousel title="Filmes em cartaz:" movies={filmesEmCartaz} />
          )}
          
          {filmesEmCartaz.length === 0 && (
            <div className="text-gray-400 px-6">Nenhum filme em cartaz neste cinema</div>
          )}
        </>
      )}

      {comingSoonMovies.length > 0 && (
        <MovieCarousel title="Em breve:" movies={comingSoonMovies} />
      )}

      {selectedTheater && (
        <Modal
          isOpen={isMapModalOpen}
          onClose={() => setIsMapModalOpen(false)}
          title={selectedTheater.name}
        >
          <TheaterMap theater={selectedTheater} className="h-80 w-full" />
        </Modal>
      )}
    </div>
  );
}


export default HomePage;
