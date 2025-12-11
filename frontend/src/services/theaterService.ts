// src/services/addressService.ts
import api from './api/axios';
import { Theater } from '../types/Theater';

export const theaterService = {
  async getAllTheaters(): Promise<Theater[]> {
    const response = await api.get<Theater[]>('/theaters/list');
    return response.data;
  }
};
