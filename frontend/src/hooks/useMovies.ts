import { useEffect, useState } from "react";
import { Movie } from "../types/Movie";
import { movieService } from "../services/movieService";

export const useMovies = () => {
    const [movies, setMovies] = useState<Movie[]>([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState<string | null>(null);

    useEffect(() => {
        const fetchMovies = async () => {
            try {
                setLoading(true);
                const data = await movieService.getAllMovies();
                setMovies(data);
                setError(null);
            }
            catch (err) {
                setError("Failed to fetch movies.");
            }
            finally {
                setLoading(false);
            }
        };

        fetchMovies();
    }, []);

    return { movies, loading, error };
}