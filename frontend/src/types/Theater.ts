export interface Address {
  id: number;
  street: string;
  number: string;
  neighborhood: string;
  city: string;
  state: string;
  zipCode: string;
  latitude: number;
  longitude: number;
}

export interface Theater {
  id: number;
  name: string;
  logoUrl: string;
  address: Address;
  distance?: number;
}
