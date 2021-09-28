package com.dxc.glic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dxc.glic.entity.InventoryUser;

@Repository
public interface InventoryUserRepository extends JpaRepository<InventoryUser,String>{

}
