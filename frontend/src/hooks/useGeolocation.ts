// src/hooks/useGeolocation.ts
import { useState, useEffect } from "react";
import { LocationAddress, nominatimService } from "../services/nominatimService";


interface Coordinates {
  latitude: number;
  longitude: number;
}

interface UseGeolocationReturn {
  location: Coordinates | null;
  address: LocationAddress | null;
  error: string | null;
  loading: boolean;
}

export const useGeolocation = (): UseGeolocationReturn => {
  const [location, setLocation] = useState<Coordinates | null>(null);
  const [address, setAddress] = useState<LocationAddress | null>(null);
  const [error, setError] = useState<string | null>(null);
  const [loading, setLoading] = useState<boolean>(true);

  useEffect(() => {
    const storedLocation = localStorage.getItem("userLocation");
    const storedAddress = localStorage.getItem("userAddress");
    
    if (storedLocation && storedAddress) {
      try {
        const parsedLocation: Coordinates = JSON.parse(storedLocation);
        const parsedAddress: LocationAddress = JSON.parse(storedAddress);
        setLocation(parsedLocation);
        setAddress(parsedAddress);
        setLoading(false);
        return;
      } catch (err) {
        console.error("Erro ao parsear localização:", err);
        localStorage.removeItem("userLocation");
        localStorage.removeItem("userAddress");
      }
    }

    if (!navigator.geolocation) {
      setError("Geolocalização não é suportada pelo navegador.");
      setLoading(false);
      return;
    }

    navigator.geolocation.getCurrentPosition(
      async (position: GeolocationPosition) => {
        const newLocation: Coordinates = {
          latitude: position.coords.latitude,
          longitude: position.coords.longitude,
        };
        
        setLocation(newLocation);
        localStorage.setItem("userLocation", JSON.stringify(newLocation));

        try {
          const locationAddress = await nominatimService.getAddressFromCoordinates(
            newLocation.latitude,
            newLocation.longitude
          );
          setAddress(locationAddress);
          localStorage.setItem("userAddress", JSON.stringify(locationAddress));
        } catch (err) {
          console.error("Erro ao buscar endereço:", err);
        }
        setLoading(false);
      },
      (err: GeolocationPositionError) => {
        setError(err.message);
        setLoading(false);
      }
    );
  }, []);

  return { location, address, error, loading };
};
