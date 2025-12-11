import React, { useState, useEffect } from "react";
import MovieCarousel from "../components/MovieCarousel/MovieCarousel";
import TheaterDropdown from "../components/TheaterDropdown/TheaterDropdown";
import TheaterMap from "../components/TheaterMap/TheaterMap";
import Modal from "../components/Modal/Modal";
import { theaterService } from "../services/api/theaterService";
import { movieService } from "../services/api/movieService";
import { MovieStatus } from "../types/enums";
import { Movie } from "../types/Movie";
import { Theater } from "../types/Theater";
import { FaMapMarkerAlt, FaExclamationTriangle } from "react-icons/fa";

const HomePage: React.FC = () => {
  const [selectedTheater, setSelectedTheater] = useState<Theater | null>(null);
  const [movies, setMovies] = useState<Movie[]>([]);
  const [comingSoonMovies, setComingSoonMovies] = useState<Movie[]>([]);
  
  // Separate loading states for better UX
  const [loadingMovies, setLoadingMovies] = useState(false);
  const [loadingComingSoon, setLoadingComingSoon] = useState(true);
  
  // Separate error states
  const [errorMovies, setErrorMovies] = useState<string | null>(null);
  const [errorComingSoon, setErrorComingSoon] = useState<string | null>(null);
  
  const [isMapModalOpen, setIsMapModalOpen] = useState(false);

  // Fetch coming soon movies (global, not tied to a theater)
  useEffect(() => {
    const fetchComingSoon = async () => {
      try {
        setLoadingComingSoon(true);
        setErrorComingSoon(null);
        const data = await movieService.getAllMovies(MovieStatus.COMING_SOON);
        setComingSoonMovies(data);
      } catch (err) {
        console.error("Erro ao carregar filmes em breve", err);
        setErrorComingSoon("Não foi possível carregar os filmes em breve");
      } finally {
        setLoadingComingSoon(false);
      }
    };

    fetchComingSoon();
  }, []);

  // Fetch movies by selected theater
  useEffect(() => {
    const fetchMovies = async () => {
      if (!selectedTheater) {
        setMovies([]);
        setErrorMovies(null);
        return;
      }

      try {
        setLoadingMovies(true);
        setErrorMovies(null);
        const data = await theaterService.getMoviesByTheaterId(selectedTheater.id);
        setMovies(data);
      } catch (err) {
        const errorMessage = "Não foi possível carregar os filmes deste cinema";
        setErrorMovies(errorMessage);
        console.error(err);
        setMovies([]);
      } finally {
        setLoadingMovies(false);
      }
    };

    fetchMovies();
  }, [selectedTheater]);

  const filmesEmCartaz: Movie[] = movies.filter(
    (movie) => movie.status === MovieStatus.NOW_PLAYING
  );

  // Reusable loading spinner component
  const LoadingSpinner = () => (
    <div className="flex items-center justify-center py-12">
      <div className="w-8 h-8 border-4 border-gray-600 border-t-white rounded-full animate-spin"></div>
    </div>
  );

  // Reusable error message component
  const ErrorMessage: React.FC<{ message: string; onRetry?: () => void }> = ({ 
    message, 
    onRetry 
  }) => (
    <div className="flex flex-col items-center justify-center py-8 px-6">
      <FaExclamationTriangle className="text-red-500 text-4xl mb-3" />
      <p className="text-red-400 text-center mb-4">{message}</p>
      {onRetry && (
        <button
          onClick={onRetry}
          className="px-4 py-2 bg-red-500 hover:bg-red-600 text-white rounded transition-colors"
        >
          Tentar novamente
        </button>
      )}
    </div>
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
            <FaMapMarkerAlt className="text-xl sm:text-base" />
            <span className="hidden sm:inline underline">Ver localização</span>
          </button>
        )}

      </div>

      {/* Theater movies section */}
      {loadingMovies ? (
        <LoadingSpinner />
      ) : errorMovies ? (
        <ErrorMessage 
          message={errorMovies}
          onRetry={() => selectedTheater && setSelectedTheater({...selectedTheater})}
        />
      ) : !selectedTheater ? (
        <div className="text-gray-400 px-6 py-8 text-center">
          Selecione um cinema para ver os filmes disponíveis
        </div>
      ) : (
        <>
          {filmesEmCartaz.length > 0 ? (
            <MovieCarousel title="Filmes em cartaz:" movies={filmesEmCartaz} />
          ) : (
            <div className="text-gray-400 px-6 py-4">
              Nenhum filme em cartaz neste cinema
            </div>
          )}
        </>
      )}

      {/* Coming soon section */}
      <div className="mt-6">
        {loadingComingSoon ? (
          <LoadingSpinner />
        ) : errorComingSoon ? (
          <ErrorMessage 
            message={errorComingSoon}
            onRetry={() => window.location.reload()}
          />
        ) : comingSoonMovies.length > 0 ? (
          <MovieCarousel title="Em breve:" movies={comingSoonMovies} />
        ) : null}
      </div>

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
};

export default HomePage;
