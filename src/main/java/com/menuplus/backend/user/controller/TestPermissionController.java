package com.menuplus.backend.user.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class TestPermissionController {

    // MANAGER
    @GetMapping("/manager")
    public ResponseEntity<String> getManager() {
        return ResponseEntity.ok("GET MANAGER");
    }

    @GetMapping("/manager/sub")
    public ResponseEntity<String> getManagerSub() {
        return ResponseEntity.ok("GET MANAGER SUB");
    }

    @PostMapping("/manager")
    public ResponseEntity<String> postManager() {
        return ResponseEntity.ok("POST MANAGER");
    }

    @PutMapping("/manager/{id}")
    public ResponseEntity<String> putManager(@PathVariable Long id) {
        return ResponseEntity.ok("PUT MANAGER " + id);
    }

    @DeleteMapping("/manager/{id}")
    public ResponseEntity<String> deleteManager(@PathVariable Long id) {
        return ResponseEntity.ok("DELETE MANAGER " + id);
    }

    // WAITER
    @GetMapping("/waiter")
    public ResponseEntity<String> getWaiter() {
        return ResponseEntity.ok("GET WAITER");
    }

    @PostMapping("/waiter")
    public ResponseEntity<String> postWaiter() {
        return ResponseEntity.ok("POST WAITER");
    }

    @PutMapping("/waiter/{id}")
    public ResponseEntity<String> putWaiter(@PathVariable Long id) {
        return ResponseEntity.ok("PUT WAITER " + id);
    }

    @DeleteMapping("/waiter/{id}")
    public ResponseEntity<String> deleteWaiter(@PathVariable Long id) {
        return ResponseEntity.ok("DELETE WAITER " + id);
    }

    // HOST
    @GetMapping("/host")
    public ResponseEntity<String> getHost() {
        return ResponseEntity.ok("GET HOST");
    }

    @PostMapping("/host")
    public ResponseEntity<String> postHost() {
        return ResponseEntity.ok("POST HOST");
    }

    @PutMapping("/host/{id}")
    public ResponseEntity<String> putHost(@PathVariable Long id) {
        return ResponseEntity.ok("PUT HOST " + id);
    }

    @DeleteMapping("/host/{id}")
    public ResponseEntity<String> deleteHost(@PathVariable Long id) {
        return ResponseEntity.ok("DELETE HOST " + id);
    }

    // KITCHEN
    @GetMapping("/kitchen")
    public ResponseEntity<String> getKitchen() {
        return ResponseEntity.ok("GET KITCHEN");
    }

    @PostMapping("/kitchen")
    public ResponseEntity<String> postKitchen() {
        return ResponseEntity.ok("POST KITCHEN");
    }

    @PutMapping("/kitchen/{id}")
    public ResponseEntity<String> putKitchen(@PathVariable Long id) {
        return ResponseEntity.ok("PUT KITCHEN " + id);
    }

    @DeleteMapping("/kitchen/{id}")
    public ResponseEntity<String> deleteKitchen(@PathVariable Long id) {
        return ResponseEntity.ok("DELETE KITCHEN " + id);
    }

    // CASHIER
    @GetMapping("/cashier")
    public ResponseEntity<String> getCashier() {
        return ResponseEntity.ok("GET CASHIER");
    }

    @PostMapping("/cashier")
    public ResponseEntity<String> postCashier() {
        return ResponseEntity.ok("POST CASHIER");
    }

    @PutMapping("/cashier/{id}")
    public ResponseEntity<String> putCashier(@PathVariable Long id) {
        return ResponseEntity.ok("PUT CASHIER " + id);
    }

    @DeleteMapping("/cashier/{id}")
    public ResponseEntity<String> deleteCashier(@PathVariable Long id) {
        return ResponseEntity.ok("DELETE CASHIER " + id);
    }

    // ACCOUNTANT
    @GetMapping("/accountant")
    public ResponseEntity<String> getAccountant() {
        return ResponseEntity.ok("GET ACCOUNTANT");
    }

    @PostMapping("/accountant")
    public ResponseEntity<String> postAccountant() {
        return ResponseEntity.ok("POST ACCOUNTANT");
    }

    @PutMapping("/accountant/{id}")
    public ResponseEntity<String> putAccountant(@PathVariable Long id) {
        return ResponseEntity.ok("PUT ACCOUNTANT " + id);
    }

    @DeleteMapping("/accountant/{id}")
    public ResponseEntity<String> deleteAccountant(@PathVariable Long id) {
        return ResponseEntity.ok("DELETE ACCOUNTANT " + id);
    }

    // EMPLOYEE
    @GetMapping("/employee")
    public ResponseEntity<String> getEmployee() {
        return ResponseEntity.ok("GET EMPLOYEE");
    }

    @PostMapping("/employee")
    public ResponseEntity<String> postEmployee() {
        return ResponseEntity.ok("POST EMPLOYEE");
    }

    @PutMapping("/employee/{id}")
    public ResponseEntity<String> putEmployee(@PathVariable Long id) {
        return ResponseEntity.ok("PUT EMPLOYEE " + id);
    }

    @DeleteMapping("/employee/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable Long id) {
        return ResponseEntity.ok("DELETE EMPLOYEE " + id);
    }

    // CUSTOMER
    @GetMapping("/customer")
    public ResponseEntity<String> getCustomer() {
        return ResponseEntity.ok("GET CUSTOMER");
    }

    @PostMapping("/customer")
    public ResponseEntity<String> postCustomer() {
        return ResponseEntity.ok("POST CUSTOMER");
    }

    @PutMapping("/customer/{id}")
    public ResponseEntity<String> putCustomer(@PathVariable Long id) {
        return ResponseEntity.ok("PUT CUSTOMER " + id);
    }

    @DeleteMapping("/customer/{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable Long id) {
        return ResponseEntity.ok("DELETE CUSTOMER " + id);
    }

    // SUPPORT
    @GetMapping("/support")
    public ResponseEntity<String> getSupport() {
        return ResponseEntity.ok("GET SUPPORT");
    }

    @PostMapping("/support")
    public ResponseEntity<String> postSupport() {
        return ResponseEntity.ok("POST SUPPORT");
    }

    @PutMapping("/support/{id}")
    public ResponseEntity<String> putSupport(@PathVariable Long id) {
        return ResponseEntity.ok("PUT SUPPORT " + id);
    }

    @DeleteMapping("/support/{id}")
    public ResponseEntity<String> deleteSupport(@PathVariable Long id) {
        return ResponseEntity.ok("DELETE SUPPORT " + id);
    }

    // SYSTEM_ADMIN
    @GetMapping("/system-admin")
    public ResponseEntity<String> getSystemAdmin() {
        return ResponseEntity.ok("GET SYSTEM ADMIN");
    }

    @PostMapping("/system-admin")
    public ResponseEntity<String> postSystemAdmin() {
        return ResponseEntity.ok("POST SYSTEM ADMIN");
    }

    @PutMapping("/system-admin/{id}")
    public ResponseEntity<String> putSystemAdmin(@PathVariable Long id) {
        return ResponseEntity.ok("PUT SYSTEM ADMIN " + id);
    }

    @DeleteMapping("/system-admin/{id}")
    public ResponseEntity<String> deleteSystemAdmin(@PathVariable Long id) {
        return ResponseEntity.ok("DELETE SYSTEM ADMIN " + id);
    }
}
