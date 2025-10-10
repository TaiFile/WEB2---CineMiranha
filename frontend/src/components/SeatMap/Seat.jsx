function Seat({ seat, onSeatClick }) {
  if (!seat) {
    return <div className="w-4 h-4 lg:w-6 lg:h-6"></div>;
  }

  const seatClass =
    seat.status === "available"
      ? "bg-cinema-light-900 hover:bg-cinema-light-700"
      : seat.status === "occupied"
      ? "bg-gray-600 cursor-not-allowed"
      : "bg-cinema-red";

  const handleClick = () => {
    if (seat.status !== "occupied" && onSeatClick) {
      onSeatClick(seat.id);
    }
  };

  return (
    <div
      key={seat.id}
      className={`w-4 h-4 lg:w-6 lg:h-6 flex justify-center items-center text-xs rounded-sm cursor-pointer ${seatClass}`}
      onClick={handleClick}
    ></div>
  );
}

export default Seat;
