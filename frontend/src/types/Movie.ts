import { AgeRating, MovieStatus } from "./enums";

export interface Movie {
  id: number;
  title: string;
  synopsis: string;
  coverUrl: string;
  trailerUrl: string;
  durationInSeconds: number; 
  ageRating: AgeRating;
  status: MovieStatus;
  sessions: string[];
  genres: string[];
}
