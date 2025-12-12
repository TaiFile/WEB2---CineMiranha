import { useState, useEffect, useMemo } from "react";
import Button from "../components/Button/Button";
import MovieDetails from "../components/MovieDetails/MovieDetails";
import StepProgressBar from "../components/StepProgressBar/StepProgressBar";
import { useNavigate, useParams } from "react-router-dom";
import SeatMap from "../components/SeatMap/SeatMap";
import Seat from "../components/SeatMap/Seat";
import OrderSidebar from "../components/OrderSideBar/OrderSideBar";
import { FaArrowLeft } from "react-icons/fa6";
import { sessionService } from "../services/api/sessionService";
import { movieService } from "../services/api/movieService";

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

// Helper para formatar duração
function formatDuration(seconds) {
  const hours = Math.floor(seconds / 3600);
  const minutes = Math.floor((seconds % 3600) / 60);
  return `${hours}h ${minutes}min`;
}

function Seats() {
  const navigate = useNavigate();
  const { id } = useParams();
  const sessionId = Number(id);

  const [sessionData, setSessionData] = useState(null);
  const [movie, setMovie] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const [selectedSeatIds, setSelectedSeatIds] = useState(new Set());

  useEffect(() => {
    async function fetchData() {
      try {
        setLoading(true);
        const session = await sessionService.getSessionById(sessionId);
        setSessionData(session);

        // Buscar dados do filme
        const movieData = await movieService.getMovieById(session.movieId);
        setMovie(movieData);
      } catch (err) {
        console.error("Erro ao carregar dados:", err);
        setError("Erro ao carregar sessão. Tente novamente.");
      } finally {
        setLoading(false);
      }
    }

    if (sessionId) {
      fetchData();
    }
  }, [sessionId]);

  // Criar mapa de assentos ocupados (seatId -> true)
  const occupiedSeatIds = useMemo(() => {
    if (!sessionData?.tickets) return new Set();
    return new Set(sessionData.tickets.map((ticket) => ticket.seatId));
  }, [sessionData]);

  // Converter rows do backend para o formato esperado pelo SeatMap
  const { seatGrid, allSeats } = useMemo(() => {
    if (!sessionData?.room?.rows) {
      return { seatGrid: [], allSeats: [] };
    }

    const rows = sessionData.room.rows;

    // Encontrar o número máximo de assentos em uma fileira
    const maxSeats = Math.max(
      ...rows.map((row) => row.seats?.length || 0),
      0
    );

    const grid = [];
    const seats = [];

    rows.forEach((row, rowIndex) => {
      const rowSeats = row.seats || [];

      const gridRow = [];

      rowSeats.forEach((seat, colIndex) => {
        const isOccupied = occupiedSeatIds.has(seat.id);
        const isSelected = selectedSeatIds.has(seat.id);

        const seatData = {
          id: seat.id,
          row: rowIndex + 1,
          column: colIndex + 1,
          rowLetter: row.letter,
          seatNumber: seat.number,
          seatType: seat.seatType,
          status: isOccupied
            ? "occupied"
            : isSelected
            ? "selected"
            : "available",
          displayId: `${row.letter}${seat.number}`,
        };

        gridRow.push(seatData);
        seats.push(seatData);
      });

      // Preencher com nulls se necessário para manter o grid uniforme
      while (gridRow.length < maxSeats) {
        gridRow.push(null);
      }

      grid.push(gridRow);
    });

    return { seatGrid: grid, allSeats: seats };
  }, [sessionData, occupiedSeatIds, selectedSeatIds]);

  const handleSeatClick = (seatId) => {
    setSelectedSeatIds((prev) => {
      const newSet = new Set(prev);
      if (newSet.has(seatId)) {
        newSet.delete(seatId);
      } else {
        newSet.add(seatId);
      }
      return newSet;
    });
  };

  const selectedSeats = allSeats.filter((seat) => selectedSeatIds.has(seat.id));

  if (loading) {
    return (
      <div className="bg-cinema-darkPalette-800 text-white min-h-screen flex items-center justify-center">
        <div className="text-xl">Carregando assentos...</div>
      </div>
    );
  }

  if (error) {
    return (
      <div className="bg-cinema-darkPalette-800 text-white min-h-screen flex items-center justify-center">
        <div className="text-xl text-red-500">{error}</div>
      </div>
    );
  }

  // Preparar dados do filme e sessão para os componentes
  const movieForDetails = movie
    ? {
        image: movie.coverUrl,
        title: movie.title,
        duration: formatDuration(movie.durationInSeconds),
      }
    : null;

  const sessionForDetails = sessionData
    ? {
        date: new Date(sessionData.date)
          .toLocaleDateString("pt-BR", {
            day: "2-digit",
            month: "short",
          })
          .toUpperCase(),
        time: new Date(sessionData.date).toLocaleTimeString("pt-BR", {
          hour: "2-digit",
          minute: "2-digit",
        }),
        format: formatDisplay[sessionData.format] || sessionData.format,
        lang: subtitleDisplay[sessionData.subtitle] || sessionData.subtitle,
      }
    : null;

  return (
    <div className="flex justify-center bg-cinema-darkPalette-800 min-h-screen min-w-screen text-white">
      <div className="w-full sm:container sm:mx-8">
        <div
          className="ml-6 mt-10 cursor-pointer z-10"
          onClick={() => navigate(-1)}
        >
          <FaArrowLeft size={32} />
        </div>

        <div className="max-w-4xl mx-auto mt-7">
          <StepProgressBar
            currentStep={2}
            totalSteps={7}
            label="Escolha dos assentos"
          />
        </div>

        <div className="lg:hidden">
          {movieForDetails && (
            <MovieDetails movie={movieForDetails} session={sessionForDetails} />
          )}
        </div>

        <div className="flex flex-col lg:flex-row lg:gap-8 lg:items-start lg:justify-center my-8 mx-4">
          <div className="flex-1 lg:max-w-4xl">
            <SeatMap seatGrid={seatGrid} onSeatClick={handleSeatClick} />

            <div className="mt-4">
              <p>Legenda</p>
              <div className="flex flex-col lg:flex-row gap-1 lg:gap-4 mt-2 p-2 justify-start bg-cinema-darkPalette-700 rounded-md">
                <div className="flex items-center">
                  <Seat seat={{ status: "available" }} />
                  <p className="inline-block ml-2">Assentos livres</p>
                </div>
                <div className="flex items-center">
                  <Seat seat={{ status: "occupied" }} />
                  <p className="inline-block ml-2">Assentos ocupados</p>
                </div>
                <div className="flex items-center">
                  <Seat seat={{ status: "selected" }} />
                  <p className="inline-block ml-2">Assentos selecionados</p>
                </div>
              </div>
            </div>
          </div>

          {/* Mobile Bottom Bar */}
          <div className="fixed bottom-0 left-0 right-0 flex justify-between items-center lg:hidden h-20 px-10 border-y border-black bg-cinema-darkPalette-800">
            <div className="bg-cinema-light-500 text-black flex justify-center items-center rounded-full w-8 h-8 mr-4">
              {selectedSeats.length ? selectedSeats.length : 0}
            </div>
            <div className="w-48">
              <Button
                text="Escolher Ingressos"
                onClickHandler={() => alert("Em desenvolvimento")}
              />
            </div>
          </div>

          <div className="hidden lg:block w-96 flex-shrink-0">
            <OrderSidebar
              movie={movieForDetails}
              session={sessionForDetails}
              selectedSeats={selectedSeats}
              onRemoveSeat={handleSeatClick}
            />
          </div>
        </div>
      </div>
    </div>
  );
}

export default Seats;
