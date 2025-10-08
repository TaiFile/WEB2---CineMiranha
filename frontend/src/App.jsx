import Header from './components/Header/Header';
import Footer from './components/Footer/Footer';

function App() {
    return (
        <div className="min-h-screen bg-black flex flex-col">
            <Header />

            <main className="flex-1 pt-20">
                <div className="max-w-7xl mx-auto px-4 md:px-8 py-8">
                    <h1 className="text-white text-4xl md:text-5xl font-bold mb-4">
                        Bem-vindo ao INCINE
                    </h1>
                    <p className="text-gray-400 text-lg">
                        Milhares de filmes e séries esperando por você.
                    </p>
                </div>
            </main>

            <Footer />
        </div>
    );
}

export default App;
