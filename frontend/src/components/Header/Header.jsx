import { useState } from 'react';

function Header() {
    const [menuOpen, setMenuOpen] = useState(false);
    const [searchOpen, setSearchOpen] = useState(false);

    return (
        <header className="bg-cinema-footer fixed top-0 w-full z-50">
            <nav className="grid grid-cols-3 items-center px-4 md:px-8 py-2">
                <div className="flex items-center gap-4">
                    <button
                        onClick={() => setMenuOpen(!menuOpen)}
                        className="md:hidden p-2"
                        aria-label="Menu"
                    >
                        <svg className="w-6 h-6 text-white" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                            <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M4 6h16M4 12h16M4 18h16"/>
                        </svg>
                    </button>
                    <ul className="hidden md:flex items-center gap-6 ml-8">
                        <li><a href="/" className="text-white">Início</a></li>
                        <li><a href="/filmes" className="text-white">Filmes</a></li>
                        <li><a href="/series" className="text-white">Séries</a></li>
                        <li><a href="/minha-lista" className="text-white">Minha Lista</a></li>
                    </ul>
                </div>

                <div className="flex justify-center">
                    <a href="/">
                        <img src="/Images/logo.png" alt="INCINE Logo" className="w-24"/>
                    </a>
                </div>

                <div className="flex items-center justify-end gap-3">
                    {searchOpen && (
                        <input
                            type="text"
                            placeholder="Buscar"
                            className="w-40 md:w-48 px-3 py-1 bg-gray-700 text-white rounded-full text-sm "
                            autoFocus
                        />
                    )}

                    <button onClick={() => setSearchOpen(!searchOpen)} aria-label="Buscar" className="p-2">
                        <svg className="w-5 h-5 text-white" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                            <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M21 21l-6-6m2-5a7 7 0 11-14 0 7 7 0 0114 0z"/>
                        </svg>
                    </button>

                    <button aria-label="Perfil" className="w-8 h-8">
                        <svg className="w-5 h-5 text-white" fill="currentColor" viewBox="0 0 20 20">
                            <path fillRule="evenodd" d="M10 9a3 3 0 100-6 3 3 0 000 6zm-7 9a7 7 0 1114 0H3z" clipRule="evenodd"/>
                        </svg>
                    </button>
                </div>
            </nav>

            {menuOpen && (
                <div className="md:hidden bg-black border-t border-gray-800">
                    <ul className="px-4 py-4">
                        <li><a href="/" className="block py-2 text-white">Início</a></li>
                        <li><a href="/filmes" className="block py-2 text-white">Filmes</a></li>
                        <li><a href="/series" className="block py-2 text-white">Séries</a></li>
                        <li><a href="/minha-lista" className="block py-2 text-white">Minha Lista</a></li>
                    </ul>
                </div>
            )}
        </header>
    );
}

export default Header;
