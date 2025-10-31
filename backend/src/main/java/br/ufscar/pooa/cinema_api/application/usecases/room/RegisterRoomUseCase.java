package br.ufscar.pooa.cinema_api.application.usecases.room;

import br.ufscar.pooa.cinema_api.application.mappers.IRoomMapper;
import br.ufscar.pooa.cinema_api.application.mappers.IRowMapper;
import br.ufscar.pooa.cinema_api.application.dtos.room.RegisterRoomRequestDTO;
import br.ufscar.pooa.cinema_api.application.dtos.room.RoomResponseDTO;
import br.ufscar.pooa.cinema_api.application.ports.in.IRegisterRoomUseCase;
import br.ufscar.pooa.cinema_api.application.ports.out.repository.IRoomRepository;
import br.ufscar.pooa.cinema_api.application.ports.out.repository.ITheaterRepository;
import br.ufscar.pooa.cinema_api.domain.Room;
import br.ufscar.pooa.cinema_api.domain.Row;
import br.ufscar.pooa.cinema_api.domain.Seat;
import br.ufscar.pooa.cinema_api.domain.Theater;
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
