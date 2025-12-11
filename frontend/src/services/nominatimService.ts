// src/services/nominatimService.ts
import axios from 'axios';

const nominatimApi = axios.create({
  baseURL: 'https://nominatim.openstreetmap.org',
  timeout: 10000,
  headers: {
    'Accept-Language': 'pt-BR',
  }
});

export interface LocationAddress {
  street?: string;
  neighborhood?: string;
  city?: string;
  state?: string;
  postalCode?: string;
  country?: string;
  fullAddress: string;
}

// ✅ Export nomeado
export const nominatimService = {
  // Reverse Geocoding: coordenadas -> endereço
  async getAddressFromCoordinates(
    latitude: number,
    longitude: number
  ): Promise<LocationAddress> {
    try {
      const response = await nominatimApi.get('/reverse', {
        params: {
          lat: latitude,
          lon: longitude,
          format: 'json',
          addressdetails: 1,
        }
      });

      const { address, display_name } = response.data;

      return {
        street: address.road,
        neighborhood: address.suburb,
        city: address.city || address.town || address.village,
        state: address.state,
        postalCode: address.postcode,
        country: address.country,
        fullAddress: display_name,
      };
    } catch (error) {
      console.error('Erro ao buscar endereço:', error);
      throw new Error('Não foi possível obter o endereço');
    }
  },

  // Geocoding: endereço -> coordenadas
  async getCoordinatesFromAddress(address: string): Promise<{
    latitude: number;
    longitude: number;
  }> {
    try {
      const response = await nominatimApi.get('/search', {
        params: {
          q: address,
          format: 'json',
          limit: 1,
        }
      });

      if (response.data.length === 0) {
        throw new Error('Endereço não encontrado');
      }

      const result = response.data[0];
      return {
        latitude: parseFloat(result.lat),
        longitude: parseFloat(result.lon),
      };
    } catch (error) {
      console.error('Erro ao buscar coordenadas:', error);
      throw new Error('Não foi possível obter as coordenadas');
    }
  }
};
