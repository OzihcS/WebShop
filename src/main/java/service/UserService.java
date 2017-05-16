package service;

import domain.User;
import repository.UserRepository;

public class UserService {

    private UserRepository repository;

    public UserService() {
        this.repository = new UserRepository();
    }

    public void add(User user) {
        repository.add(user);
    }

    public boolean contains(User user) {
        return repository.consistsUser(user);
    }
}
