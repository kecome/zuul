package cn.lfungame.mapper;

import cn.lfungame.model.User;

import java.util.List;

/**
 * @Auther: xuke
 * @Date: 2018/5/22 16:36
 * @Description:
 */
public interface UserMapper {
    Long insertLog(User user);
    User selectUserById(Long id);
    List<User> selectUsers();
    void insertUser(User user);
    void updateUser(User user);
}
