import { AgeRating as AgeRatingEnum } from "../../types/enums";
import React from "react";

interface AgeRatingProps {
  rating: AgeRatingEnum;
  className?: string;
}

const ratingMap = {
  [AgeRatingEnum.GENERAL_AUDIENCE]: { text: "L", color: "bg-green-600" },
  [AgeRatingEnum.TEN_YEARS]: { text: "10", color: "bg-blue-600" },
  [AgeRatingEnum.TWELVE_YEARS]: { text: "12", color: "bg-yellow-600" },
  [AgeRatingEnum.FOURTEEN_YEARS]: { text: "14", color: "bg-orange-600" },
  [AgeRatingEnum.SIXTEEN_YEARS]: { text: "16", color: "bg-red-600" },
  [AgeRatingEnum.EIGHTEEN_YEARS]: { text: "18", color: "bg-red-600" },
};

const AgeRating: React.FC<AgeRatingProps> = ({ rating, className }) => {
  console.log(rating);
  const { text, color } = ratingMap[rating] || { text: "N/A", color: "bg-gray-600" };

  return (
    <div
      className={`
             text-white text-xs font-bold px-2 py-1 rounded
            ${color}
          ${className}`}
    >
      {text}
    </div>
  );
};

export default AgeRating;
