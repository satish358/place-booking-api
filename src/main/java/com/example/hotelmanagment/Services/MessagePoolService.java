package com.example.hotelmanagment.Services;

import com.example.hotelmanagment.Models.MessagePool;
import com.example.hotelmanagment.Models.User;
import com.example.hotelmanagment.Repositories.MessagePoolDAO;
import com.example.hotelmanagment.Repositories.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class MessagePoolService {
    @Autowired
    MessagePoolDAO messagePoolDAO;
    @Autowired
    UserDAO userDAO;

    public MessagePool createMessage(String message, Long sender, Long receiver){
        MessagePool messagePool = new MessagePool( );
        messagePool.setMessage(message);
        Optional<User> senderU = userDAO.findById(sender);
        Optional<User> receiverU = userDAO.findById(receiver);
        messagePool.setSender(senderU.isPresent()? senderU.get() : null);
        messagePool.setReceiver(receiverU.isPresent() ? receiverU.get() : null);
        messagePool.setCreatedOn(new Date());
        messagePoolDAO.save(messagePool);
        return messagePool;
    }
    public List<MessagePool> getAllMessagesByUserId(Long receiver){
        Optional<User> user = userDAO.findById(receiver);
        if(user.isPresent())
            return messagePoolDAO.findAllByReceiverOrderByCreatedOn(user.get());
        return new ArrayList<>();
    }
    public List<MessagePool> getAll(){
        return messagePoolDAO.findAll();
    }
}
