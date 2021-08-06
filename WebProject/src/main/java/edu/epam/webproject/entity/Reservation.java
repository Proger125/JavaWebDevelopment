package edu.epam.webproject.entity;

import java.math.BigInteger;
import java.util.Date;

public class Reservation extends Entity{
    private long id;
    private User tenant;
    private Offer offer;
    private Date arrivalDate;
    private Date departureDate;
    private BigInteger totalPrice;
    private String comment;
    private ReservationStatus status;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getTenant() {
        return tenant;
    }

    public void setTenant(User tenant) {
        this.tenant = tenant;
    }

    public Offer getOffer() {
        return offer;
    }

    public void setOffer(Offer offer) {
        this.offer = offer;
    }

    public Date getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(Date arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public Date getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }

    public BigInteger getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigInteger totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public ReservationStatus getStatus() {
        return status;
    }

    public void setStatus(ReservationStatus status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Reservation that = (Reservation) o;
        return id == that.id &&
                tenant.equals(that.tenant) &&
                offer.equals(that.offer) &&
                arrivalDate.equals(that.arrivalDate) &&
                departureDate.equals(that.departureDate) &&
                totalPrice.equals(that.totalPrice) &&
                comment.equals(that.comment) &&
                status == that.status;
    }

    @Override
    public int hashCode() {
        return Long.hashCode(id)
                + tenant.hashCode()
                + offer.hashCode()
                + arrivalDate.hashCode()
                + departureDate.hashCode()
                + totalPrice.hashCode()
                + comment.hashCode()
                + status.hashCode();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Reservation { ");
        builder.append("id = ").append(id).append(", ");
        builder.append("tenant = ").append(tenant).append(", ");
        builder.append("offer = ").append(offer).append(", ");
        builder.append("arrival date = ").append(arrivalDate).append(", ");
        builder.append("departure date = ").append(departureDate).append(", ");
        builder.append("total price = ").append(totalPrice).append(", ");
        builder.append("comment = ").append(comment).append(", ");
        builder.append("status = ").append(status).append(" ");
        builder.append("}");
        return builder.toString();
    }
    public enum ReservationStatus {
        CONFIRMED,
        REJECTED,
        IN_PROCESSING
    }
    //TODO Inner class for dates
}
