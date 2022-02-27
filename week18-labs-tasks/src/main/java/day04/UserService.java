package day04;

import java.util.Comparator;
import java.util.List;

public class UserService {

    private List<User> users = List.of(
            new User("Jill Doe"),
            new User("Jane Doe"),
            new User("John Doe")
    );

    public List<User> getUsers() {
        return users;
    }

    public void sortList() {
        users = users.stream()
                .sorted(Comparator.comparing(User::getName).reversed())
                .toList();
    }

    public static void main(String[] args) {
        UserService us = new UserService();
        System.out.println(us.getUsers());
        us.sortList();
        System.out.println(us.getUsers());
    }
}
