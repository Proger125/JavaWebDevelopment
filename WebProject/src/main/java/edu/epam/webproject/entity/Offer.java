package edu.epam.webproject.entity;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * Class designed to store information about offer
 */
public class Offer extends Entity{
    private long id;
    private User owner;
    private Address address;
    private BigInteger pricePerDay;
    private String description;
    private List<String> photos;
    private OfferStatus status;
    public Offer(){
        this.photos = new ArrayList<>();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public BigInteger getPricePerDay() {
        return pricePerDay;
    }

    public void setPricePerDay(BigInteger pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public OfferStatus getStatus() {
        return status;
    }

    public void setStatus(OfferStatus status) {
        this.status = status;
    }

    public void addPhoto(String photo){
        photos.add(photo);
    }

    public List<String> getPhotos() {
        return photos;
    }

    public void setPhotos(List<String> photos) {
        this.photos = photos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Offer offer = (Offer) o;
        return id == offer.id &&
                owner.equals(offer.owner) &&
                address.equals(offer.address) &&
                pricePerDay.equals(offer.pricePerDay) &&
                description.equals(offer.description) &&
                status == offer.status;
    }

    @Override
    public int hashCode() {
        return Long.hashCode(id)
                + owner.hashCode()
                + address.hashCode()
                + pricePerDay.hashCode()
                + description.hashCode()
                + status.hashCode();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Offer { ");
        builder.append("id = ").append(id).append(", ");
        builder.append("owner = ").append(owner).append(", ");
        builder.append("address = ").append(address).append(", ");
        builder.append("price per day = ").append(pricePerDay).append(", ");
        builder.append("description = ").append(description).append(", ");
        builder.append("status = ").append(status).append(" ");
        builder.append("}");
        return builder.toString();
    }

    public enum OfferStatus {
        ACTIVE(1),
        INACTIVE(2);
        private final int value;
        private OfferStatus(int value){
            this.value = value;
        }
        public int getValue(){
            return value;
        }
    }
}
