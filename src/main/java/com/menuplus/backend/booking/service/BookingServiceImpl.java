package com.menuplus.backend.booking.service;

import com.menuplus.backend.booking.api.service.BookingService;
import org.springframework.stereotype.Service;

@Service
public class BookingServiceImpl implements BookingService {
    @Override
    public void test() {
        System.out.println("BookingService test method called");
    }
}