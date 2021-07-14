package edu.epam.webproject.entity;

public class Address extends Entity{
    private long id;
    private String country;
    private String city;
    private String street;
    private int houseNumber;
    private int apartmentNumber;

    public Address(long id, String country, String city, String street, int houseNumber, int apartmentNumber) {
        this.id = id;
        this.country = country;
        this.city = city;
        this.street = street;
        this.houseNumber = houseNumber;
        this.apartmentNumber = apartmentNumber;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(int houseNumber) {
        this.houseNumber = houseNumber;
    }

    public int getApartmentNumber() {
        return apartmentNumber;
    }

    public void setApartmentNumber(int apartmentNumber) {
        this.apartmentNumber = apartmentNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Address address = (Address) o;
        return id == address.id &&
                houseNumber == address.houseNumber &&
                apartmentNumber == address.apartmentNumber &&
                country.equals(address.country) &&
                city.equals(address.city) &&
                street.equals(address.street);
    }

    @Override
    public int hashCode() {
        return Long.hashCode(id)
                + country.hashCode()
                + city.hashCode()
                + street.hashCode()
                + Integer.hashCode(houseNumber)
                + Integer.hashCode(apartmentNumber);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Address { ");
        builder.append("id = ").append(id).append(", ");
        builder.append("country = ").append(country).append(", ");
        builder.append("city = ").append(city).append(", ");
        builder.append("street = ").append(street).append(", ");
        builder.append("house number = ").append(houseNumber).append(", ");
        builder.append("apartmentNumber = ").append(apartmentNumber).append(" ");
        builder.append("}");
        return builder.toString();
    }
}
