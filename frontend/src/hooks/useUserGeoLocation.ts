import { useState, useEffect } from "react";

interface GeoLocation {
  latitude: number;
  longitude: number;
}

const STORAGE_KEY = "user_geolocation";

export const useUserGeoLocation = () => {
  const [location, setLocation] = useState<GeoLocation | null>(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState<string | null>(null);

  useEffect(() => {
    const storedLocation = localStorage.getItem(STORAGE_KEY);
    if (storedLocation) {
      try {
        const parsed = JSON.parse(storedLocation) as GeoLocation;
        setLocation(parsed);
        setLoading(false);
        return;
      } catch {
        localStorage.removeItem(STORAGE_KEY);
      }
    }

    if (!navigator.geolocation) {
      setError("Geolocalização não é suportada pelo seu navegador");
      setLoading(false);
      return;
    }

    navigator.geolocation.getCurrentPosition(
      (position) => {
        const newLocation: GeoLocation = {
          latitude: position.coords.latitude,
          longitude: position.coords.longitude,
        };
        localStorage.setItem(STORAGE_KEY, JSON.stringify(newLocation));
        setLocation(newLocation);
        setLoading(false);
      },
      (err) => {
        setError(`Erro ao obter localização: ${err.message}`);
        setLoading(false);
      },
      {
        enableHighAccuracy: true,
        timeout: 10000,
        maximumAge: 600000, 
      }
    );
  }, []);

  const refreshLocation = () => {
    setLoading(true);
    setError(null);

    navigator.geolocation.getCurrentPosition(
      (position) => {
        const newLocation: GeoLocation = {
          latitude: position.coords.latitude,
          longitude: position.coords.longitude,
        };
        localStorage.setItem(STORAGE_KEY, JSON.stringify(newLocation));
        setLocation(newLocation);
        setLoading(false);
      },
      (err) => {
        setError(`Erro ao obter localização: ${err.message}`);
        setLoading(false);
      }
    );
  };

  return { location, loading, error, refreshLocation };
};
