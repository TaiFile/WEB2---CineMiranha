// OrderSidebar.jsx
import Button from "../Button/Button";
import MovieDetails from "../MovieDetails/MovieDetails";

const OrderSidebar = ({
  movie,
  session,
  selectedSeats,
  seats,
  onRemoveSeat,
}) => {
  const seatPrice = 25.0;
  const selectedSeatsList = seats.filter((seat) =>
    selectedSeats.includes(seat.id)
  );
  const totalItems = selectedSeatsList.length;
  const totalTaxa = 0.0;
  const totalPrice = totalItems * seatPrice;

  return (
    <aside className="block w-96 bg-cinema-darkPalette-700 border-l border-cinema-darkPalette-600 overflow-y-auto rounded-md">
      <div className="sticky top-0">
        {/* Header */}
        <div className="border-b border-cinema-darkPalette-600 pt-4">
          <div className="flex items-center justify-between px-4 mb-2">
            <h2 className="text-xl font-semibold text-white">
              Resumo do pedido
            </h2>
          </div>

          {/* Movie Details Card */}
          <MovieDetails movie={movie} session={session} />
        </div>

        {/* Session Info */}
        <div className="px-6 py-2 border-b border-cinema-darkPalette-600">
          <div className="grid grid-cols-3 gap-4 text-center text-sm">
            <div>
              <p className="text-white font-semibold">SALA 2</p>
            </div>
            <div>
              <p className="text-white font-semibold">SEX 10/10</p>
            </div>
            <div>
              <p className="text-white font-semibold">18:30</p>
            </div>
          </div>
        </div>

        {/* Selected Seats */}
        <div className="p-4 border-b border-cinema-darkPalette-600">
          <h3 className="text-white font-semibold mb-4">Assentos</h3>
          {selectedSeatsList.length > 0 ? (
            <div className="space-y-3">
              {selectedSeatsList.map((seat) => (
                <div
                  key={seat.id}
                  className="flex items-center justify-between bg-cinema-darkPalette-800 rounded-lg p-3"
                >
                  <div className="flex items-center gap-3">
                    <div className="w-10 h-10 bg-cinema-light-500 rounded-md flex items-center justify-center text-black font-bold">
                      {seat.id}
                    </div>
                    <div>
                      <p className="text-white font-medium">{seat.id}</p>
                      <p className="text-gray-400 text-sm">
                        R$ {seatPrice.toFixed(2)}
                      </p>
                    </div>
                  </div>
                  <button
                    onClick={() => onRemoveSeat(seat.id)}
                    className="text-gray-400 hover:text-red-500 transition-colors"
                  >
                    <Trash2 size={16} />
                  </button>
                </div>
              ))}
            </div>
          ) : (
            <p className="text-gray-400 text-center py-8">
              Nenhum assento selecionado
            </p>
          )}
        </div>

        {/* Price Summary */}
        <div className="p-4">
          <div className="space-y-2">
            <div className="flex justify-between text-white">
              <span>Itens</span>
              <span>{totalItems}</span>
            </div>
            <div className="flex justify-between text-white">
              <span>Total taxa</span>
              <span>R$ {totalTaxa.toFixed(2)}</span>
            </div>
            <div className="h-px bg-cinema-darkPalette-600" />
            <div className="flex justify-between text-white text-lg font-bold">
              <span>Total</span>
              <span className="text-red-500">R$ {totalPrice.toFixed(2)}</span>
            </div>
          </div>
        </div>

        {/* Checkout Button */}
        <div className="w-full px-6 py-2">
          <Button
            text="Escolher assentos"
            onClickHandler={() => {
              alert("Funicionalidade em desenvolvimento");
            }}
          />
        </div>
      </div>
    </aside>
  );
};

export default OrderSidebar;
