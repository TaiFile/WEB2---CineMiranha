package br.ufscar.pooa.cinema_api.features.theaters;

import br.ufscar.pooa.cinema_api.features.theaters.IRoomMapper;
import br.ufscar.pooa.cinema_api.features.theaters.IRowMapper;
import br.ufscar.pooa.cinema_api.features.theaters.RegisterRoomRequestDTO;
import br.ufscar.pooa.cinema_api.features.theaters.RoomResponseDTO;
import br.ufscar.pooa.cinema_api.features.theaters.IRegisterRoomUseCase;
import br.ufscar.pooa.cinema_api.features._shared.gateways.repository.IRoomRepository;
import br.ufscar.pooa.cinema_api.features._shared.gateways.repository.ITheaterRepository;
import br.ufscar.pooa.cinema_api.domain.entities.Room;
import br.ufscar.pooa.cinema_api.domain.entities.Row;
import br.ufscar.pooa.cinema_api.domain.entities.Seat;
import br.ufscar.pooa.cinema_api.domain.entities.Theater;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RegisterRoomUseCase implements IRegisterRoomUseCase {
    private final IRoomRepository repository;
    private final ITheaterRepository theaterRepository;
    private final IRoomMapper IRoomMapper;
    private final IRowMapper IRowMapper;


    public RegisterRoomUseCase(IRoomRepository repository, ITheaterRepository theaterRepository, IRoomMapper IRoomMapper, IRowMapper IRowMapper) {
        this.repository = repository;
        this.theaterRepository = theaterRepository;
        this.IRoomMapper = IRoomMapper;
        this.IRowMapper = IRowMapper;
    }

    @Override
    public RoomResponseDTO execute(RegisterRoomRequestDTO requestDTO) {
        Theater theater = theaterRepository.findById(requestDTO.getTheaterId())
                .orElseThrow(() -> new IllegalArgumentException("Theater not found."));

        Set<Row> rows = requestDTO.getRows().stream()
                .map(rowDTO -> {
                    Row row = new Row();
                    row.setLetter(rowDTO.getLetter());
                    Set<Seat> seats = rowDTO.getSeats().stream()
                            .map(seatDTO -> {
                                Seat seat = new Seat();
                                seat.setNumber(seatDTO.getNumber());
                                seat.setSeatType(seatDTO.getSeatType());
                                seat.setRow(row);
                                return seat;
                            })
                            .collect(Collectors.toSet());
                    row.setSeats(seats);
                    row.setRoom(null); // The room is set later
                    return row;
                })
                .collect(Collectors.toSet());

        Room room = new Room();
        room.setName(requestDTO.getName());
        room.setRoomType(requestDTO.getRoomType());
        room.setTheater(theater);
        room.setRows(rows);

        for (Row row : rows) {
            row.setRoom(room);
        }

        Room savedRoom = repository.save(room);

        return IRoomMapper.toRoomResponseDTO(savedRoom);
    }
}
