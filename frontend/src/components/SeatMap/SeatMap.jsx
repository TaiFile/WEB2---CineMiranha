import Seat from "./Seat";

function SeatMap({ seatGrid = [], onSeatClick }) {
  return (
    <div className="w-full min-h-[400px] lg:min-h-[500px] pt-6 lg:pt-12 border border-cinema-light-900 flex flex-col justify-center items-center overflow-auto thin-scrollbar">
      <div className="w-full flex-grow flex items-center justify-center">
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

      <div className="bg-cinema-darkPalette-700 w-4/5 lg:w-3/4 h-6 lg:h-8 flex justify-center items-center text-sm font-semibold">
        TELA
      </div>
    </div>
  );
}

export default SeatMap;
