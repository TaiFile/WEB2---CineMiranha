export interface GeocodingResult {
  lat: number;
  lon: number;
  displayName: string;
}

const NOMINATIM_BASE_URL = "https://nominatim.openstreetmap.org";

export const openStreetMapService = {
  tileUrl: "https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png",
  attribution:
    '&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors',

  reverseGeocode: async (lat: number, lon: number): Promise<string> => {
    const response = await fetch(
      `${NOMINATIM_BASE_URL}/reverse?format=json&lat=${lat}&lon=${lon}`
    );
    const data = await response.json();
    return data.display_name || "";
  },
};
