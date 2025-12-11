import api from "./axios";

export interface SessionResponse {
  id: number;
  roomId: number;
  movieId: number;
  format: string;
  date: string;
  subtitle: string;
  priceInCents: number;
}

export interface SeatDTO {
  id: number;
  number: string;
  seatType: string;
}

export interface RowDTO {
  id: number;
  letter: string;
  seats: SeatDTO[];
}

export interface RoomDetailResponse {
  id: number;
  name: string;
  roomType: string;
  theaterId: number;
  rows: RowDTO[];
}

export interface TicketResponse {
  id: number;
  seatId: number;
  sessionId: number;
  clientId: number;
}

export interface SessionDetailResponse {
  id: number;
  movieId: number;
  format: string;
  date: string;
  subtitle: string;
  priceInCents: number;
  room: RoomDetailResponse;
  tickets: TicketResponse[];
}

export const sessionService = {
  getSessionsByMovieId: async (movieId: number): Promise<SessionResponse[]> => {
    const response = await api.get<SessionResponse[]>(
      `/movies/${movieId}/sessions`
    );
    return response.data;
  },

  getSessionById: async (sessionId: number): Promise<SessionDetailResponse> => {
    const response = await api.get<SessionDetailResponse>(
      `/sessions/${sessionId}`
    );
    return response.data;
  },
};
