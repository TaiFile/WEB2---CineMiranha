// OpenStreetMap service for geocoding and map utilities
// Note: Leaflet uses OpenStreetMap tiles directly, so most map functionality
// is handled by the react-leaflet library. This service provides additional utilities.

export interface GeocodingResult {
  lat: number;
  lon: number;
  displayName: string;
}

const NOMINATIM_BASE_URL = "https://nominatim.openstreetmap.org";

export const mapService = {
  /**
   * Search for a location using OpenStreetMap Nominatim API
   */
  searchLocation: async (query: string): Promise<GeocodingResult[]> => {
    const response = await fetch(
      `${NOMINATIM_BASE_URL}/search?format=json&q=${encodeURIComponent(query)}`
    );
    const data = await response.json();
    return data.map((item: { lat: string; lon: string; display_name: string }) => ({
      lat: parseFloat(item.lat),
      lon: parseFloat(item.lon),
      displayName: item.display_name,
    }));
  },

  /**
   * Reverse geocoding - get address from coordinates
   */
  reverseGeocode: async (lat: number, lon: number): Promise<string> => {
    const response = await fetch(
      `${NOMINATIM_BASE_URL}/reverse?format=json&lat=${lat}&lon=${lon}`
    );
    const data = await response.json();
    return data.display_name || "";
  },

  /**
   * Get the OpenStreetMap tile URL
   */
  getTileUrl: (): string => {
    return "https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png";
  },

  /**
   * Get attribution for OpenStreetMap
   */
  getAttribution: (): string => {
    return '&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors';
  },
};
