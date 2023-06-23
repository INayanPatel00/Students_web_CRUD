package com.brevitaz.services.serviceClass;

import com.brevitaz.entity.Address;
import com.brevitaz.repository.AddressRepository;
import com.brevitaz.services.serviceInterface.AddressService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceImpl implements AddressService {
    private static final Logger logger = LogManager.getLogger(AddressServiceImpl.class);

    private final AddressRepository addressRepository;

    @Autowired
    public AddressServiceImpl(final AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public Address addAddress(Address address){
        logger.info("Entered into addAddress Method");
       Address address1= addressRepository.getByHouseNoAndLandMarkAndCity(address.getHouseNo(),address.getLandMark(),address.getCity());
       if(address1 == null) {
           logger.info("Address added Successfully");
           return addressRepository.save(address);
       }
       logger.info("Address already Exists");
       return address1;
    }


//    public void checkAddress(Address address) throws SQLException {
//        if (addressRepository.checkAddress(address)) {
//            addressRepository.insertAddressDetails(address);
//        }
//    }
//    public List<Address> viewAddress(){
//        return addressRepository.findAll();
//    }

//    public int getViewById(Address address) throws SQLException {
//        return addressRepository.getAddressId(address);
//
//    }
//    public Address ViewById(int addressId){
//    return addressRepository.addressViewById(addressId);
//    }

}
