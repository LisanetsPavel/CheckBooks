package checkbooks.dao;

import checkbooks.entity.User;
import checkbooks.entity.UserRole;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by pc8 on 12.09.15.
 */
public class UserDaoImpl implements UserDao {

    static Map<String, User> map = new HashMap<>();

    static {
        fillMap();
    }

    public static void main(String[] args){
       UserDaoImpl userDao =  new UserDaoImpl();
        userDao.fillMap();
       for (Map.Entry<String, User> entry : userDao.map.entrySet())
        System.out.println(entry);
    }


    @Override
    public User findByUserName(String username) {
        System.out.println(username);
        System.out.println(map.get(username));
       return map.get(username);

    }

   private static void fillMap(){
        User user = new User();
        User user2 = new User();
        Set<UserRole> set = new HashSet<>();
        UserRole userRole = new UserRole();
        user.setUsername("Pavel");
        user.setPassword("1207");

        userRole.setRole("ROLE_USER");
      //  userRole.setUser(user);
        userRole.setUserRoleId(1);
        set.add(userRole);

       UserRole userRole2 = new UserRole();
        userRole2.setRole("ROLE_ADMIN");
      //  userRole.setUser(user);
        userRole2.setUserRoleId(2);
        set.add(userRole2);
        user.setUserRole(set);
        map.put("Pavel", user);

       Set<UserRole> set2 = new HashSet<>();

        user2.setUsername("Sasha");
        user2.setPassword("1234");
        UserRole userRole3 = new UserRole();
        userRole3.setRole("ROLE_USER");
     //   userRole.setUser(user);
        userRole3.setUserRoleId(3);
        set2.add(userRole3);
        user2.setUserRole(set2);
         map.put("Sasha", user2);
    }


}
