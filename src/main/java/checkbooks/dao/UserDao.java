package checkbooks.dao;

import checkbooks.entity.User;

/**
 * Created by pc8 on 12.09.15.
 */
public interface UserDao {
    User findByUserName(String username);
}
