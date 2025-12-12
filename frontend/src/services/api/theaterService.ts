import { Theater } from "../../types/Theater";
import { Movie } from "../../types/Movie";
import api from "./axios";
import { openStreetMapService } from "../openstreetmap/openStreetMapService";

const reverseGeocodeCache = new Map<string, string>();

async function reverseGeocodeTheaterAddress(theater: Theater): Promise<Theater> {
  const latitude = theater.address?.latitude;
  const longitude = theater.address?.longitude;

  if (typeof latitude !== "number" || typeof longitude !== "number") {
    return theater;
  }

  const cacheKey = `${latitude},${longitude}`;
  const cached = reverseGeocodeCache.get(cacheKey);
  if (cached) {
    return {
      ...theater,
      address: {
        ...theater.address,
        displayName: cached,
        street: undefined,
        number: undefined,
        neighborhood: undefined,
        city: undefined,
        state: undefined,
        zipCode: undefined,
      },
    };
  }

  try {
    const displayName = await openStreetMapService.reverseGeocode(latitude, longitude);
    reverseGeocodeCache.set(cacheKey, displayName);

    return {
      ...theater,
      address: {
        ...theater.address,
        displayName,
        street: undefined,
        number: undefined,
        neighborhood: undefined,
        city: undefined,
        state: undefined,
        zipCode: undefined,
      },
    };
  } catch {
    return {
      ...theater,
      address: {
        ...theater.address,
        displayName: "",
        street: undefined,
        number: undefined,
        neighborhood: undefined,
        city: undefined,
        state: undefined,
        zipCode: undefined,
      },
    };
  }
}

export const theaterService = {
  getTheatersByDistance: async (latitude: number, longitude: number): Promise<Theater[]> => {
    const response = await api.get<Theater[]>('/theaters/by-distance', {
      params: { latitude, longitude }
    });

    // Educational: derive the full address via reverse geocoding using ONLY coordinates.
    const theaters = response.data;
    return Promise.all(theaters.map(reverseGeocodeTheaterAddress));
  },

  getMoviesByTheaterId: async (theaterId: number): Promise<Movie[]> => {
    const response = await api.get<Movie[]>(`/theaters/${theaterId}/movies`);
    return response.data;
  }
};
