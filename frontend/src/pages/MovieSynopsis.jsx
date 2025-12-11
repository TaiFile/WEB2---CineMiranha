import { useState, useEffect } from "react";
import TrailerModal from "../components/TrailerModal/TrailerModal";
import { useNavigate, useParams } from "react-router-dom";
import AgeRating from "../components/AgeRating/AgeRating";
import { FaArrowLeft } from "react-icons/fa6";
import { movieService } from "../services/movieService";

function MovieSynopsis() {
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [movie, setMovie] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const navigate = useNavigate();
  const { id } = useParams();

  useEffect(() => {
    const fetchMovie = async () => {
      try {
        setLoading(true);
        const movieData = await movieService.getMovieById(Number(id));
        setMovie(movieData);
      } catch (err) {
        setError("Erro ao carregar o filme");
        console.error(err);
      } finally {
        setLoading(false);
      }
    };

    if (id) {
      fetchMovie();
    }
  }, [id]);

  const formatDuration = (durationInSeconds) => {
    const hours = Math.floor(durationInSeconds / 3600);
    const minutes = Math.floor((durationInSeconds % 3600) / 60);
    return `${hours}h ${minutes}min`;
  };

  if (loading) {
    return (
      <div className="flex items-center justify-center w-full min-h-screen bg-[#2F3036] text-white">
        <p>Carregando...</p>
      </div>
    );
  }

  if (error || !movie) {
    return (
      <div className="flex items-center justify-center w-full min-h-screen bg-[#2F3036] text-white">
        <p>{error || "Filme não encontrado"}</p>
      </div>
    );
  }

  return (
    <div className="relative flex flex-col w-full min-h-screen bg-[#2F3036] text-white">
      <div
        className="absolute top-10 left-10 cursor-pointer z-10"
        onClick={() => navigate("/")}
      >
        <FaArrowLeft size={32} />
      </div>

      <div className="relative z-0">
        <img
          src={movie.coverUrl}
          alt={movie.title}
          className="w-full h-80 object-cover opacity-60"
        />
        <img
          src="/Images/play.svg"
          alt={`Assistir trailer de ${movie.title}`}
          className="absolute top-1/2 left-1/2 transform -translate-x-1/2 -translate-y-1/2 w-16 h-16 cursor-pointer hover:scale-110 transition-transform"
          onClick={() => setIsModalOpen(true)}
        />
      </div>

      <div className="flex items-center justify-center">
        <section className="flex flex-col items-center -mt-16 lg:-mt-40 py-6 px-6 gap-6 max-w-7xl overflow-x-hidden">
          <div className="w-full flex flex-col items-center gap-4 lg:flex-row lg:justify-start lg:ml-32">
            <img
              src={movie.coverUrl}
              alt={movie.title}
              className="relative object-cover rounded-md w-[208px] h-[296px] lg:w-[260px] lg:h-[370px]"
            />

            <div className="h-full flex flex-col lg:self-end gap-4">
              <h1 className="text-3xl font-bold">{movie.title}</h1>

              <div className="flex flex-col gap-4 max-w-5xl text-center bg-cinema-darkPalette-700 rounded-md p-4">
                <div className="flex justify-between items-center gap-4">
                  <p>{movie.genres?.join(", ")}</p>
                  <p>{formatDuration(movie.durationInSeconds)}</p>
                </div>

                <div className="flex justify-between items-center gap-4">
                  <div className="flex flex-col items-start text-left">
                    <p>
                      <b>Diretor:</b> Walter Salles
                    </p>
                    <p>
                      <b>Elenco:</b> Fernanda Montenegro, Fernanda Torres,
                      Selton Mello
                    </p>
                  </div>
                  <AgeRating rating={movie.ageRating} className={"self-end"} />
                </div>
              </div>
            </div>
          </div>

          <div className="text-justify">
            <p>
              <b>Sinopse:</b>
            </p>
            <p>{movie.synopsis}</p>
          </div>

          <div className="w-full max-w-48">
            <button
              className="bg-cinema-light-900 transition-all duration-200 hover:scale-105  text-cinema-darkPalette-900 font-bold py-2 px-4 w-full rounded-md"
              onClick={() => navigate(`/movies/${id}/session`)}
            >
              Adquirir Ingresso
            </button>
          </div>
        </section>
      </div>

      <TrailerModal
        isOpen={isModalOpen}
        onClose={() => setIsModalOpen(false)}
        video={movie.trailerUrl}
        title={movie.title}
      />
    </div>
  );
}

export default MovieSynopsis;
