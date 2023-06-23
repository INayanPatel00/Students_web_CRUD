package com.brevitaz.repository;

import com.brevitaz.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Integer>{
    Address getByHouseNoAndLandMarkAndCity(int houseNo,String landMark ,String city );

}
