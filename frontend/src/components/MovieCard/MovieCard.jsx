function MovieCard({ title, duration, image, ageRating }) {
    return (
        <div className="flex-shrink-0 w-36 cursor-pointer group">
            {/* Imagem do Filme */}
            <div className="relative overflow-hidden rounded-lg">
                <img
                    src={image}
                    alt={title}
                    className="w-full h-52 object-cover group-hover:scale-110 transition-transform duration-300"
                />

                {/* Badge de Classificação */}
                {ageRating && (
                    <span className={`
            absolute bottom-2 right-2 text-white text-xs font-bold px-2 py-1 rounded
            ${ageRating === 'L' ? 'bg-green-600' :
                        ageRating === '10' ? 'bg-blue-600' :
                            ageRating === '12' ? 'bg-yellow-600' :
                                ageRating === '14' ? 'bg-orange-600' :
                                    ageRating === '16' || ageRating === '18' ? 'bg-red-600' : 'bg-gray-600'}
          `}>
            {ageRating}
          </span>
                )}
            </div>

            {/* Informações */}
            <div className="mt-2">
                <h3 className="text-white text-sm font-semibold line-clamp-2 leading-tight">
                    {title}
                </h3>
                <p className="text-gray-400 text-xs mt-1">{duration}</p>
            </div>
        </div>
    );
}

export default MovieCard;
