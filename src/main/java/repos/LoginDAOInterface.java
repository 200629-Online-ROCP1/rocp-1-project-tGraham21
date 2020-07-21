package repos;

import models.*;

public interface LoginDAOInterface {
	int checkLogin(LoginDTO dto);
}
