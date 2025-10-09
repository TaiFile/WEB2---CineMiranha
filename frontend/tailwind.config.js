/** @type {import('tailwindcss').Config} */
export default {
    content: [
        "./index.html",
        "./src/**/*.{js,ts,jsx,tsx}",
    ],
    theme: {
        extend: {
            colors: {
                cinema: {
                    dark: '#0a0a0a',
                    darker: '#141414',
                    gray: '#1a1a1a',
                    lightGray: '#2a2a2a',
                    red: '#e50914',
                    redHover: '#b8070f',
                    header: '#1F2024',  // ← adicione aqui
                    footer: '#1F2024',

                    light: {
                        900: '#C5C6CC',
                        800: '#D4D6DD',
                        700: '#E8E9F1',
                        600: '#F8F9FE',
                        500: '#FAFAFA',
                    },
                    
                    // Dark Colors
                    darkPalette: {
                        900: '#1F2024',
                        800: '#2F3036',
                        700: '#494A50',
                        600: '#71727A',
                        500: '#8F9098',
                    },

                }
            },
            fontFamily: {
                sans: ['Inter', 'system-ui', 'sans-serif'],
            },
            screens: {
                'xs': '475px',
            },
        },
    },
    plugins: [
        function({ addUtilities }) {
            addUtilities({
                '.scrollbar-hide': {
                    '-ms-overflow-style': 'none',
                    'scrollbar-width': 'none',
                    '&::-webkit-scrollbar': {
                        display: 'none'
                    }
                }
            })
        }
    ],
}
