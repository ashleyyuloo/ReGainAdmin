package com.regain.adminzn.service.util;

import java.util.Date;

public class BookingUtil {
    public static double calculateTotalPrice(double equipmentPrice, Date startDate, Date endDate) {
        long diffInMillies = Math.abs(endDate.getTime() - startDate.getTime());
        long diffInDays = (diffInMillies / (1000 * 60 * 60 * 24)) + 1; // Add 1 to include the last day

        return equipmentPrice * diffInDays;
    }
}
