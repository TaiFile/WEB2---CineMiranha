import MovieCard from '../MovieCard/MovieCard';

function MovieCarousel({ title, movies, showMore = true }) {
    return (
        <section className="mb-8">
            <div className="flex justify-between items-center mb-4 px-4 lg:px-6">
                <h2 className="text-white text-lg font-bold">{title}</h2>
                {showMore && (
                    <button className="text-gray-400 text-sm hover:text-white transition-colors">
                        Ver mais
                    </button>
                )}
            </div>

            <div className="overflow-x-auto scrollbar-hide">
                <div className="flex justify-start gap-3 lg:gap-6 px-4 lg:px-6 pb-2">
                    {movies.map((movie) => (
                        <MovieCard
                            key={movie.id}
                            title={movie.title}
                            duration={movie.duration}
                            image={movie.image}
                            ageRating={movie.ageRating}
                        />
                    ))}
                </div>
            </div>
        </section>
    );
}

export default MovieCarousel;
