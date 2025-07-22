import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UserManager {
    private List<User> users = new ArrayList<>();
    private static final String USERS_FILE = "users.dat";

    public UserManager() {
        loadUsers();
    }

    public boolean addUser(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return false; // User already exists
            }
        }
        users.add(new User(username, password));
        saveUsers();
        return true;
    }

    public boolean authenticate(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    private void saveUsers() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(USERS_FILE))) {
            oos.writeObject(users);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    private void loadUsers() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(USERS_FILE))) {
            users = (List<User>) ois.readObject();
        } catch (FileNotFoundException e) {
            // Create a default admin user if the file doesn't exist
            users.add(new User("admin", "password"));
            saveUsers();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
