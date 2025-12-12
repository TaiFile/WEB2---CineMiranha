package br.ufscar.pooa.cinema_api.infrastructure.database;

import br.ufscar.pooa.cinema_api.domain.repositories.client.IClientRepository;
import br.ufscar.pooa.cinema_api.domain.repositories.genre.IGenreRepository;
import br.ufscar.pooa.cinema_api.domain.repositories.manager.IManagerRepository;
import br.ufscar.pooa.cinema_api.domain.repositories.movie.IMovieRepository;
import br.ufscar.pooa.cinema_api.domain.repositories.room.IRoomRepository;
import br.ufscar.pooa.cinema_api.domain.repositories.row.IRowRepository;
import br.ufscar.pooa.cinema_api.domain.repositories.seat.ISeatRepository;
import br.ufscar.pooa.cinema_api.domain.repositories.session.ISessionRepository;
import br.ufscar.pooa.cinema_api.domain.repositories.theater.ITheaterRepository;
import br.ufscar.pooa.cinema_api.domain.entities.Address;
import br.ufscar.pooa.cinema_api.domain.entities.Client;
import br.ufscar.pooa.cinema_api.domain.entities.Genre;
import br.ufscar.pooa.cinema_api.domain.entities.Manager;
import br.ufscar.pooa.cinema_api.domain.entities.Movie;
import br.ufscar.pooa.cinema_api.domain.entities.Room;
import br.ufscar.pooa.cinema_api.domain.entities.Row;
import br.ufscar.pooa.cinema_api.domain.entities.Seat;
import br.ufscar.pooa.cinema_api.domain.entities.Session;
import br.ufscar.pooa.cinema_api.domain.entities.Theater;
import br.ufscar.pooa.cinema_api.domain.enums.AgeRating;
import br.ufscar.pooa.cinema_api.domain.enums.Format;
import br.ufscar.pooa.cinema_api.domain.enums.Gender;
import br.ufscar.pooa.cinema_api.domain.enums.MovieStatus;
import br.ufscar.pooa.cinema_api.domain.enums.Role;
import br.ufscar.pooa.cinema_api.domain.enums.RoomType;
import br.ufscar.pooa.cinema_api.domain.enums.SeatType;
import br.ufscar.pooa.cinema_api.domain.enums.Subtitle;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Profile("dev")
public class DatabaseSeeder implements CommandLineRunner {

    private final ITheaterRepository theaterRepository;
    private final IClientRepository clientRepository;
    private final IManagerRepository managerRepository;
    private final IGenreRepository genreRepository;
    private final IMovieRepository movieRepository;
    private final IRoomRepository roomRepository;
    private final IRowRepository rowRepository;
    private final ISeatRepository seatRepository;
    private final ISessionRepository sessionRepository;
    private final PasswordEncoder passwordEncoder;

    public DatabaseSeeder(ITheaterRepository theaterRepository, IClientRepository clientRepository,
        IManagerRepository managerRepository,
        IGenreRepository genreRepository, IMovieRepository movieRepository,
        IRoomRepository roomRepository, IRowRepository rowRepository,
        ISeatRepository seatRepository, ISessionRepository sessionRepository,
        PasswordEncoder passwordEncoder) {
        this.theaterRepository = theaterRepository;
        this.clientRepository = clientRepository;
        this.managerRepository = managerRepository;
        this.genreRepository = genreRepository;
        this.movieRepository = movieRepository;
        this.roomRepository = roomRepository;
        this.rowRepository = rowRepository;
        this.seatRepository = seatRepository;
        this.sessionRepository = sessionRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        if (theaterRepository.count() > 0) {
            System.out.println("Banco de dados já populado. Seeding não executado.");
            return;
        }

        System.out.println("Iniciando seeding do banco de dados...");

        // ============================================================
        // 1. GENRES
        // ============================================================
        Genre actionGenre = new Genre().setName("Ação");
        Genre comedyGenre = new Genre().setName("Comédia");
        Genre dramaGenre = new Genre().setName("Drama");
        Genre scifiGenre = new Genre().setName("Ficção Científica");
        Genre horrorGenre = new Genre().setName("Terror");
        Genre animationGenre = new Genre().setName("Animação");
        Genre familyGenre = new Genre().setName("Família");
        Genre adventureGenre = new Genre().setName("Aventura");
        Genre fantasyGenre = new Genre().setName("Fantasia");
        Genre musicalGenre = new Genre().setName("Musical");
        Genre crimeGenre = new Genre().setName("Crime");

        genreRepository.saveAll(List.of(
            actionGenre, comedyGenre, dramaGenre, scifiGenre, horrorGenre,
            animationGenre, familyGenre, adventureGenre, fantasyGenre, musicalGenre, crimeGenre
        ));

        // ============================================================
        // 2. MOVIES - NOW PLAYING
        // ============================================================
        Movie movieAindaEstouAqui = new Movie()
            .setTitle("AINDA ESTOU AQUI")
            .setDurationInSeconds(8100)
            .setCoverUrl("/Images/ainda-estou-aqui.jpg")
            .setTrailerUrl("https://www.youtube.com/embed/_NzqP0jmk3o?si=9c6tXFlDYT4VJSeG")
            .setSynopsis(
                "Rio de Janeiro, início dos anos 1970. O país enfrenta o endurecimento da ditadura militar. Os Paiva — Rubens, Eunice e seus cinco filhos — vivem na frente da praia, numa casa de portas abertas para os amigos. Um dia, Rubens é levado por militares à paisana e desaparece. Eunice, cuja busca pela verdade sobre o destino de seu marido se estenderia por décadas, é obrigada a se reinventar e traçar um novo futuro para si e seus filhos. Baseado no livro biográfico de Marcelo Rubens Paiva.")
            .setAgeRating(AgeRating.FOURTEEN_YEARS)
            .setGenres(new ArrayList<>(List.of(dramaGenre)))
            .setStatus(MovieStatus.NOW_PLAYING);
        movieRepository.save(movieAindaEstouAqui);

        Movie movieChicoBento = new Movie()
            .setTitle("CHICO BENTO E A GOIABEIRA MARAVILHOSA")
            .setDurationInSeconds(6000)
            .setCoverUrl("/Images/chico-bento.jpg")
            .setTrailerUrl("https://www.youtube.com/embed/7M0fKoXuQxc?si=tgtpefPpcwUfI-z1")
            .setSynopsis("Chico Bento embarca em uma aventura mágica para salvar a goiabeira mais especial de sua vila.")
            .setAgeRating(AgeRating.GENERAL_AUDIENCE)
            .setGenres(new ArrayList<>(List.of(animationGenre, comedyGenre, familyGenre)))
            .setStatus(MovieStatus.NOW_PLAYING);
        movieRepository.save(movieChicoBento);

        Movie movieMoana2 = new Movie()
            .setTitle("MOANA 2")
            .setDurationInSeconds(7200)
            .setCoverUrl("/Images/moana-2.jpg")
            .setTrailerUrl("https://www.youtube.com/watch?v=lqMJQa3JhZU")
            .setSynopsis("Moana retorna ao oceano em uma nova aventura épica para salvar seu povo.")
            .setAgeRating(AgeRating.GENERAL_AUDIENCE)
            .setGenres(new ArrayList<>(List.of(animationGenre, adventureGenre, familyGenre)))
            .setStatus(MovieStatus.NOW_PLAYING);
        movieRepository.save(movieMoana2);

        Movie movieWicked = new Movie()
            .setTitle("WICKED")
            .setDurationInSeconds(9600)
            .setCoverUrl("/Images/wicked.jpg")
            .setTrailerUrl("https://www.youtube.com/watch?v=6COmYeLsz4c")
            .setSynopsis("A história não contada das bruxas de Oz, antes de Dorothy chegar.")
            .setAgeRating(AgeRating.TWELVE_YEARS)
            .setGenres(new ArrayList<>(List.of(fantasyGenre, musicalGenre)))
            .setStatus(MovieStatus.NOW_PLAYING);
        movieRepository.save(movieWicked);

        Movie movieGodcomputer = new Movie()
                .setTitle("The Godcomputer")
                .setSynopsis("In a future where an AI governs the world, a small group of rebels attempts to overthrow it.")
                .setDurationInSeconds(8100)
                .setAgeRating(AgeRating.SIXTEEN_YEARS)
                .setGenres(new ArrayList<>(List.of(scifiGenre, dramaGenre)))
                .setCoverUrl("https://image.tmdb.org/t/p/w500/9gk7adHYeDvHkCSEqAvQNLV5Uge.jpg")
                .setTrailerUrl("https://www.youtube.com/watch?v=GV01B5kVsC0")
                .setStatus(MovieStatus.NOW_PLAYING);
        movieRepository.save(movieGodcomputer);


        Movie movieHillHouse = new Movie()
            .setTitle("The Haunting of Hill House")
            .setSynopsis("A family is confronted with haunting memories of their old home and the terrifying events that drove them from it.")
            .setDurationInSeconds(7800)
            .setAgeRating(AgeRating.EIGHTEEN_YEARS)
            .setGenres(new ArrayList<>(List.of(horrorGenre, dramaGenre)))
            .setCoverUrl("https://picsum.photos/200/300?random=4")
            .setTrailerUrl("https://www.youtube.com/watch?v=3eqxXqJDmcY")
            .setStatus(MovieStatus.NOW_PLAYING);
        movieRepository.save(movieHillHouse);
        movieRepository.save(movieHillHouse);

        Movie movieMatrix = new Movie()
            .setTitle("Matrix Resurrections")
            .setSynopsis("Neo retorna à Matrix, enfrentando uma nova ameaça enquanto tenta desvendar os mistérios de sua existência e reencontrar Trinity.")
            .setDurationInSeconds(8880)
            .setAgeRating(AgeRating.FOURTEEN_YEARS)
            .setGenres(new ArrayList<>(List.of(actionGenre, scifiGenre)))
            .setCoverUrl("https://image.tmdb.org/t/p/w500/8c4a8kE7PizaGQQnditMmI1xbRp.jpg")
            .setTrailerUrl("https://www.youtube.com/watch?v=9ix7TUGVYIo")
            .setStatus(MovieStatus.NOW_PLAYING);
        movieRepository.save(movieMatrix);

        Movie movieInterstellar = new Movie()
            .setTitle("Interstellar")
            .setSynopsis("Um grupo de exploradores viaja através de um buraco de minhoca no espaço, em uma tentativa de garantir a sobrevivência da humanidade.")
            .setDurationInSeconds(10140)
            .setAgeRating(AgeRating.TEN_YEARS)
            .setGenres(new ArrayList<>(List.of(scifiGenre, adventureGenre, dramaGenre)))
            .setCoverUrl("https://image.tmdb.org/t/p/w500/gEU2QniE6E77NI6lCU6MxlNBvIx.jpg")
            .setTrailerUrl("https://www.youtube.com/watch?v=zSWdZVtXT7E")
            .setStatus(MovieStatus.NOW_PLAYING);
        movieRepository.save(movieInterstellar);

        // ============================================================
        // 3. MOVIES - COMING SOON
        // ============================================================
        Movie movieShrek5 = new Movie()
            .setTitle("SHREK 5")
            .setDurationInSeconds(0)
            .setCoverUrl("/Images/shrek-5.jpg")
            .setTrailerUrl("https://www.youtube.com/watch?v=W37DlG1i61s")
            .setSynopsis("O ogro mais amado do cinema retorna em uma nova aventura hilária.")
            .setGenres(new ArrayList<>(List.of(animationGenre, comedyGenre, familyGenre)))
            .setStatus(MovieStatus.COMING_SOON);
        movieRepository.save(movieShrek5);

        Movie movieCapitaoAmerica = new Movie()
            .setTitle("CAPITÃO AMÉRICA: ADMIRÁVEL MUNDO NOVO")
            .setDurationInSeconds(0)
            .setCoverUrl("/Images/capitao-america.jpg")
            .setTrailerUrl("https://www.youtube.com/watch?v=U7JG6FMoEdM")
            .setSynopsis("Sam Wilson assume o manto do Capitão América e enfrenta novos desafios.")
            .setGenres(new ArrayList<>(List.of(actionGenre, scifiGenre)))
            .setStatus(MovieStatus.COMING_SOON);
        movieRepository.save(movieCapitaoAmerica);

        Movie movieCoringa2 = new Movie()
            .setTitle("CORINGA 2: FOLIE À DEUX")
            .setDurationInSeconds(0)
            .setCoverUrl("/Images/coringa-2.jpg")
            .setTrailerUrl("https://www.youtube.com/watch?v=kD6LoK5GcGw")
            .setSynopsis("Arthur Fleck encontra o amor enquanto enfrenta as consequências de seus atos.")
            .setGenres(new ArrayList<>(List.of(crimeGenre, dramaGenre, musicalGenre)))
            .setStatus(MovieStatus.COMING_SOON);
        movieRepository.save(movieCoringa2);

        // ============================================================
        // 4. USERS (Client and Manager)
        // ============================================================
        Client client = new Client()
            .setEmail("cliente@teste.com")
            .setPassword(passwordEncoder.encode("123456"))
            .setCpf("123.456.789-00")
            .setName("Cliente de Teste")
            .setPhoneNumber("(16) 99999-9999")
            .setGender(Gender.MALE)
            .setBirthDate(LocalDate.now().minusYears(25))
            .setTickets(new ArrayList<>())
            .setRole(Role.CLIENT);
        Client savedClient = clientRepository.save(client);

        Manager manager = new Manager();
        manager.setEmail("manager@teste.com");
        manager.setRole(Role.MANAGER);
        manager.setPassword(passwordEncoder.encode("123456"));
        manager.setCpf("987.654.321-00");
        manager.setBirthDate(LocalDate.now().minusYears(35));
        Manager savedManager = managerRepository.save(manager);

        // ============================================================
        // 5. THEATER 1 - Iguatemi São Carlos
        // ============================================================
        Address addressIguatemi = new Address()
            .setZipCode("13571-410")
            .setStreet("Rodovia Washington Luís")
            .setNumber("KM 235")
            .setCity("São Carlos")
            .setNeighborhood("Parque Faber Castell I")
            .setState("SP")
            .setCountry("Brasil")
            .setLatitude(-22.0087)
            .setLongitude(-47.8909);
        Theater theaterIguatemi = new Theater()
            .setName("Cinépolis Iguatemi São Carlos")
            .setLogoUrl("https://www.cinepolis.com.br/assets/images/logo.png")
            .setRooms(new ArrayList<>())
            .setAddress(addressIguatemi)
            .setManagers(new ArrayList<>());
        Theater savedIguatemi = theaterRepository.save(theaterIguatemi);

        // Rooms for Iguatemi
        Room roomIguatemi1 = new Room()
            .setName("Sala 1")
            .setRoomType(RoomType.STANDARD)
            .setTheater(savedIguatemi)
            .setRows(new ArrayList<>())
            .setSessions(new ArrayList<>());
        Room savedRoomIguatemi1 = roomRepository.save(roomIguatemi1);

        Room roomIguatemi2 = new Room()
            .setName("Sala VIP")
            .setRoomType(RoomType.SPECIAL)
            .setTheater(savedIguatemi)
            .setRows(new ArrayList<>())
            .setSessions(new ArrayList<>());
        Room savedRoomIguatemi2 = roomRepository.save(roomIguatemi2);

        // Rows and Seats for Iguatemi Sala 1 (5 rows x 10 seats)
        createSeatsForRoom(savedRoomIguatemi1, 5, 10);

        // Rows and Seats for Iguatemi Sala VIP (4 rows x 8 seats)
        createSeatsForRoom(savedRoomIguatemi2, 4, 8);

        // ============================================================
        // 6. THEATER 2 - Cine São Carlos
        // ============================================================
        Address addressCineSC = new Address()
            .setZipCode("13560-648")
            .setStreet("Avenida São Carlos")
            .setNumber("1800")
            .setCity("São Carlos")
            .setNeighborhood("Centro")
            .setState("SP")
            .setCountry("Brasil")
            .setLatitude(-22.0174)
            .setLongitude(-47.8901);
        Theater theaterCineSC = new Theater()
            .setName("Cine São Carlos")
            .setLogoUrl("https://example.com/cinesaocarlos-logo.png")
            .setRooms(new ArrayList<>())
            .setAddress(addressCineSC)
            .setManagers(new ArrayList<>());
        Theater savedCineSC = theaterRepository.save(theaterCineSC);

        // Rooms for Cine São Carlos
        Room roomCineSC1 = new Room()
            .setName("Sala 1")
            .setRoomType(RoomType.STANDARD)
            .setTheater(savedCineSC)
            .setRows(new ArrayList<>())
            .setSessions(new ArrayList<>());
        Room savedRoomCineSC1 = roomRepository.save(roomCineSC1);

        Room roomCineSC2 = new Room()
            .setName("Sala 2")
            .setRoomType(RoomType.STANDARD)
            .setTheater(savedCineSC)
            .setRows(new ArrayList<>())
            .setSessions(new ArrayList<>());
        Room savedRoomCineSC2 = roomRepository.save(roomCineSC2);

        // Rows and Seats for Cine São Carlos Sala 1 (6 rows x 12 seats)
        createSeatsForRoom(savedRoomCineSC1, 6, 12);

        // Rows and Seats for Cine São Carlos Sala 2 (5 rows x 10 seats)
        createSeatsForRoom(savedRoomCineSC2, 5, 10);

        // ============================================================
        // 7. SESSIONS - Cinépolis Iguatemi São Carlos
        // ============================================================
        // Ainda Estou Aqui - Iguatemi
        Session sessionIguatemi1 = new Session()
            .setFormat(Format.TWO_D)
            .setDate(LocalDateTime.now(ZoneId.of("America/Sao_Paulo")).plusDays(1).withHour(14).withMinute(0))
            .setSubtitle(Subtitle.DUBBED)
            .setPriceInCents(3500)
            .setRoom(savedRoomIguatemi1)
            .setMovie(movieAindaEstouAqui)
            .setTickets(new ArrayList<>());
        Session savedSession = sessionRepository.save(sessionIguatemi1);

        // Chico Bento - Iguatemi
        Session sessionIguatemi2 = new Session()
            .setFormat(Format.TWO_D)
            .setDate(LocalDateTime.now(ZoneId.of("America/Sao_Paulo")).plusDays(1).withHour(16).withMinute(0))
            .setSubtitle(Subtitle.DUBBED)
            .setPriceInCents(3500)
            .setRoom(savedRoomIguatemi1)
            .setMovie(movieChicoBento)
            .setTickets(new ArrayList<>());
        sessionRepository.save(sessionIguatemi2);

        // Wicked - Iguatemi
        Session sessionIguatemi3 = new Session()
            .setFormat(Format.TWO_D)
            .setDate(LocalDateTime.now(ZoneId.of("America/Sao_Paulo")).plusDays(1).withHour(19).withMinute(0))
            .setSubtitle(Subtitle.SUBTITLED)
            .setPriceInCents(4800)
            .setRoom(savedRoomIguatemi1)
            .setMovie(movieWicked)
            .setTickets(new ArrayList<>());
        sessionRepository.save(sessionIguatemi3);

        // Godcomputer - Iguatemi
        Session sessionIguatemi4 = new Session()
            .setFormat(Format.TWO_D)
            .setDate(LocalDateTime.now(ZoneId.of("America/Sao_Paulo")).plusDays(1).withHour(21).withMinute(30))
            .setSubtitle(Subtitle.SUBTITLED)
            .setPriceInCents(4000)
            .setRoom(savedRoomIguatemi2)
            .setMovie(movieGodcomputer)
            .setTickets(new ArrayList<>());
        sessionRepository.save(sessionIguatemi4);

        // Hill House - Iguatemi
        Session sessionIguatemi5 = new Session()
            .setFormat(Format.TWO_D)
            .setDate(LocalDateTime.now(ZoneId.of("America/Sao_Paulo")).plusDays(2).withHour(22).withMinute(0))
            .setSubtitle(Subtitle.ORIGINAL)
            .setPriceInCents(4500)
            .setRoom(savedRoomIguatemi2)
            .setMovie(movieHillHouse)
            .setTickets(new ArrayList<>());
        sessionRepository.save(sessionIguatemi5);

        // Matrix - Iguatemi
        Session sessionIguatemi6 = new Session()
            .setFormat(Format.TWO_D)
            .setDate(LocalDateTime.now(ZoneId.of("America/Sao_Paulo")).plusDays(2).withHour(14).withMinute(0))
            .setSubtitle(Subtitle.DUBBED)
            .setPriceInCents(3800)
            .setRoom(savedRoomIguatemi1)
            .setMovie(movieMatrix)
            .setTickets(new ArrayList<>());
        sessionRepository.save(sessionIguatemi6);

        Session sessionIguatemi7 = new Session()
            .setFormat(Format.TWO_D)
            .setDate(LocalDateTime.now(ZoneId.of("America/Sao_Paulo")).plusDays(2).withHour(17).withMinute(0))
            .setSubtitle(Subtitle.SUBTITLED)
            .setPriceInCents(3800)
            .setRoom(savedRoomIguatemi1)
            .setMovie(movieMatrix)
            .setTickets(new ArrayList<>());
        sessionRepository.save(sessionIguatemi7);

        // Interstellar - Iguatemi
        Session sessionIguatemi8 = new Session()
            .setFormat(Format.TWO_D)
            .setDate(LocalDateTime.now(ZoneId.of("America/Sao_Paulo")).plusDays(3).withHour(15).withMinute(0))
            .setSubtitle(Subtitle.DUBBED)
            .setPriceInCents(4200)
            .setRoom(savedRoomIguatemi2)
            .setMovie(movieInterstellar)
            .setTickets(new ArrayList<>());
        sessionRepository.save(sessionIguatemi8);

        Session sessionIguatemi9 = new Session()
            .setFormat(Format.TWO_D)
            .setDate(LocalDateTime.now(ZoneId.of("America/Sao_Paulo")).plusDays(3).withHour(19).withMinute(30))
            .setSubtitle(Subtitle.SUBTITLED)
            .setPriceInCents(4200)
            .setRoom(savedRoomIguatemi2)
            .setMovie(movieInterstellar)
            .setTickets(new ArrayList<>());
        sessionRepository.save(sessionIguatemi9);

        // ============================================================
        // 8. SESSIONS - Cine São Carlos
        // ============================================================
        // Ainda Estou Aqui - Cine SC
        Session sessionCineSC1 = new Session()
            .setFormat(Format.TWO_D)
            .setDate(LocalDateTime.now(ZoneId.of("America/Sao_Paulo")).plusDays(1).withHour(14).withMinute(30))
            .setSubtitle(Subtitle.ORIGINAL)
            .setPriceInCents(2800)
            .setRoom(savedRoomCineSC1)
            .setMovie(movieAindaEstouAqui)
            .setTickets(new ArrayList<>());
        sessionRepository.save(sessionCineSC1);

        // Chico Bento - Cine SC
        Session sessionCineSC2 = new Session()
            .setFormat(Format.TWO_D)
            .setDate(LocalDateTime.now(ZoneId.of("America/Sao_Paulo")).plusDays(1).withHour(15).withMinute(0))
            .setSubtitle(Subtitle.DUBBED)
            .setPriceInCents(2500)
            .setRoom(savedRoomCineSC2)
            .setMovie(movieChicoBento)
            .setTickets(new ArrayList<>());
        sessionRepository.save(sessionCineSC2);

        // Moana 2 - ONLY at Cine SC
        Session sessionCineSC3 = new Session()
            .setFormat(Format.TWO_D)
            .setDate(LocalDateTime.now(ZoneId.of("America/Sao_Paulo")).plusDays(1).withHour(17).withMinute(0))
            .setSubtitle(Subtitle.DUBBED)
            .setPriceInCents(3000)
            .setRoom(savedRoomCineSC1)
            .setMovie(movieMoana2)
            .setTickets(new ArrayList<>());
        sessionRepository.save(sessionCineSC3);

        Session sessionCineSC4 = new Session()
            .setFormat(Format.THREE_D)
            .setDate(LocalDateTime.now(ZoneId.of("America/Sao_Paulo")).plusDays(2).withHour(15).withMinute(0))
            .setSubtitle(Subtitle.DUBBED)
            .setPriceInCents(4500)
            .setRoom(savedRoomCineSC1)
            .setMovie(movieMoana2)
            .setTickets(new ArrayList<>());
        sessionRepository.save(sessionCineSC4);

        // Wicked - Cine SC
        Session sessionCineSC5 = new Session()
            .setFormat(Format.TWO_D)
            .setDate(LocalDateTime.now(ZoneId.of("America/Sao_Paulo")).plusDays(1).withHour(19).withMinute(30))
            .setSubtitle(Subtitle.SUBTITLED)
            .setPriceInCents(4000)
            .setRoom(savedRoomCineSC2)
            .setMovie(movieWicked)
            .setTickets(new ArrayList<>());
        sessionRepository.save(sessionCineSC5);

        // Godcomputer - Cine SC
        Session sessionCineSC6 = new Session()
            .setFormat(Format.TWO_D)
            .setDate(LocalDateTime.now(ZoneId.of("America/Sao_Paulo")).plusDays(1).withHour(21).withMinute(0))
            .setSubtitle(Subtitle.SUBTITLED)
            .setPriceInCents(3500)
            .setRoom(savedRoomCineSC1)
            .setMovie(movieGodcomputer)
            .setTickets(new ArrayList<>());
        sessionRepository.save(sessionCineSC6);

        // Hill House - Cine SC
        Session sessionCineSC7 = new Session()
            .setFormat(Format.TWO_D)
            .setDate(LocalDateTime.now(ZoneId.of("America/Sao_Paulo")).plusDays(2).withHour(22).withMinute(30))
            .setSubtitle(Subtitle.ORIGINAL)
            .setPriceInCents(4000)
            .setRoom(savedRoomCineSC2)
            .setMovie(movieHillHouse)
            .setTickets(new ArrayList<>());
        sessionRepository.save(sessionCineSC7);

        // Matrix - Cine SC
        Session sessionCineSC8 = new Session()
            .setFormat(Format.TWO_D)
            .setDate(LocalDateTime.now(ZoneId.of("America/Sao_Paulo")).plusDays(2).withHour(16).withMinute(0))
            .setSubtitle(Subtitle.DUBBED)
            .setPriceInCents(3200)
            .setRoom(savedRoomCineSC1)
            .setMovie(movieMatrix)
            .setTickets(new ArrayList<>());
        sessionRepository.save(sessionCineSC8);

        Session sessionCineSC9 = new Session()
            .setFormat(Format.TWO_D)
            .setDate(LocalDateTime.now(ZoneId.of("America/Sao_Paulo")).plusDays(2).withHour(19).withMinute(0))
            .setSubtitle(Subtitle.SUBTITLED)
            .setPriceInCents(3200)
            .setRoom(savedRoomCineSC2)
            .setMovie(movieMatrix)
            .setTickets(new ArrayList<>());
        sessionRepository.save(sessionCineSC9);

        // Interstellar - Cine SC
        Session sessionCineSC10 = new Session()
            .setFormat(Format.TWO_D)
            .setDate(LocalDateTime.now(ZoneId.of("America/Sao_Paulo")).plusDays(3).withHour(14).withMinute(0))
            .setSubtitle(Subtitle.DUBBED)
            .setPriceInCents(3800)
            .setRoom(savedRoomCineSC1)
            .setMovie(movieInterstellar)
            .setTickets(new ArrayList<>());
        sessionRepository.save(sessionCineSC10);

        Session sessionCineSC11 = new Session()
            .setFormat(Format.TWO_D)
            .setDate(LocalDateTime.now(ZoneId.of("America/Sao_Paulo")).plusDays(3).withHour(18).withMinute(30))
            .setSubtitle(Subtitle.SUBTITLED)
            .setPriceInCents(3800)
            .setRoom(savedRoomCineSC2)
            .setMovie(movieInterstellar)
            .setTickets(new ArrayList<>());
        sessionRepository.save(sessionCineSC11);

        System.out.println("\n------------------------------------------------------------");
        System.out.println("Seeding Finalizado!");
        System.out.println("Cinemas criados:");
        System.out.println(">>> Cinépolis Iguatemi São Carlos (ID: " + savedIguatemi.getId() + ")");
        System.out.println(">>> Cine São Carlos (ID: " + savedCineSC.getId() + ")");
        System.out.println("\nUse os seguintes IDs para testes:");
        System.out.println(">>> clientId: " + savedClient.getId());
        System.out.println(">>> managerId: " + savedManager.getId());
        System.out.println(">>> sessionId: " + savedSession.getId());
        System.out.println("------------------------------------------------------------\n");
    }

    private void createSeatsForRoom(Room room, int numRows, int seatsPerRow) {
        char[] rowLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
        
        for (int r = 0; r < numRows; r++) {
            Row row = new Row()
                .setLetter(rowLetters[r])
                .setRoom(room)
                .setSeats(new ArrayList<>());
            rowRepository.save(row);

            List<Seat> seats = new ArrayList<>();
            for (int s = 1; s <= seatsPerRow; s++) {
                SeatType seatType = SeatType.STANDARD;
                // First row gets some PLUS_SIZE seats
                if (r == 0 && s <= 2) {
                    seatType = SeatType.PLUS_SIZE;
                }
                // Middle seats in first row are WHEELCHAIR
                if (r == 0 && s >= seatsPerRow - 1) {
                    seatType = SeatType.WHEELCHAIR;
                }

                Seat seat = new Seat()
                    .setNumber(s)
                    .setRow(row)
                    .setSeatType(seatType)
                    .setTickets(new ArrayList<>());
                seats.add(seat);
            }
            seatRepository.saveAll(seats);
        }
    }
}
