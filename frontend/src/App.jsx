import Header from './components/Header/Header';
import Footer from './components/Footer/Footer';
import HomePage from './pages/HomePage';

function App() {
    return (
        <div className="min-h-screen bg-[#282C31] flex flex-col">
            <Header />

            <main className="flex-1 pt-16">
                <HomePage />
            </main>

            <Footer />
        </div>
    );
}

export default App;
