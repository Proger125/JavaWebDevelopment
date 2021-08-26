package edu.epam.webproject.validator;

import java.math.BigInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static edu.epam.webproject.validator.ValidatorRegExp.*;

public class OfferValidator {
    private OfferValidator(){}

    public static boolean validateOffer(String country, String city, String street, int houseNumber, int apartmentNumber, BigInteger pricePerDay, String description){
        return validateAddress(country, city, street, houseNumber, apartmentNumber)
                && validateOfferInfo(pricePerDay, description);
    }

    private static boolean validateAddress(String country, String city, String street, int houseNumber, int apartmentNumber){
        return validateAddressString(country) && validateAddressString(city) && validateAddressString(street)
                && validateAddressNumber(houseNumber) && validateAddressNumber(apartmentNumber);
    }

    private static boolean validateOfferInfo(BigInteger pricePerDay, String description){
        return validateOfferDescription(description) && validateOfferPrice(pricePerDay);
    }

    private static boolean validateAddressString(String addressItem){
        Pattern pattern = Pattern.compile(ADDRESS_STRING_REGEXP);
        Matcher matcher = pattern.matcher(addressItem);
        return matcher.matches();
    }

    private static boolean validateAddressNumber(int addressItem){
        Pattern pattern = Pattern.compile(ADDRESS_NUMBER_REGEXP);
        Matcher matcher = pattern.matcher(Integer.toString(addressItem));
        return matcher.matches();
    }

    private static boolean validateOfferDescription(String description){
        Pattern pattern = Pattern.compile(OFFER_INFO_STRING_REGEXP);
        Matcher matcher = pattern.matcher(description);
        return matcher.matches();
    }

    private static boolean validateOfferPrice(BigInteger price){
        Pattern pattern = Pattern.compile(ADDRESS_NUMBER_REGEXP);
        Matcher matcher = pattern.matcher(price.toString());
        return matcher.matches();
    }
}
