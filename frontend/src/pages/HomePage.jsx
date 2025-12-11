// src/pages/HomePage.jsx
import { useMemo } from "react";
import MovieCarousel from "../components/MovieCarousel/MovieCarousel";
import { useMovies } from "../hooks/useMovies";
import { useGeolocation } from "../hooks/useGeolocation";
import { useNearestTheater } from "../hooks/useNearestTheater";
import { MovieStatus } from "../types/enums";
import { formatDistance } from "../utils/distanceUtils";
import { formatShortAddress } from "../utils/addressUtils";

function HomePage() {
  // Geolocalização do usuário
  const { location, address, error: geoError, loading: geoLoading } = useGeolocation();
  
  // Theater mais próximo
  const { 
    nearestTheater, 
    allTheaters,
    loading: theaterLoading, 
    error: theaterError 
  } = useNearestTheater(location?.latitude, location?.longitude);
  
  // Filmes
  const { movies, loading: moviesLoading, error: moviesError } = useMovies();

  // Loading state combinado
  const isLoading = geoLoading || theaterLoading || moviesLoading;

  if (isLoading) {
    return (
      <div className="flex flex-col items-center justify-center min-h-screen bg-[#282C31]">
        <div className="text-white text-xl mb-2">
          {geoLoading && "📍 Obtendo sua localização..."}
          {theaterLoading && "🎬 Buscando cinemas próximos..."}
          {moviesLoading && "🎞️ Carregando filmes..."}
        </div>
        <div className="mt-4">
          <div className="animate-spin rounded-full h-12 w-12 border-b-2 border-white"></div>
        </div>
      </div>
    );
  }

  // Error state
  if (moviesError) {
    return (
      <div className="flex items-center justify-center min-h-screen bg-[#282C31]">
        <div className="text-red-500 text-xl">❌ Erro: {moviesError}</div>
      </div>
    );
  }

  // Filtra por status
  const filmesEmCartaz = movies.filter(
    (movie) => movie.status === MovieStatus.NOW_PLAYING
  );

  const filmesEmBreve = movies.filter(
    (movie) => movie.status === MovieStatus.COMING_SOON
  );

  return (
    <div className="flex flex-col min-h-screen bg-[#282C31] pb-8 pt-6">
      {/* Card de Localização e Theater Mais Próximo */}
      {nearestTheater && nearestTheater.address && (
        <div className="bg-gradient-to-r from-gray-800 to-gray-900 rounded-lg mx-4 mb-6 p-4 shadow-lg">
          <div className="flex items-start justify-between">
            {/* Sua Localização */}
            <div className="flex-1">
              <div className="flex items-center mb-1">
                <span className="text-2xl mr-2">📍</span>
                <p className="text-gray-400 text-xs font-medium">Sua localização</p>
              </div>
              <p className="text-white text-sm font-semibold">
                {address?.city || 'Carregando...'}, {address?.state || ''}
              </p>
            </div>

            {/* Divisor */}
            <div className="w-px bg-gray-700 mx-4 self-stretch"></div>

            {/* Cinema Mais Próximo */}
            <div className="flex-1">
              <div className="flex items-center mb-1">
                <span className="text-2xl mr-2">🎬</span>
                <p className="text-gray-400 text-xs font-medium">Cinema mais próximo</p>
              </div>
              <p className="text-white text-sm font-semibold mb-1">
                {nearestTheater.name}
              </p>
              {nearestTheater.distance && (
                <p className="text-blue-400 text-xs font-medium">
                  📏 {formatDistance(nearestTheater.distance)} de você
                </p>
              )}
              <p className="text-gray-500 text-xs mt-1">
                {nearestTheater.address.neighborhood}, {nearestTheater.address.city}
              </p>
            </div>
          </div>
        </div>
      )}

      {/* Avisos */}
      {theaterError && (
        <div className="bg-red-900 bg-opacity-30 text-red-400 text-sm px-4 py-2 mx-4 mb-4 rounded">
          ❌ {theaterError}
        </div>
      )}

      {!nearestTheater && !theaterLoading && (
        <div className="bg-yellow-900 bg-opacity-30 text-yellow-400 text-sm px-4 py-2 mx-4 mb-4 rounded">
          ⚠️ Não foi possível encontrar cinemas próximos
        </div>
      )}

      {/* Filmes em cartaz */}
      {filmesEmCartaz.length > 0 && (
        <MovieCarousel 
          title={`🎥 Filmes em cartaz (${filmesEmCartaz.length})`} 
          movies={filmesEmCartaz} 
        />
      )}

      {/* Filmes em breve */}
      {filmesEmBreve.length > 0 && (
        <MovieCarousel 
          title={`🔜 Em breve (${filmesEmBreve.length})`} 
          movies={filmesEmBreve} 
        />
      )}

      {/* Mensagem quando não há filmes */}
      {filmesEmCartaz.length === 0 && filmesEmBreve.length === 0 && (
        <div className="text-center mt-10 px-4">
          <p className="text-white text-lg mb-2">🎬</p>
          <p className="text-white text-lg">
            Nenhum filme disponível no momento
          </p>
          {nearestTheater && (
            <p className="text-gray-400 text-sm mt-2">
              no {nearestTheater.name}
            </p>
          )}
        </div>
      )}

      {/* Debug: Lista todos os theaters (só em dev) */}
      {process.env.NODE_ENV === 'development' && allTheaters.length > 0 && (
        <div className="mx-4 mt-8 bg-gray-900 rounded-lg p-4">
          <h3 className="text-white text-sm font-bold mb-3">
            🔍 Debug: Todos os cinemas ({allTheaters.length})
          </h3>
          <div className="space-y-2">
            {allTheaters.map((theater, index) => (
              <div 
                key={theater.id} 
                className={`p-3 rounded text-xs ${
                  index === 0 ? 'bg-blue-900' : 'bg-gray-800'
                }`}
              >
                <p className="text-white font-semibold">{theater.name}</p>
                {theater.address && (
                  <>
                    <p className="text-gray-400 mt-1">
                      {formatShortAddress(theater.address)}
                    </p>
                    <p className="text-gray-500 text-xs">
                      CEP: {theater.address.zipCode}
                    </p>
                  </>
                )}
                {theater.distance && (
                  <p className={`mt-1 font-medium ${
                    index === 0 ? 'text-blue-300' : 'text-gray-300'
                  }`}>
                    📏 {formatDistance(theater.distance)}
                    {index === 0 && ' ⭐ (mais próximo)'}
                  </p>
                )}
              </div>
            ))}
          </div>
        </div>
      )}
    </div>
  );
}

export default HomePage;
