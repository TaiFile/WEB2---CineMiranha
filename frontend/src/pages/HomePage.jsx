import MovieCarousel from '../components/MovieCarousel/MovieCarousel';
import {moviesData} from "@/data/movieData.js";

function HomePage() {
    return (
        <div className="min-h-screen bg-[#282C31] pb-8 pt-6" >
            <MovieCarousel
                title="Filmes em cartaz:"
                movies={moviesData.emCartaz}
            />

            <MovieCarousel
                title="Em breve:"
                movies={moviesData.emBreve}
            />
        </div>
    );
}

export default HomePage;
