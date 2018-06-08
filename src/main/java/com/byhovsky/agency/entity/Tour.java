package com.byhovsky.agency.entity;

import java.math.BigDecimal;
import java.sql.Date;

/**
 * Tour
 *
 * @author Denis Byhovsky
 */
public class Tour {
    private int tourId;
    private String photo;
    private BigDecimal cost;
    private String description;
    private int duration;
    private Date date;
    private Country country;
    private TourType type;
    private Hotel hotel;


    public Tour(int tourId, String photo, BigDecimal cost, String description, int duration, Date date, Country country, TourType type, Hotel hotel) {
        this.tourId = tourId;
        this.photo = photo;
        this.cost = cost;
        this.description = description;
        this.duration = duration;
        this.date = date;
        this.country = country;
        this.type = type;
        this.hotel = hotel;
    }

    /**
     * Getter for property 'tourId'.
     *
     * @return Value for property 'tourId'.
     */
    public int getTourId() {
        return tourId;
    }

    /**
     * Setter for property 'tourId'.
     *
     * @param tourId Value to set for property 'tourId'.
     */
    public void setTourId(int tourId) {
        this.tourId = tourId;
    }

    /**
     * Getter for property 'photo'.
     *
     * @return Value for property 'photo'.
     */
    public String getPhoto() {
        return photo;
    }

    /**
     * Setter for property 'photo'.
     *
     * @param photo Value to set for property 'photo'.
     */
    public void setPhoto(String photo) {
        this.photo = photo;
    }

    /**
     * Getter for property 'cost'.
     *
     * @return Value for property 'cost'.
     */
    public BigDecimal getCost() {
        return cost;
    }

    /**
     * Setter for property 'cost'.
     *
     * @param cost Value to set for property 'cost'.
     */
    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    /**
     * Getter for property 'description'.
     *
     * @return Value for property 'description'.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Setter for property 'description'.
     *
     * @param description Value to set for property 'description'.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Getter for property 'duration'.
     *
     * @return Value for property 'duration'.
     */
    public int getDuration() {
        return duration;
    }

    /**
     * Setter for property 'duration'.
     *
     * @param duration Value to set for property 'duration'.
     */
    public void setDuration(int duration) {
        this.duration = duration;
    }

    /**
     * Getter for property 'date'.
     *
     * @return Value for property 'date'.
     */
    public Date getDate() {
        return date;
    }

    /**
     * Setter for property 'date'.
     *
     * @param date Value to set for property 'date'.
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * Getter for property 'country'.
     *
     * @return Value for property 'country'.
     */
    public Country getCountry() {
        return country;
    }

    /**
     * Setter for property 'country'.
     *
     * @param country Value to set for property 'country'.
     */
    public void setCountry(Country country) {
        this.country = country;
    }

    /**
     * Getter for property 'type'.
     *
     * @return Value for property 'type'.
     */
    public TourType getType() {
        return type;
    }

    /**
     * Setter for property 'type'.
     *
     * @param type Value to set for property 'type'.
     */
    public void setType(TourType type) {
        this.type = type;
    }

    /**
     * Getter for property 'hotel'.
     *
     * @return Value for property 'hotel'.
     */
    public Hotel getHotel() {
        return hotel;
    }

    /**
     * Setter for property 'hotel'.
     *
     * @param hotel Value to set for property 'hotel'.
     */
    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tour tour = (Tour) o;

        if (tourId != tour.tourId) return false;
        if (duration != tour.duration) return false;
        if (photo != null ? !photo.equals(tour.photo) : tour.photo != null) return false;
        if (cost != null ? !cost.equals(tour.cost) : tour.cost != null) return false;
        if (description != null ? !description.equals(tour.description) : tour.description != null) return false;
        if (date != null ? !date.equals(tour.date) : tour.date != null) return false;
        if (country != null ? !country.equals(tour.country) : tour.country != null) return false;
        if (type != tour.type) return false;
        return hotel != null ? hotel.equals(tour.hotel) : tour.hotel == null;
    }

    @Override
    public int hashCode() {
        int result = tourId;
        result = 31 * result + (photo != null ? photo.hashCode() : 0);
        result = 31 * result + (cost != null ? cost.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + duration;
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (country != null ? country.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (hotel != null ? hotel.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Tour{" +
                "tourId=" + tourId +
                ", photo='" + photo + '\'' +
                ", cost=" + cost +
                ", description='" + description + '\'' +
                ", duration=" + duration +
                ", date=" + date +
                ", country=" + country +
                ", type=" + type +
                ", hotel=" + hotel +
                '}';
    }
}
