import { useState } from "react";
import { useNavigate } from "react-router-dom";
import MovieDetails from "../components/MovieDetails/MovieDetails";
import StepProgressBar from "../components/StepProgressBar/StepProgressBar";
import { moviesData } from "@/data/movieData.js";
import { FaArrowLeft } from "react-icons/fa6";

const sessionData = {
  dates: [
    { day: "SEG", date: "03/FEV" },
    { day: "TER", date: "04/FEV" },
    { day: "QUA", date: "05/FEV" },
    { day: "QUI", date: "06/FEV" },
    { day: "SEX", date: "07/FEV" },
    { day: "SAB", date: "08/FEV" },
    { day: "DOM", date: "09/FEV" },
  ],

  sessionsByRoom: {
    "Sala 1": [
      { time: "14:00", format: "2D", lang: "LEG" },
      { time: "16:30", format: "2D", lang: "LEG" },
      { time: "19:00", format: "2D", lang: "LEG" },
      { time: "15:00", format: "2D", lang: "DUB" },
      { time: "17:30", format: "2D", lang: "DUB" },
      { time: "20:00", format: "2D", lang: "DUB" },
      { time: "21:00", format: "3D", lang: "LEG" },
      { time: "22:00", format: "3D", lang: "DUB" },
      { time: "17:00", format: "3D", lang: "LEG" },
      { time: "20:30", format: "3D", lang: "LEG" },
    ],
  },
};

function Sessions() {
  const navigate = useNavigate();

  const movie = moviesData.emCartaz[0];
  const [selectedDate, setSelectedDate] = useState(sessionData.dates[0].date);
  const [selectedLang, setSelectedLang] = useState("ALL");
  const [selectedFormat, setSelectedFormat] = useState("ALL");

  return (
    <div className="bg-cinema-darkPalette-900 text-cinema-light-800 p-4 min-h-screen">
      <div className="mx-8 my-8 space-y-8">
        <div className="cursor-pointer z-10" onClick={() => navigate("/movies")}>
          <FaArrowLeft size={32} />
        </div>

        <StepProgressBar
          currentStep={1}
          totalSteps={7}
          label="Escolha da sessão"
        />
        <MovieDetails movie={movie} />

        <div>
          <div className="flex justify-start flex-shrink-0 gap-10 overflow-x-auto pb-2 scrollbar-hide overflow-auto">
            {sessionData.dates.map((d) => (
              <button
                key={d.date}
                onClick={() => setSelectedDate(d.date)}
                className={`p-2 rounded-lg min-w-[80px] min-h-[60px] md:min-w-[120px] md:min-h-[80px] transition-colors duration-200 ${
                  selectedDate === d.date
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
          {Object.entries(sessionData.sessionsByRoom).map(
            ([sala, horarios]) => {
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
                        key={`${horario.time}-${horario.lang}-${horario.format}`}
                        onClick={() => navigate("/movies/seats")}
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
            }
          )}
        </div>
      </div>
    </div>
  );
}

export default Sessions;
