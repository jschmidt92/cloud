package org.a3sog.hq.repository;

import org.a3sog.hq.model.User;

import java.util.List;

public interface UserDao {
  boolean saveUser(User user);

  List<User> fetchAllUser();

  User fetchUserById(Long id);

  boolean deleteUser(Long id);

  boolean updateUser(Long id, User user);
}
