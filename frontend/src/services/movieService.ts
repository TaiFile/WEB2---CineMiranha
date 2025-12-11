import { Movie } from "../types/Movie";
import api from "./api/axios";

export const movieService = {
  getAllMovies: async (): Promise<Movie[]> => {
    const response = await api.get<Movie[]>('/movies');
    return response.data; 
  },

  getMovieById: async (id: number): Promise<Movie> => {
    const response = await api.get<Movie>(`/movies/${id}`);
    return response.data;
  },

  getMoviesByStatus: async (status: string): Promise<Movie[]> => {
    const response = await api.get<Movie[]>(`/movies?status=${status}`);
    return response.data;
  },

  searchMovies: async (query: string): Promise<Movie[]> => {
    const response = await api.get<Movie[]>(`/movies/search?q=${query}`);
    return response.data;
  }
};
