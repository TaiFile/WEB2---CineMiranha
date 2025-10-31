package br.ufscar.pooa.cinema_api.application.usecases.room;

import br.ufscar.pooa.cinema_api.application.dtos.room.RegisterRoomRequestDTO;
import br.ufscar.pooa.cinema_api.application.dtos.room.RoomResponseDTO;
import br.ufscar.pooa.cinema_api.application.ports.in.IRegisterRoomUseCase;
import br.ufscar.pooa.cinema_api.application.ports.out.mapper.IObjectMapper;
import br.ufscar.pooa.cinema_api.application.ports.out.repository.IRoomRepository;
import br.ufscar.pooa.cinema_api.application.ports.out.repository.ITheaterRepository;
import br.ufscar.pooa.cinema_api.domain.Room;
import br.ufscar.pooa.cinema_api.domain.Row;
import br.ufscar.pooa.cinema_api.domain.Seat;
import br.ufscar.pooa.cinema_api.domain.Theater;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegisterRoomUseCase implements IRegisterRoomUseCase {
    private final IRoomRepository repository;
    private final ITheaterRepository theaterRepository;
    private final IObjectMapper mapper;

    public RegisterRoomUseCase(IRoomRepository repository, ITheaterRepository theaterRepository, IObjectMapper mapper) {
        this.repository = repository;
        this.theaterRepository = theaterRepository;
        this.mapper = mapper;
    }

    @Override
    public RoomResponseDTO execute(RegisterRoomRequestDTO requestDTO) {
        Theater theater = theaterRepository.findById(requestDTO.getTheaterId())
                .orElseThrow(() -> new IllegalArgumentException("Theater not found."));

        List<Row> rows = requestDTO.getRows().stream()
                .map(rowDTO -> {
                    List<Seat> seats = rowDTO.getSeats().stream()
                            .map(seatDTO -> mapper.parseObject(seatDTO, Seat.class))
                            .toList();

                    Row row = mapper.parseObject(rowDTO, Row.class);
                    row.setSeats(seats);

                    seats.forEach(seat -> seat.setRow(row));
                    return row;
                })
                .toList();

        Room room = new Room();
        room.setName(requestDTO.getName());
        room.setRoomType(requestDTO.getRoomType());
        room.setTheater(theater);
        room.setRows(rows);

        Room savedRoom = repository.save(room);

        return mapper.parseObject(savedRoom, RoomResponseDTO.class);
    }
}
