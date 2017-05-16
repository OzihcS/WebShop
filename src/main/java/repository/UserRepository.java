package repository;

import domain.User;

import java.util.HashMap;
import java.util.Map;

/**
 * Provides storage of users.
 */
public class UserRepository {
    private Map<String, User> users;

    public UserRepository() {
        this.users = new HashMap<>();
    }

    /**
     * Adds new user to user's storage.
     *
     * @param user to add.
     */
    public void add(User user) {
        users.put(user.getEmail(), user);
    }

    /**
     * Checks existence specified user in users storage.
     *
     * @param user to check.
     * @return true if user exists and false in otherwise.
     */
    public boolean consistsUser(User user) {
        return users.containsKey(user.getEmail());
    }

}
