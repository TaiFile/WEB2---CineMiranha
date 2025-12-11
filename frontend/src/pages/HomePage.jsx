import { useState, useEffect } from "react";
import MovieCarousel from "../components/MovieCarousel/MovieCarousel";
import { moviesData } from "@/data/movieData.js";

function HomePage() {
  const [ setLocation] = useState(null);
  const [ setError] = useState(null);

  useEffect(() => {
    const storedLocation = localStorage.getItem("userLocation");
    if (storedLocation) {
      setLocation(JSON.parse(storedLocation));
    } else {
      if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(
          (position) => {
            const newLocation = {
              latitude: position.coords.latitude,
              longitude: position.coords.longitude,
            };
            setLocation(newLocation);
            localStorage.setItem("userLocation", JSON.stringify(newLocation));
          },
          (err) => {
            setError(err.message);
          }
        );
      } else {
        setError("Geolocation is not supported by this browser.");
      }
    }
  }, []);

  return (
    <div className="flex flex-col min-h-screen bg-[#282C31] pb-8 pt-6">
      <MovieCarousel title="Filmes em cartaz:" movies={moviesData.emCartaz} />
      <MovieCarousel title="Em breve:" movies={moviesData.emBreve} />
    </div>
  );
}

export default HomePage;
