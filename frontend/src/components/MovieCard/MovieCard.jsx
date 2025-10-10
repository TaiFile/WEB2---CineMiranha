import { useNavigate } from "react-router-dom";
import AgeRating from "../AgeRating/AgeRating";

function MovieCard({ title, duration, image, ageRating }) {
  const navigate = useNavigate();

  return (
    <div className="flex-shrink-0 w-36 cursor-pointer group">
      <div className="relative overflow-hidden rounded-lg">
        <img
          src={image}
          alt={title}
          className="w-full h-52 object-cover"
          onClick={() => navigate(`/movies`)}
        />

        {ageRating && (
          <AgeRating rating={ageRating} className="absolute bottom-2 right-2" />
        )}
      </div>

      <div className="mt-2">
        <h3 className="text-white text-sm font-semibold leading-tight">
          {title}
        </h3>
        <p className="text-gray-400 text-xs mt-1">{duration}</p>
      </div>
    </div>
  );
}

export default MovieCard;
