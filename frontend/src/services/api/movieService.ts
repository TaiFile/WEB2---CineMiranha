import { Movie } from "../../types/Movie";
import { MovieStatus } from "../../types/enums";
import api from "./axios";

export const movieService = {
  getAllMovies: async (status?: MovieStatus): Promise<Movie[]> => {
    const response = await api.get<Movie[]>('/movies', {
      params: status ? { status } : undefined
    });
    return response.data; 
  },

  getMovieById: async (id: number): Promise<Movie> => {
    const response = await api.get<Movie>(`/movies/${id}`);
    return response.data;
  },

  searchMovies: async (query: string): Promise<Movie[]> => {
    const response = await api.get<Movie[]>(`/movies/search?q=${query}`);
    return response.data;
  }
};
