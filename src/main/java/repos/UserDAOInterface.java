package repos;

import java.util.List;
import models.*;

public interface UserDAOInterface {
	public List<User> findAll();
	public User findById(int id);
	public User updateUserById(int id, User user);
	public User addUser(User user);
}
