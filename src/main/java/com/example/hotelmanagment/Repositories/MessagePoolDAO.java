package com.example.hotelmanagment.Repositories;

import com.example.hotelmanagment.Models.MessagePool;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessagePoolDAO extends JpaRepository<MessagePool, Long> {
    List<MessagePool> findAllBySenderOrderByCreatedOn(Long sender);
    List<MessagePool> findAllByReceiverOrderByCreatedOn(Long sender);
}
