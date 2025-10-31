package br.ufscar.pooa.cinema_api.domain;

import java.util.Objects;

public class Address {
	private String zipCode;
	private String street;
	private String number;
	private String complement;
	private String city;
	private String neighborhood;
	private String state;
	private String country;

	public Address() {
	}

	public Address(String zipCode, String street, String number, String complement, String city, String neighborhood, String state, String country) {
		this.zipCode = zipCode;
		this.street = street;
		this.number = number;
		this.complement = complement;
		this.city = city;
		this.neighborhood = neighborhood;
		this.state = state;
		this.country = country;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getNeighborhood() {
		return neighborhood;
	}

	public void setNeighborhood(String neighborhood) {
		this.neighborhood = neighborhood;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getComplement() {
		return complement;
	}

	public void setComplement(String complement) {
		this.complement = complement;
	}

	@Override
	public boolean equals(Object o) {
		if (o == null || getClass() != o.getClass()) return false;
		Address address = (Address) o;
		return Objects.equals(country, address.country) && Objects.equals(state, address.state) && Objects.equals(city, address.city) && Objects.equals(zipCode, address.zipCode) && Objects.equals(neighborhood, address.neighborhood) && Objects.equals(street, address.street) && Objects.equals(number, address.number) && Objects.equals(complement, address.complement);
	}

	@Override
	public int hashCode() {
		return Objects.hash(country, state, city, zipCode, neighborhood, street, number, complement);
	}

	@Override
	public String toString() {
		return "Address{" +
				"zipCode='" + zipCode + '\'' +
				", street='" + street + '\'' +
				", number='" + number + '\'' +
				", complement='" + complement + '\'' +
				", city='" + city + '\'' +
				", neighborhood='" + neighborhood + '\'' +
				", state='" + state + '\'' +
				", country='" + country + '\'' +
				'}';
	}
}