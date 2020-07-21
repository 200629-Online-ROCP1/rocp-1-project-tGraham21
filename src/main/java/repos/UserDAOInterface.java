package repos;

import java.util.List;
import models.*;

public interface UserDAOInterface {
	public List<User> findAll();
	public User findById(int id);
	public User updateUserbyId(User user);
	public Boolean addUser(User user);
}
