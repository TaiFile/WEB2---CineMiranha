import { useState, useEffect, useMemo } from "react";
import { useNavigate, useParams } from "react-router-dom";
import MovieDetails from "../components/MovieDetails/MovieDetails";
import StepProgressBar from "../components/StepProgressBar/StepProgressBar";
import { FaArrowLeft } from "react-icons/fa6";
import { movieService } from "../services/api/movieService";
import { sessionService } from "../services/api/sessionService";

// Helpers para converter enums do backend para exibição
const formatDisplay = {
  TWO_D: "2D",
  THREE_D: "3D",
};

const subtitleDisplay = {
  SUBTITLED: "LEG",
  DUBBED: "DUB",
  ORIGINAL: "ORI",
};

// Helper para gerar próximos 7 dias
function getNextSevenDays() {
  const days = ["DOM", "SEG", "TER", "QUA", "QUI", "SEX", "SAB"];
  const result = [];
  const today = new Date();

  for (let i = 0; i < 7; i++) {
    const date = new Date(today);
    date.setDate(today.getDate() + i);
    const dayName = days[date.getDay()];
    const formattedDate = date
      .toLocaleDateString("pt-BR", {
        day: "2-digit",
        month: "short",
      })
      .toUpperCase()
      .replace(".", "");

    result.push({
      day: dayName,
      date: formattedDate,
      fullDate: date.toISOString().split("T")[0], // YYYY-MM-DD para comparação
    });
  }
  return result;
}

// Helper para formatar duração
function formatDuration(seconds) {
  const hours = Math.floor(seconds / 3600);
  const minutes = Math.floor((seconds % 3600) / 60);
  return `${hours}h ${minutes}min`;
}

function Sessions() {
  const navigate = useNavigate();
  const { id } = useParams();
  const movieId = id ? Number(id) : null;

  const [movie, setMovie] = useState(null);
  const [sessions, setSessions] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  const dates = useMemo(() => getNextSevenDays(), []);
  const [selectedDate, setSelectedDate] = useState(dates[0]?.fullDate);
  const [selectedLang, setSelectedLang] = useState("ALL");
  const [selectedFormat, setSelectedFormat] = useState("ALL");

  useEffect(() => {
    async function fetchData() {
      try {
        setLoading(true);
        const [movieData, sessionsData] = await Promise.all([
          movieService.getMovieById(movieId),
          sessionService.getSessionsByMovieId(movieId),
        ]);
        setMovie(movieData);
        setSessions(sessionsData);
      } catch (err) {
        console.error("Erro ao carregar dados:", err);
        setError("Erro ao carregar sessões. Tente novamente.");
      } finally {
        setLoading(false);
      }
    }

    if (movieId && !isNaN(movieId)) {
      fetchData();
    } else {
      setLoading(false);
      setError("ID do filme não encontrado.");
    }
  }, [movieId]);

  // Agrupar sessões por sala e filtrar por data selecionada
  const sessionsByRoom = useMemo(() => {
    const grouped = {};

    sessions.forEach((session) => {
      const sessionDate = session.date.split("T")[0]; // Pegar apenas a data YYYY-MM-DD

      if (sessionDate !== selectedDate) return;

      const roomName = `Sala ${session.roomId}`; // Usando roomId por enquanto

      if (!grouped[roomName]) {
        grouped[roomName] = [];
      }

      const time = new Date(session.date).toLocaleTimeString("pt-BR", {
        hour: "2-digit",
        minute: "2-digit",
      });

      grouped[roomName].push({
        id: session.id,
        time,
        format: formatDisplay[session.format] || session.format,
        lang: subtitleDisplay[session.subtitle] || session.subtitle,
        rawFormat: session.format,
        rawSubtitle: session.subtitle,
      });
    });

    return grouped;
  }, [sessions, selectedDate]);

  if (loading) {
    return (
      <div className="bg-cinema-darkPalette-900 text-cinema-light-800 p-4 min-h-screen flex items-center justify-center">
        <div className="text-xl">Carregando sessões...</div>
      </div>
    );
  }

  if (error) {
    return (
      <div className="bg-cinema-darkPalette-900 text-cinema-light-800 p-4 min-h-screen flex items-center justify-center">
        <div className="text-xl text-red-500">{error}</div>
      </div>
    );
  }

  // Preparar dados do filme para o componente MovieDetails
  const movieForDetails = movie
    ? {
        image: movie.coverUrl?.startsWith("http")
          ? movie.coverUrl
          : movie.coverUrl,
        title: movie.title,
        duration: formatDuration(movie.durationInSeconds),
      }
    : null;

  return (
    <div className="bg-cinema-darkPalette-900 text-cinema-light-800 p-4 min-h-screen">
      <div className="mx-8 my-8 space-y-8">
        <div className="cursor-pointer z-10" onClick={() => navigate("/")}>
          <FaArrowLeft size={32} />
        </div>

        <StepProgressBar
          currentStep={1}
          totalSteps={7}
          label="Escolha da sessão"
        />
        {movieForDetails && <MovieDetails movie={movieForDetails} />}

        <div>
          <div className="flex justify-start flex-shrink-0 gap-10 overflow-x-auto pb-2 scrollbar-hide overflow-auto">
            {dates.map((d) => (
              <button
                key={d.fullDate}
                onClick={() => setSelectedDate(d.fullDate)}
                className={`p-2 rounded-lg min-w-[80px] min-h-[60px] md:min-w-[120px] md:min-h-[80px] transition-colors duration-200 ${
                  selectedDate === d.fullDate
                    ? "bg-cinema-light-600 text-black font-bold"
                    : "bg-cinema-darkPalette-800 text-cinema-light-900 hover:bg-cinema-darkPalette-700"
                }`}
              >
                <div className="text-sm lg:text-2xl">{d.day}</div>
                <div className="font-semibold lg:text-2xl">{d.date}</div>
              </button>
            ))}
          </div>
        </div>

        {/* Lista idioma e formato */}
        <div className="flex justify-between gap-1">
          <select
            value={selectedLang}
            onChange={(e) => setSelectedLang(e.target.value)}
            className="flex-shrink-0 bg-cinema-light-600 text-black rounded-lg p-2 min-w-[121px] lg:w-[250px] appearance-none lg:text-lg text-center font-semibold"
          >
            <option value="ALL">Todos os idiomas</option>
            <option value="LEG">Legendado</option>
            <option value="DUB">Dublado</option>
          </select>

          <select
            value={selectedFormat}
            onChange={(e) => setSelectedFormat(e.target.value)}
            className="bg-cinema-light-600 text-black rounded-lg p-2 min-w-[121px] lg:w-[250px] appearance-none lg:text-lg text-center font-semibold"
          >
            <option value="ALL">Todos os formatos</option>
            <option value="2D">2D</option>
            <option value="3D">3D</option>
          </select>
        </div>

        {/* Seção de horários */}
        <div className="space-y-8">
          {Object.keys(sessionsByRoom).length === 0 ? (
            <div className="text-center text-cinema-light-600 py-8">
              <p className="text-xl">
                Nenhuma sessão disponível para esta data.
              </p>
            </div>
          ) : (
            Object.entries(sessionsByRoom).map(([sala, horarios]) => {
              const filteredHorarios = horarios.filter((h) => {
                const langMatch =
                  selectedLang === "ALL" || h.lang === selectedLang;
                const formatMatch =
                  selectedFormat === "ALL" || h.format === selectedFormat;
                return langMatch && formatMatch;
              });

              if (filteredHorarios.length === 0) return null;

              return (
                <div key={sala}>
                  <div className="flex items-center gap-3 mb-4">
                    <div className="w-1.5 h-6 bg-cinema-red rounded-full"></div>
                    <h3 className="text-4xl font-bold text-cinema-light-700">
                      {sala}
                    </h3>
                  </div>

                  <div className="grid grid-cols-3 gap-4">
                    {filteredHorarios.map((horario) => (
                      <button
                        key={horario.id}
                        onClick={() =>
                          navigate(`/sessions/${horario.id}/seats`)
                        }
                        className="overflow-hidden justify-self-center flex flex-col gap-2 w-[90%] h-full p-[10%] md:p-3 
                                  bg-cinema-darkPalette-500 border border-cinema-darkPalette-700 
                                    rounded-lg hover:border-cinema-red transition-colors duration-200"
                      >
                        <div className="flex items-center justify-center bg-cinema-light-800 text-cinema-darkPalette-900 font-bold lg:text-lg text-xs h-full py-1 2xl:py-2 rounded-md">
                          {horario.time}
                        </div>
                        <div className="flex gap-2 justify-center items-center h-full">
                          <span className="flex items-center justify-center bg-cinema-light-800 text-cinema-darkPalette-900 2xl:text-2xl text-xs w-full h-full px-1 2xl:px-12 py-0.5 2xl:py-2 rounded-sm">
                            {horario.format}
                          </span>
                          <span className="flex items-center justify-center bg-cinema-light-800 text-cinema-darkPalette-900 2xl:text-2xl text-xs w-full h-full px-1 2xl:px-12 py-0.5 2xl:py-2 rounded-sm">
                            {horario.lang}
                          </span>
                        </div>
                      </button>
                    ))}
                  </div>
                </div>
              );
            })
          )}
        </div>
      </div>
    </div>
  );
}

export default Sessions;
