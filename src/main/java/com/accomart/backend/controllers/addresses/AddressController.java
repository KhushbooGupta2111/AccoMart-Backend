package com.accomart.backend.controllers.addresses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.accomart.backend.entities.Address;
import com.accomart.backend.services.AddressService.AddressService;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("address")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @PostMapping("/postAddress/{userId}")
    public ResponseEntity<Address> postAddress(@Valid @RequestBody Address address, @PathVariable int userId) {
        Address createdAddress = addressService.postAddress(address, userId);
        return new ResponseEntity<>(createdAddress, HttpStatus.CREATED);
    }

    @GetMapping("/getAddress/{addressId}")
    public ResponseEntity<Address> getAddress(@PathVariable int addressId) {
        Address address = addressService.getAddress(addressId);
        return new ResponseEntity<>(address, HttpStatus.OK);
    }

    @GetMapping("/getAddressByUserId/{userId}")
    public ResponseEntity<List<Address>> getAddressByUserId(@PathVariable int userId) {
        List<Address> addresses = addressService.getAddressByUserId(userId);
        return new ResponseEntity<>(addresses, HttpStatus.OK);
    }

    @PutMapping("/updateAddress/{id}")
    public ResponseEntity<Address> updateAddress(@PathVariable int id, @Valid @RequestBody Address address) {
        Address updatedAddress = addressService.updateAddress(id, address);
        return new ResponseEntity<>(updatedAddress, HttpStatus.OK);
    }

    @DeleteMapping("/deleteAddress/{id}")
    public ResponseEntity<Void> deleteAddress(@PathVariable int id) {
        addressService.deleteAddress(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
