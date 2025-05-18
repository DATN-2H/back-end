package com.menuplus.backend.bot.service;

import com.menuplus.backend.booking.api.service.BookingService;
import org.springframework.stereotype.Service;

@Service
public class BotServiceImp {

    private BookingService bookingService;

    public void test() {
        System.out.println("BotService test method called");
        bookingService.test();
    }
}
