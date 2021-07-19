package com.ccnu.learningService.service;

import com.ccnu.learningService.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 用户 服务类
 * </p>
 *
 * @author gzh
 * @since 2021-06-26
 */
public interface UserService extends IService<User> {

    void register(User user);

    User login(User user);

    String getIdByCardNum(String cardNum);

    User getUserById(String id);
}
