package com.menuplus.backend.booking.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bookings")
public class BookingController {

  // Define your endpoints and methods here
  // For example:
  @GetMapping("")
  public String getAllBookings() {
    return "QUAAAAAA";
  }
}
