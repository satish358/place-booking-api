package com.example.hotelmanagment.Services;

import com.example.hotelmanagment.Models.MessagePool;
import com.example.hotelmanagment.Repositories.MessagePoolDAO;
import com.example.hotelmanagment.Repositories.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
public class MessagePoolService {
    @Autowired
    MessagePoolDAO messagePoolDAO;
    @Autowired
    UserDAO userDAO;

    public MessagePool createMessage(String message, Long sender, Long receiver){
        MessagePool messagePool = new MessagePool( );
        messagePool.setMessage(message);
        messagePool.setSender(sender);
        messagePool.setReceiver(receiver);
        messagePool.setCreatedOn(new Date());
        messagePoolDAO.save(messagePool);
        return messagePool;
    }
    public List<MessagePool> getAllMessagesByUserId(Long sender){
        return messagePoolDAO.findAllBySenderOrderByCreatedOn(sender);
    }
    public List<MessagePool> getAll(){
        return messagePoolDAO.findAll();
    }
}
