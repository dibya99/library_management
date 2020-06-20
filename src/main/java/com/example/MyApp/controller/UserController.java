package com.example.MyApp.controller;

import com.example.MyApp.DTO.UserDTO;
import com.example.MyApp.Exceptions.UserNotFoundException;
import com.example.MyApp.entity.User;
import com.example.MyApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;


@RestController
public class UserController {
    @Autowired
    UserRepository user_repo;

    private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    @GetMapping("/users")
    public List<UserDTO> getUsers() {
        List<User> userlist=user_repo.findAll();
        List<UserDTO> u_list=new ArrayList<>();
        if(userlist.isEmpty())
        {
            LOGGER.severe("no users found");
            throw new UserNotFoundException(0);
        }
        for(User user:userlist)
        {
            UserDTO u=new UserDTO(user.getUser_id(),user.getFull_name(),user.getAge(),user.getSex(),user.getAddress(),
                                        user.isStatus());
            u_list.add(u);
        }
        LOGGER.info("User list prepared");
        return u_list;
    }
    @GetMapping("/users/{user_id}")
    public UserDTO get_user(@PathVariable int user_id)
    {
        Optional<User> obj=user_repo.findById(user_id);
        if(obj.isPresent())
        {
            User user=obj.get();
            UserDTO obj2=new UserDTO(user.getUser_id(),user.getFull_name(),user.getAge(),user.getSex(),user.getAddress(),
                    user.isStatus());
            LOGGER.info("User found");
            return obj2;
        }
        else
        {
            LOGGER.severe("User not found in database");
            throw new UserNotFoundException(user_id);
        }
    }

    @DeleteMapping("/users")
    public String delete_users()
    {
        List<User> userlist=user_repo.findAll();
        if(userlist.isEmpty())
        {
            LOGGER.severe("Not a single user in database");
            throw new UserNotFoundException(0);
        }
        for(User user:userlist)
        {
            user.setStatus(false);
            user_repo.save(user);
        }
        LOGGER.info("Users deleted");
        return "All users declared as removed";

    }

    @DeleteMapping("/users/{user_id}")
    public String delete_a_user(@PathVariable int user_id)
    {
        Optional <User> obj=user_repo.findById(user_id);
        if(obj.isPresent())
        {
            User user=obj.get();
            user.setStatus(false);
            user_repo.save(user);
            LOGGER.info("User successfully deleted");
            return "User successfully deleted";
        }
        else
        {
            LOGGER.severe("User not found");
            throw new UserNotFoundException(user_id);
        }
    }

    @PostMapping("/users")
    public boolean writeUser(@RequestBody User user) {
        user_repo.save(user);
        return true;
    }

    @PostMapping("/users/{user_id}")
    public boolean change_user_status(@PathVariable int user_id)
    {
        Optional <User> obj=user_repo.findById(user_id);
        if(obj.isPresent())
        {
            User user=obj.get();
            user.setStatus(true);
            user_repo.save(user);
            LOGGER.info("User added");
            return true;
        }
        else
        {
            LOGGER.severe("User not found");
            throw new UserNotFoundException(user_id);
        }
    }
}
