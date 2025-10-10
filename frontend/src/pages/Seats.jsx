import { useState } from "react";
import Button from "../components/Button/Button";
import MovieDetails from "../components/MovieDetails/MovieDetails";
import StepProgressBar from "../components/StepProgressBar/StepProgressBar";
import { moviesData } from "@/data/movieData.js";
import { useNavigate } from "react-router-dom";
import SeatMap from "../components/SeatMap/SeatMap";
import Seat from "../components/SeatMap/Seat";
import OrderSidebar from "../components/OrderSideBar/OrderSideBar";
import { FaArrowLeft } from "react-icons/fa6";

function Seats() {
  const navigate = useNavigate();

  const movie = moviesData.emCartaz[0];
  const session = { date: "03/FEV", time: "16:00", format: "2D", lang: "LEG" };

  const [seats, setSeats] = useState([
    { id: "E1", row: 1, column: 2, status: "available" },
    { id: "E2", row: 1, column: 3, status: "available" },
    { id: "E3", row: 1, column: 4, status: "available" },
    { id: "E4", row: 1, column: 5, status: "available" },
    { id: "E5", row: 1, column: 6, status: "available" },
    { id: "E6", row: 1, column: 7, status: "available" },
    { id: "E8", row: 1, column: 9, status: "occupied" },
    { id: "E9", row: 1, column: 10, status: "occupied" },
    { id: "E10", row: 1, column: 11, status: "occupied" },
    { id: "E11", row: 1, column: 12, status: "occupied" },
    { id: "E12", row: 1, column: 13, status: "available" },
    { id: "E13", row: 1, column: 14, status: "available" },

    { id: "D1", row: 2, column: 2, status: "available" },
    { id: "D2", row: 2, column: 3, status: "available" },
    { id: "D4", row: 2, column: 5, status: "available" },
    { id: "D5", row: 2, column: 6, status: "available" },
    { id: "D6", row: 2, column: 7, status: "available" },
    { id: "D8", row: 2, column: 9, status: "available" },
    { id: "D9", row: 2, column: 10, status: "occupied" },
    { id: "D10", row: 2, column: 11, status: "occupied" },
    { id: "D12", row: 2, column: 13, status: "available" },
    { id: "D13", row: 2, column: 14, status: "available" },

    { id: "C1", row: 3, column: 2, status: "occupied" },
    { id: "C2", row: 3, column: 3, status: "occupied" },
    { id: "C4", row: 3, column: 5, status: "occupied" },
    { id: "C5", row: 3, column: 6, status: "occupied" },
    { id: "C6", row: 3, column: 7, status: "selected" },
    { id: "C7", row: 3, column: 8, status: "occupied" },
    { id: "C8", row: 3, column: 9, status: "occupied" },
    { id: "C9", row: 3, column: 10, status: "occupied" },
    { id: "C10", row: 3, column: 11, status: "occupied" },
    { id: "C12", row: 3, column: 13, status: "available" },
    { id: "C13", row: 3, column: 14, status: "available" },

    { id: "B1", row: 4, column: 2, status: "available" },
    { id: "B2", row: 4, column: 3, status: "available" },
    { id: "B4", row: 4, column: 5, status: "occupied" },
    { id: "B5", row: 4, column: 6, status: "occupied" },
    { id: "B6", row: 4, column: 7, status: "occupied" },
    { id: "B7", row: 4, column: 8, status: "occupied" },
    { id: "B8", row: 4, column: 9, status: "occupied" },
    { id: "B9", row: 4, column: 10, status: "occupied" },
    { id: "B12", row: 4, column: 13, status: "available" },
    { id: "B13", row: 4, column: 14, status: "available" },

    { id: "A3", row: 5, column: 4, status: "available" },
    { id: "A4", row: 5, column: 5, status: "available" },
    { id: "A5", row: 5, column: 6, status: "occupied" },
    { id: "A6", row: 5, column: 7, status: "occupied" },
    { id: "A7", row: 5, column: 8, status: "occupied" },
    { id: "A8", row: 5, column: 9, status: "available" },
    { id: "A9", row: 5, column: 10, status: "available" },
  ]);

  const handleSeatClick = (seatId) => {
    setSeats(
      seats.map((seat) => {
        if (seat.id === seatId && seat.status !== "occupied") {
          return {
            ...seat,
            status: seat.status === "selected" ? "available" : "selected",
          };
        }
        return seat;
      })
    );
  };

  const numRows = 5;
  const numCols = 14;
  const seatGrid = Array(numRows)
    .fill(null)
    .map(() => Array(numCols).fill(null));
  seats.forEach((seat) => {
    if (
      seat.row >= 1 &&
      seat.row <= numRows &&
      seat.column >= 1 &&
      seat.column <= numCols
    ) {
      seatGrid[seat.row - 1][seat.column - 1] = seat;
    }
  });

  const selectedSeats = seats.filter((seat) => seat.status === "selected");

  return (
    <div className="flex justify-center bg-cinema-darkPalette-800 min-h-screen min-w-screen text-white">
      <div className="w-full sm:container sm:mx-8">
        <div
          className="mt-10 cursor-pointer z-10"
          onClick={() => navigate("/movies/session")}
        >
          <FaArrowLeft size={32} />
        </div>

        <div className="max-w-4xl mx-auto mt-7">
          <StepProgressBar
            currentStep={2}
            totalSteps={7}
            label="Escolha da sessão"
          />
        </div>

        <div className="lg:hidden">
          <MovieDetails movie={movie} session={session} />
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
                onClickHandler={() => navigate("/ingressos")}
              />
            </div>
          </div>

          <div className="hidden lg:block w-96 flex-shrink-0">
            <OrderSidebar
              movie={movie}
              session={session}
              selectedSeats={selectedSeats}
              seats={seats}
              onRemoveSeat={handleSeatClick}
            />
          </div>
        </div>
      </div>
    </div>
  );
}

export default Seats;
