import MovieDetails from "../components/MovieDetails/MovieDetails";
import StepProgressBar from "../components/StepProgressBar/StepProgressBar";
import { moviesData } from "@/data/movieData.js";

function Sessions() {
  const movie = moviesData.emCartaz[0];

  return (
    <div>
      <StepProgressBar
        currentStep={1}
        totalSteps={7}
        label="Escolha da sessão"
      />

      <MovieDetails movie={movie} />
    </div>
  );
}

export default Sessions;
