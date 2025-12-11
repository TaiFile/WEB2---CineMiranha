import { Theater } from "../types/Theater";
import { Movie } from "../types/Movie";
import api from "./api/axios";

export const theaterService = {
  getTheatersByDistance: async (latitude: number, longitude: number): Promise<Theater[]> => {
    const response = await api.get<Theater[]>('/theaters/by-distance', {
      params: { latitude, longitude }
    });
    return response.data;
  },

  getMoviesByTheaterId: async (theaterId: number): Promise<Movie[]> => {
    const response = await api.get<Movie[]>(`/theaters/${theaterId}/movies`);
    return response.data;
  }
};
