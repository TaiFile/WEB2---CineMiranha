import { useState } from 'react';

function Header() {
    const [menuOpen, setMenuOpen] = useState(false);

    return (
        <header className=" bg-cinema-footer from-black to-transparent fixed top-0 w-full z-50 transition-all duration-300">
            <nav className="grid grid-cols-3 items-center justify-between px-4 md:px-8 py-2">
                <div className="flex items-center gap-4">
                    <button
                        onClick={() => setMenuOpen(!menuOpen)}
                        className="md:hidden p-2 hover:bg-gray-800 rounded transition"
                        aria-label="Menu"
                    >
                        <svg
                            className="w-6 h-6 text-white"
                            fill="none"
                            stroke="currentColor"
                            viewBox="0 0 24 24"
                        >
                            <path
                                strokeLinecap="round"
                                strokeLinejoin="round"
                                strokeWidth={2}
                                d="M4 6h16M4 12h16M4 18h16"
                            />
                        </svg>
                    </button>
                    <ul className="hidden md:flex items-center gap-6 ml-8">
                        <li>
                            <a href="/" className="text-white hover:text-gray-300 transition font-medium">
                                Início
                            </a>
                        </li>
                        <li>
                            <a href="/filmes" className="text-white hover:text-gray-300 transition font-medium">
                                Filmes
                            </a>
                        </li>
                        <li>
                            <a href="/series" className="text-white hover:text-gray-300 transition font-medium">
                                Séries
                            </a>
                        </li>
                        <li>
                            <a href="/minha-lista" className="text-white hover:text-gray-300 transition font-medium">
                                Minha Lista
                            </a>
                        </li>
                    </ul>
                </div>

                <div className="flex justify-center">
                    <a href="/" className="">
                        <img src="/Images/logo.png" alt="INCINE Logo" className="w-24 "/>
                    </a>
                </div>

                <div className="flex items-center justify-end gap-3">
                    <button
                        aria-label="Buscar"
                        className="p-2  rounded-full "
                    >
                        <svg
                            className="w-5 h-5 text-white transition"
                            fill="none"
                            stroke="currentColor"
                            viewBox="0 0 24 24"
                        >
                            <path
                                strokeLinecap="round"
                                strokeLinejoin="round"
                                strokeWidth={2}
                                d="M21 21l-6-6m2-5a7 7 0 11-14 0 7 7 0 0114 0z"
                            />
                        </svg>
                    </button>

                    <button
                        aria-label="Notificações"
                        className="p-2 hover:bg-gray-800 rounded-full transition group hidden md:block"
                    >
                        <svg
                            className="w-5 h-5 text-white  transition"
                            fill="none"
                            stroke="currentColor"
                            viewBox="0 0 24 24"
                        >
                            <path
                                strokeLinecap="round"
                                strokeLinejoin="round"
                                strokeWidth={2}
                                d="M15 17h5l-1.405-1.405A2.032 2.032 0 0118 14.158V11a6.002 6.002 0 00-4-5.659V5a2 2 0 10-4 0v.341C7.67 6.165 6 8.388 6 11v3.159c0 .538-.214 1.055-.595 1.436L4 17h5m6 0v1a3 3 0 11-6 0v-1m6 0H9"
                            />
                        </svg>
                    </button>

                    <button
                        aria-label="Perfil"
                        className="w-8 h-8  rounded flex items-center justify-center "
                    >
                        <svg
                            className="w-5 h-5 text-white"
                            fill="currentColor"
                            viewBox="0 0 20 20"
                        >
                            <path
                                fillRule="evenodd"
                                d="M10 9a3 3 0 100-6 3 3 0 000 6zm-7 9a7 7 0 1114 0H3z"
                                clipRule="evenodd"
                            />
                        </svg>
                    </button>
                </div>
            </nav>
            
            {menuOpen && (
                <div className="md:hidden bg-black bg-opacity-95 border-t border-gray-800">
                    <ul className="px-4 py-4 space-y-1">
                        <li>
                            <a
                                href="/"
                                className="block py-3 px-4 text-white hover:bg-gray-800 rounded transition font-medium"
                            >
                                Início
                            </a>
                        </li>
                        <li>
                            <a
                                href="/filmes"
                                className="block py-3 px-4 text-white hover:bg-gray-800 rounded transition font-medium"
                            >
                                Filmes
                            </a>
                        </li>
                        <li>
                            <a
                                href="/series"
                                className="block py-3 px-4 text-white hover:bg-gray-800 rounded transition font-medium"
                            >
                                Séries
                            </a>
                        </li>
                        <li>
                            <a
                                href="/minha-lista"
                                className="block py-3 px-4 text-white hover:bg-gray-800 rounded transition font-medium"
                            >
                                Minha Lista
                            </a>
                        </li>
                    </ul>
                </div>
            )}
        </header>
    );
}

export default Header;
