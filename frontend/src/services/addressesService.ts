// src/services/addressService.ts
import api from './api/axios';
import { Address } from '../types/Theater';

export const addressService = {
  async getAllAddresses(): Promise<Address[]> {
    const response = await api.get<Address[]>('/theaters/addresses');
    
    return response.data;
  }
};
