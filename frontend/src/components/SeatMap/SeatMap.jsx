import { useState } from "react";
import { FaPlus, FaMinus, FaUndo } from "react-icons/fa";
import Seat from "./Seat";

function SeatMap({ seatGrid = [], onSeatClick }) {
  const [zoom, setZoom] = useState(1);

  const MIN_ZOOM = 0.5;
  const MAX_ZOOM = 3.0;
  const ZOOM_STEP = 0.5;

  const handleZoomIn = () => {
    setZoom((prev) => Math.min(prev + ZOOM_STEP, MAX_ZOOM));
  };

  const handleZoomOut = () => {
    setZoom((prev) => Math.max(prev - ZOOM_STEP, MIN_ZOOM));
  };

  const handleResetZoom = () => {
    setZoom(1);
  };

  return (
    <div className="w-full min-h-[400px] lg:min-h-[500px] pt-6 lg:pt-12 border border-cinema-light-900 flex flex-col justify-center items-center overflow-hidden relative">
      {/* Zoom Controls */}
      <div className="absolute top-3 right-3 flex flex-col gap-2 z-10">
        <button
          onClick={handleZoomIn}
          disabled={zoom >= MAX_ZOOM}
          className="bg-cinema-darkPalette-700 hover:bg-cinema-darkPalette-600 disabled:opacity-50 disabled:cursor-not-allowed p-3 rounded-lg transition-colors"
          title="Zoom in"
        >
          <FaPlus className="w-5 h-5" />
        </button>
        <button
          onClick={handleZoomOut}
          disabled={zoom <= MIN_ZOOM}
          className="bg-cinema-darkPalette-700 hover:bg-cinema-darkPalette-600 disabled:opacity-50 disabled:cursor-not-allowed p-3 rounded-lg transition-colors"
          title="Zoom out"
        >
          <FaMinus className="w-5 h-5" />
        </button>
        <button
          onClick={handleResetZoom}
          disabled={zoom === 1}
          className="bg-cinema-darkPalette-700 hover:bg-cinema-darkPalette-600 disabled:opacity-50 disabled:cursor-not-allowed p-3 rounded-lg transition-colors"
          title="Reset zoom"
        >
          <FaUndo className="w-5 h-5" />
        </button>
      </div>

      <div className="absolute top-3 left-3 bg-cinema-darkPalette-700 px-3 py-1.5 rounded-lg text-sm text-gray-300 z-10">
        {Math.round(zoom * 100)}%
      </div>

      {/* Scrollable Container */}
      <div className="w-full flex-grow flex items-center justify-center overflow-auto thin-scrollbar">
        <div
          style={{
            transform: `scale(${zoom})`,
            transformOrigin: "center center",
            transition: "transform 0.15s ease-out",
          }}
        >
          <table className="border-spacing-2 lg:border-spacing-3 border-separate">
            <tbody>
              {seatGrid.map((row, rowIndex) => (
                <tr key={rowIndex}>
                  {row.map((seat, colIndex) => (
                    <td key={colIndex}>
                      <Seat seat={seat} onSeatClick={onSeatClick} />
                    </td>
                  ))}
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      </div>

      <div className="bg-cinema-darkPalette-700 w-4/5 lg:w-3/4 h-6 lg:h-8 flex justify-center items-center text-sm font-semibold">
        TELA
      </div>
    </div>
  );
}

export default SeatMap;
