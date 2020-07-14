package repos;

import java.util.Set;
import models.*;

// interface for DAO functionality
public interface BankDAO {
	public boolean insert(User user);
	public boolean insertStatement(User user);
	public User findByUsername(String string);
	public Set<User> selectAll();
}
