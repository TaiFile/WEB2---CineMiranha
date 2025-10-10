function AgeRating({ rating, className }) {
  const colorMap = {
    L: "bg-green-600",
    10: "bg-blue-600",
    12: "bg-yellow-600",
    14: "bg-orange-600",
    16: "bg-red-600",
    18: "bg-red-600",
  };

  if (!rating) return null;

  return (
    <div
      className={`
             text-white text-xs font-bold px-2 py-1 rounded
            ${colorMap[rating] || "bg-gray-600"}
          ${className}`}
    >
      {rating}
    </div>
  );
}

export default AgeRating;
