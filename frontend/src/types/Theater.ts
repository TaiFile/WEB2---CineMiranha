export interface Theater {
  id: number;
  name: string;
  logoUrl: string;
  address: string;
  addressId: number;
  latitude?: number;
  longitude?: number;
}

export interface Address {
  id: number;
  street: string;
  number: string;
  neighborhood: string;
  city: string;
  complement?: string;
  country: string;
  state: string;
  zipCode: string;
  latitude?: number;
  longitude?: number;
}

    // @OneToOne(cascade = CascadeType.ALL)
    // @JoinColumn(name = "address_id", unique = true)
    // private Address address;

    // @OneToMany(mappedBy = "theater", cascade = CascadeType.ALL)
    // private List<Room> rooms = new ArrayList<>();

    // @OneToMany(mappedBy = "theater")
    // private List<Manager> managers = new ArrayList<>();

    // @Transient
    // private Double distance;