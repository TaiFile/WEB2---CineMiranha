import { Format, Subtitle } from "./enums";
import { Movie } from "./Movie";

export interface Session {
    id: number;
    format: Format;
    date: string;
    subtitle: Subtitle;
    priceInCents: number;
    room: string;
    movie: Movie;
    tickets: string[];
}
