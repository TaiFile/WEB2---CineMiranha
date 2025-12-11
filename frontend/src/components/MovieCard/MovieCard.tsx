// src/components/MovieCard/MovieCard.tsx
import { useNavigate } from "react-router-dom";
import AgeRating from "../AgeRating/AgeRating";
import { Movie } from "../../types/Movie";
import React from "react";

interface MovieCardProps extends Pick<Movie, 'id' | 'title' | 'durationInSeconds' | 'coverUrl' | 'ageRating'> {}

const MovieCard: React.FC<MovieCardProps> = ({ id, title, durationInSeconds, coverUrl, ageRating }) => {
  const navigate = useNavigate();
  const durationInMinutes = Math.floor(durationInSeconds / 60);
  const formattedDuration = `${durationInMinutes} min`;

  return (
    <div className="max-lg:flex-shrink-0 w-36 lg:w-52 cursor-pointer group">
      <div className="relative overflow-hidden rounded-lg">
        <img
          src={coverUrl}
          alt={title}
          className="w-full h-52 lg:h-72 object-cover"
          onClick={() => navigate(`/movies/${id}`)} 
        />

        {ageRating && (
          <AgeRating rating={ageRating} className="absolute bottom-2 right-2" />
        )}
      </div>

      <div className="mt-2">
        <h3 className="text-white text-sm font-semibold leading-tight">
          {title}
        </h3>
        <p className="text-gray-400 text-xs mt-1">{formattedDuration}</p>
      </div>
    </div>
  );
}

export default MovieCard;
