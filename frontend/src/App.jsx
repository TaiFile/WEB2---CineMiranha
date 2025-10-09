import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Header from "./components/Header/Header";
import Footer from "./components/Footer/Footer";
import HomePage from "./pages/HomePage";
import MovieSynopsis from "./pages/MovieSynopsis";
import Sessions from './pages/Sessions';

function App() {
  return (
    <Router>
      <div className="min-h-screen bg-[#282C31] flex flex-col">
        <Header />

        <main className="flex-1 pt-16">
          <Routes>
            <Route path="/" element={<HomePage />} />
            <Route path="/movies" element={<MovieSynopsis />} />
            <Route path="/movies/session" element={<Sessions />} />
          </Routes>
        </main>

        <Footer />
      </div>
    </Router>
  );
}

export default App;
