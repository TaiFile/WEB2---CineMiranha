// src/components/AgeRating/AgeRating.jsx

function AgeRating({ rating, className = "" }) {
  // Mapeia enum do backend para display
  const displayMap = {
    GENERAL_AUDIENCE: "L",
    TEN_YEARS: "10",
    TWELVE_YEARS: "12",
    FOURTEEN_YEARS: "14",
    SIXTEEN_YEARS: "16",
    EIGHTEEN_YEARS: "18"
  };

  // Mapeia para cores
  const colorMap = {
    GENERAL_AUDIENCE: "bg-green-600",
    TEN_YEARS: "bg-blue-600",
    TWELVE_YEARS: "bg-yellow-600",
    FOURTEEN_YEARS: "bg-orange-600",
    SIXTEEN_YEARS: "bg-red-600",
    EIGHTEEN_YEARS: "bg-red-800"
  };

  if (!rating) return null;

  const displayValue = displayMap[rating] || rating;
  const colorClass = colorMap[rating] || "bg-gray-600";

  return (
    <div
      className={`
        text-white text-xs font-bold px-2 py-1 rounded
        ${colorClass}
        ${className}
      `}
    >
      {displayValue}
    </div>
  );
}

export default AgeRating;
