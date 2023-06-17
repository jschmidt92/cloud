package org.a3sog.hq.service;

import org.a3sog.hq.model.User;

import java.util.List;

public interface UserService {
  boolean saveUser(User user);

  List<User> fetchAllUser();

  User fetchUserById(Long id);

  boolean deleteUser(Long id);

  boolean updateUser(Long id, User user);
}
