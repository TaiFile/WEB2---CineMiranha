import { useState } from "react";
import { moviesData } from "@/data/movieData.js";
import TrailerModal from "../components/TrailerModal/TrailerModal";
import { useNavigate } from "react-router-dom";
import MovieImage from "../components/MovieImage/MovieImage";

function MovieSynopsis() {
  const [isModalOpen, setIsModalOpen] = useState(false);
  const navigate = useNavigate();

  let movie = moviesData.emCartaz[0];
  movie.categories = ["História", "Drama", "Biografia"];

  return (
    <div className="flex flex-col min-h-screen bg-[#2F3036] text-white">
      <div className="relative z-0">
        <img
          src={movie.thumbnail}
          alt={movie.title}
          className="w-full h-80 object-cover opacity-60"
        />
        <img
          src="public/images/play.svg"
          alt={`Assistir trailer de ${movie.title}`}
          className="absolute top-1/2 left-1/2 transform -translate-x-1/2 -translate-y-1/2 w-16 h-16 cursor-pointer hover:scale-110 transition-transform"
          onClick={() => setIsModalOpen(true)}
        />
      </div>

      <div className="flex items-center justify-center">
        <section className="md:container flex flex-col items-center -mt-16 lg:-mt-40 py-6 px-6 gap-6">
          <div className="w-full flex flex-col items-center gap-4 lg:flex-row lg:justify-start lg:ml-32">
            <MovieImage imageUrl={movie.image} altText={movie.title} isBig />

            <div className="h-full flex flex-col lg:self-end gap-4">
              <h1 className="text-3xl font-bold">{movie.title}</h1>

              <div className="flex flex-col gap-4 max-w-5xl text-center bg-cinema-darkPalette-700 rounded-md p-4">
                <div className="flex justify-between items-center gap-4">
                  <p>{movie.categories.join(", ")}</p>
                  <p>{movie.duration}</p>
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
                  <img
                    src="public/images/14.png"
                    alt={movie.ageRating}
                    width={24}
                    height={24}
                  />
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
              onClick={() => navigate("/movies/session")}
            >
              Adquirir Ingresso
            </button>
          </div>
        </section>
      </div>

      <TrailerModal
        isOpen={isModalOpen}
        onClose={() => setIsModalOpen(false)}
        video={movie.video}
        title={movie.title}
      />
    </div>
  );
}

export default MovieSynopsis;
