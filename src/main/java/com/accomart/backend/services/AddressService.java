package com.accomart.backend.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import com.accomart.backend.entities.Address;
import com.accomart.backend.entities.User;
import com.accomart.backend.repositories.AddressRepo;
import com.accomart.backend.repositories.UserRepo;
import java.util.List;
import java.util.Optional;

@Component
public class AddressService {

    @Autowired
    private AddressRepo addressRepo;

    @Autowired
    private UserRepo userRepo;

    @Transactional
    public Address postAddress(Address address, int userId) {
        Optional<User> user = userRepo.findById(userId);
        if (user.isPresent()) {
            address.setUser(user.get());
            return addressRepo.save(address);
        } else {
            throw new IllegalArgumentException("User not found");
        }
    }

    public Address getAddress(int addressId) {
        Optional<Address> address = addressRepo.findById(addressId);
        return address.orElse(null);
    }

    public List<Address> getAddressByUserId(int userId) {
        return addressRepo.findByUserUserId(userId);
    }

    @Transactional
    public Address updateAddress(int id, Address address) {
        Optional<Address> existingAddress = addressRepo.findById(id);
        if (existingAddress.isPresent()) {
            Address updatedAddress = existingAddress.get();
            updatedAddress.setStreet(address.getStreet());
            updatedAddress.setCity(address.getCity());
            updatedAddress.setState(address.getState());
            updatedAddress.setZipCode(address.getZipCode());
            updatedAddress.setPhoneNumber(address.getPhoneNumber());
            return addressRepo.save(updatedAddress);
        }
        return null;
    }

    @Transactional
    public void deleteAddress(int id) {
        if (addressRepo.existsById(id)) {
            addressRepo.deleteById(id);
        }
    }
}
