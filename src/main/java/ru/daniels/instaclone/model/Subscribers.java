package ru.daniels.instaclone.model;

import ru.daniels.instaclone.model.dbentity.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Subscribers {
    private long id;
    private final HashMap<Long, User> subscribers;

    public Subscribers(){
        subscribers = new HashMap<>();
    }

    public Subscribers(HashMap<Long, User> subscribers){
        this.subscribers = subscribers;
    }

    public List<User> getSubscribers() {
        return new ArrayList<>(subscribers.values());
    }

    public User getSubscriber(long id){
        return subscribers.get(id);
    }

    public void addSubscriber(User user){
        subscribers.put(user.getId(), user);
    }

    public void deleteSubscriber(long id){
        subscribers.remove(id);
    }
}
