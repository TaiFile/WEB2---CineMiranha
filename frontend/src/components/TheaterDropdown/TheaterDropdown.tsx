import React, { useState, useEffect, useRef } from "react";
import { Theater } from "../../types/Theater";
import { theaterService } from "../../services/theaterService";
import { useUserGeoLocation } from "../../hooks/useUserGeoLocation";
import { FaChevronDown, FaMapMarkerAlt } from "react-icons/fa";

interface TheaterDropdownProps {
  onTheaterSelect: (theater: Theater | null) => void;
  selectedTheater: Theater | null;
}

const TheaterDropdown: React.FC<TheaterDropdownProps> = ({
  onTheaterSelect,
  selectedTheater,
}) => {
  const [isOpen, setIsOpen] = useState(false);
  const [theaters, setTheaters] = useState<Theater[]>([]);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState<string | null>(null);
  const dropdownRef = useRef<HTMLDivElement>(null);

  const { location, loading: locationLoading, error: locationError } = useUserGeoLocation();

  useEffect(() => {
    const fetchTheaters = async () => {
      if (!location) return;

      try {
        setLoading(true);
        const data = await theaterService.getTheatersByDistance(
          location.latitude,
          location.longitude
        );
        setTheaters(data);
        
        // Auto-select the first theater if none selected
        if (data.length > 0 && !selectedTheater) {
          onTheaterSelect(data[0]);
        }
      } catch (err) {
        setError("Erro ao carregar cinemas");
        console.error(err);
      } finally {
        setLoading(false);
      }
    };

    fetchTheaters();
  }, [location]);

  // Close dropdown when clicking outside
  useEffect(() => {
    const handleClickOutside = (event: MouseEvent) => {
      if (dropdownRef.current && !dropdownRef.current.contains(event.target as Node)) {
        setIsOpen(false);
      }
    };

    document.addEventListener("mousedown", handleClickOutside);
    return () => document.removeEventListener("mousedown", handleClickOutside);
  }, []);

  const formatDistance = (distance?: number) => {
    if (!distance) return "";
    if (distance < 1) {
      return `${Math.round(distance * 1000)}m`;
    }
    return `${distance.toFixed(1)}km`;
  };

  if (locationLoading) {
    return (
      <div className="flex items-center gap-2 text-gray-400 text-sm">
        <div className="w-4 h-4 border-2 border-gray-600 border-t-white rounded-full animate-spin"></div>
        <span>Obtendo localização...</span>
      </div>
    );
  }

  if (locationError) {
    return (
      <div className="text-red-400 text-sm">
        <FaMapMarkerAlt className="inline mr-2" />
        {locationError}
      </div>
    );
  }

  return (
    <div ref={dropdownRef} className="relative w-full max-w-xs">
      <button
        onClick={() => setIsOpen(!isOpen)}
        className="w-full flex items-center justify-between gap-2 bg-cinema-darkPalette-700 hover:bg-cinema-darkPalette-600 text-white px-4 py-2 rounded-lg transition-colors"
        disabled={loading}
      >
        <div className="flex items-center gap-2">
          <FaMapMarkerAlt className="text-cinema-light-900" />
          {loading ? (
            <span className="text-gray-400">Carregando...</span>
          ) : selectedTheater ? (
            <span className="truncate">{selectedTheater.name}</span>
          ) : (
            <span className="text-gray-400">Selecione um cinema</span>
          )}
        </div>
        <FaChevronDown
          className={`transition-transform ${isOpen ? "rotate-180" : ""}`}
        />
      </button>

      {isOpen && (
        <div className="absolute top-full left-0 right-0 mt-2 bg-cinema-darkPalette-700 rounded-lg shadow-lg overflow-hidden z-50">
          {error ? (
            <div className="px-4 py-3 text-red-400 text-sm">{error}</div>
          ) : theaters.length === 0 ? (
            <div className="px-4 py-3 text-gray-400 text-sm">
              Nenhum cinema encontrado
            </div>
          ) : (
            <ul className="max-h-60 overflow-y-auto">
              {theaters.map((theater) => (
                <li key={theater.id}>
                  <button
                    onClick={() => {
                      onTheaterSelect(theater);
                      setIsOpen(false);
                    }}
                    className={`w-full flex items-center justify-between px-4 py-3 hover:bg-cinema-darkPalette-600 transition-colors text-left ${
                      selectedTheater?.id === theater.id
                        ? "bg-cinema-darkPalette-600"
                        : ""
                    }`}
                  >
                    <div className="flex items-center gap-3">
                      {theater.logoUrl && (
                        <img
                          src={theater.logoUrl}
                          alt={theater.name}
                          className="w-8 h-8 rounded object-cover"
                        />
                      )}
                      <div>
                        <p className="text-white font-medium">{theater.name}</p>
                        {theater.address && (
                          <p className="text-gray-400 text-xs">
                            {theater.address.neighborhood}, {theater.address.city}
                          </p>
                        )}
                      </div>
                    </div>
                    {theater.distance !== undefined && (
                      <span className="text-cinema-light-900 text-sm">
                        {formatDistance(theater.distance)}
                      </span>
                    )}
                  </button>
                </li>
              ))}
            </ul>
          )}
        </div>
      )}
    </div>
  );
};

export default TheaterDropdown;
