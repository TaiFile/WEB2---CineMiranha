import React from "react";
import { MapContainer, TileLayer, Marker, Popup } from "react-leaflet";
import { Icon } from "leaflet";
import { Theater } from "../../types/Theater";
import { openStreetMapService } from "../../services/openstreetmap/openStreetMapService";
import { FaMapMarkerAlt } from "react-icons/fa";
import "leaflet/dist/leaflet.css";

interface TheaterMapProps {
  theater: Theater;
  className?: string;
}

// Custom marker icon
const theaterIcon = new Icon({
  iconUrl: "https://unpkg.com/leaflet@1.9.4/dist/images/marker-icon.png",
  iconRetinaUrl: "https://unpkg.com/leaflet@1.9.4/dist/images/marker-icon-2x.png",
  shadowUrl: "https://unpkg.com/leaflet@1.9.4/dist/images/marker-shadow.png",
  iconSize: [25, 41],
  iconAnchor: [12, 41],
  popupAnchor: [1, -34],
  shadowSize: [41, 41],
});

const TheaterMap: React.FC<TheaterMapProps> = ({ theater, className = "" }) => {
  const { address } = theater;
  
  if (!address?.latitude || !address?.longitude) {
    return (
      <div className={`flex items-center justify-center bg-cinema-darkPalette-700 rounded-lg p-4 ${className}`}>
        <div className="text-gray-400 text-center">
          <FaMapMarkerAlt className="mx-auto mb-2 text-2xl" />
          <p>Localização não disponível</p>
        </div>
      </div>
    );
  }

  const position: [number, number] = [address.latitude, address.longitude];

  return (
    <div className={`rounded-lg overflow-hidden ${className}`}>
      <MapContainer
        center={position}
        zoom={15}
        style={{ height: "100%", width: "100%", minHeight: "200px" }}
        scrollWheelZoom={false}
      >
        <TileLayer
          attribution={openStreetMapService.getAttribution()}
          url={openStreetMapService.getTileUrl()}
        />
        <Marker position={position} icon={theaterIcon}>
          <Popup>
            <div className="text-sm">
              <strong>{theater.name}</strong>
              <br />
              {address.displayName ? (
                <>{address.displayName}</>
              ) : (
                <>
                  {address.street}, {address.number}
                  <br />
                  {address.neighborhood} - {address.city}/{address.state}
                  <br />
                  CEP: {address.zipCode}
                </>
              )}
            </div>
          </Popup>
        </Marker>
      </MapContainer>
    </div>
  );
};

export default TheaterMap;
