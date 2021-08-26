package edu.epam.webproject.validator;

import org.testng.annotations.Test;

import java.math.BigInteger;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class OfferValidatorTest {
    @Test
    public void offerValidatorTestTrue(){
        String country = "Belarus";
        String city = "Minsk";
        String street = "Lobanka";
        int houseNumber = 12;
        int apartmentNumber = 34;
        BigInteger pricePerDay = new BigInteger("45");
        String description = "description";
        assertTrue(OfferValidator.validateOffer(country, city, street, houseNumber, apartmentNumber, pricePerDay, description));
    }

    @Test
    public void offerValidatorTestFalse(){
        String country = "<script>alert('Error');</script>";
        String city = "Minsk";
        String street = "Lobanka";
        int houseNumber = 12;
        int apartmentNumber = 34;
        BigInteger pricePerDay = new BigInteger("45");
        String description = "description";
        assertFalse(OfferValidator.validateOffer(country, city, street, houseNumber, apartmentNumber, pricePerDay, description));
    }
}
