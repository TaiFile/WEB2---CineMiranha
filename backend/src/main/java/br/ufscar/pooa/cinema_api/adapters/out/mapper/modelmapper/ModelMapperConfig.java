package br.ufscar.pooa.cinema_api.adapters.out.mapper.modelmapper;

import br.ufscar.pooa.cinema_api.adapters.out.persistence.entities.*;
import br.ufscar.pooa.cinema_api.domain.*;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        // =====================================================================================
        //  ENTITY -> DOMAIN MAPPINGS
        //  Use PropertyMap to prevent configuration errors when skipping properties
        //  in objects with deep, implicit nested mappings.
        // =====================================================================================

        // These have no cycles or are roots of graphs we want to map fully
        modelMapper.createTypeMap(SessionEntity.class, Session.class);
        modelMapper.createTypeMap(ClientEntity.class, Client.class);
        modelMapper.createTypeMap(TheaterEntity.class, Theater.class);
        modelMapper.createTypeMap(AddressEntity.class, Address.class);

        // Use PropertyMap to break cycles by skipping the parent back-reference
        modelMapper.addMappings(new PropertyMap<GenreEntity, Genre>() {
            protected void configure() { skip(destination.getMovies()); }
        });
        modelMapper.addMappings(new PropertyMap<MovieEntity, Movie>() {
            protected void configure() { skip(destination.getSessions()); }
        });
        modelMapper.addMappings(new PropertyMap<RoomEntity, Room>() {
            protected void configure() { skip(destination.getSessions()); }
        });
        modelMapper.addMappings(new PropertyMap<RowEntity, Row>() {
            protected void configure() { skip(destination.getRoom()); }
        });
        modelMapper.addMappings(new PropertyMap<SeatEntity, Seat>() {
            protected void configure() { skip(destination.getRow()); }
        });
        modelMapper.addMappings(new PropertyMap<TicketEntity, Ticket>() {
            protected void configure() {
                skip(destination.getClient());
                skip(destination.getSession());
            }
        });

        // =====================================================================================
        //  DOMAIN -> ENTITY MAPPINGS
        //  Goal: Prevent detached entity errors by skipping collections.
        // =====================================================================================

        modelMapper.createTypeMap(Client.class, ClientEntity.class)
                .addMappings(mapper -> mapper.skip(ClientEntity::setTickets));
        modelMapper.createTypeMap(Genre.class, GenreEntity.class)
                .addMappings(mapper -> mapper.skip(GenreEntity::setMovies));
        modelMapper.createTypeMap(Movie.class, MovieEntity.class)
                .addMappings(mapper -> {
                    mapper.skip(MovieEntity::setGenres);
                    mapper.skip(MovieEntity::setSessions);
                });
        modelMapper.createTypeMap(Room.class, RoomEntity.class)
                .addMappings(mapper -> {
                    mapper.skip(RoomEntity::setRows);
                    mapper.skip(RoomEntity::setSessions);
                });
        modelMapper.createTypeMap(Row.class, RowEntity.class)
                .addMappings(mapper -> mapper.skip(RowEntity::setSeats));
        modelMapper.createTypeMap(Session.class, SessionEntity.class)
                .addMappings(mapper -> mapper.skip(SessionEntity::setTickets));
        modelMapper.createTypeMap(Theater.class, TheaterEntity.class);

        // No collections to skip on these
        modelMapper.createTypeMap(Seat.class, SeatEntity.class);
        modelMapper.createTypeMap(Ticket.class, TicketEntity.class);
        modelMapper.createTypeMap(Address.class, AddressEntity.class);

        return modelMapper;
    }
}
