export const moviesData = {
    emCartaz: [
        {
            id: 1,
            title: 'AINDA ESTOU AQUI',
            duration: '135 min',
            image: '/movies/ainda-estou-aqui.jpg',
            ageRating: '14',
            genre: 'Drama',
            synopsis: 'Uma família brasileira enfrenta desafios durante a ditadura militar.',
            year: 2024,
        },
        {
            id: 2,
            title: 'CHICO BENTO E A GOIABEIRA MARAVILHOSA',
            duration: '100 min',
            image: '/movies/chico-bento.jpg',
            ageRating: 'L',
            genre: 'Aventura',
            synopsis: 'Chico Bento vive uma grande aventura em busca da goiabeira mágica.',
            year: 2024,
        },
        {
            id: 3,
            title: 'MOANA 2',
            duration: '120 min',
            image: '/movies/moana-2.jpg',
            ageRating: 'L',
            genre: 'Animação',
            synopsis: 'Moana retorna em uma nova jornada pelos mares da Oceania.',
            year: 2024,
        },
        {
            id: 4,
            title: 'WICKED',
            duration: '160 min',
            image: '/movies/wicked.jpg',
            ageRating: '12',
            genre: 'Musical',
            synopsis: 'A história não contada das bruxas de Oz.',
            year: 2024,
        },
        {
            id: 5,
            title: 'GLADIADOR II',
            duration: '155 min',
            image: '/movies/gladiador-2.jpg',
            ageRating: '16',
            genre: 'Ação',
            synopsis: 'A continuação épica da saga do gladiador.',
            year: 2024,
        },
    ],

    emBreve: [
        {
            id: 6,
            title: 'SHREK 5',
            duration: '30/02',
            image: '/movies/shrek-5.jpg',
            genre: 'Animação',
            synopsis: 'O ogro verde retorna para mais uma aventura hilária.',
            year: 2025,
        },
        {
            id: 7,
            title: 'CAPITÃO AMÉRICA: ADMIRÁVEL MUNDO NOVO',
            duration: '30/02',
            image: '/movies/capitao-america.jpg',
            ageRating: '12',
            genre: 'Ação',
            synopsis: 'O novo Capitão América enfrenta ameaças globais.',
            year: 2025,
        },
        {
            id: 8,
            title: 'HOMEM-ARANHA 4',
            duration: '15/04',
            image: '/movies/homem-aranha-4.jpg',
            ageRating: '12',
            genre: 'Ação',
            synopsis: 'Peter Parker retorna em uma nova aventura.',
            year: 2025,
        },
        {
            id: 9,
            title: 'AVATAR 3',
            duration: '20/12',
            image: '/movies/avatar-3.jpg',
            ageRating: '12',
            genre: 'Ficção',
            synopsis: 'A saga continua em Pandora.',
            year: 2025,
        },
    ],

    series: [
        {
            id: 10,
            title: 'THE LAST OF US - Temporada 2',
            duration: '8 episódios',
            image: '/movies/the-last-of-us.jpg',
            ageRating: '18',
            genre: 'Drama',
            synopsis: 'Joel e Ellie continuam sua jornada.',
            year: 2025,
        },
        {
            id: 11,
            title: 'THE MANDALORIAN - Temporada 4',
            duration: '12 episódios',
            image: '/movies/mandalorian.jpg',
            ageRating: '14',
            genre: 'Ficção',
            synopsis: 'As aventuras do mandaloriano continuam.',
            year: 2025,
        },
    ],
};

// Função para buscar filmes por categoria
export function getMoviesByCategory(category) {
    return moviesData[category] || [];
}

// Função para buscar filme por ID
export function getMovieById(id) {
    const allMovies = [
        ...moviesData.emCartaz,
        ...moviesData.emBreve,
        ...moviesData.series,
    ];
    return allMovies.find(movie => movie.id === id);
}

// Função para buscar filmes por gênero
export function getMoviesByGenre(genre) {
    const allMovies = [
        ...moviesData.emCartaz,
        ...moviesData.emBreve,
        ...moviesData.series,
    ];
    return allMovies.filter(movie => movie.genre === genre);
}
