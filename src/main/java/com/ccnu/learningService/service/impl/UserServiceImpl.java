package com.ccnu.learningService.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ccnu.learningService.entity.User;
import com.ccnu.learningService.handler.exceptionhandler.MyException;
import com.ccnu.learningService.mapper.UserMapper;
import com.ccnu.learningService.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ccnu.learningService.utils.JwtUtils;
import com.ccnu.learningService.utils.MD5;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * <p>
 * 用户 服务实现类
 * </p>
 *
 * @author gzh
 * @since 2021-06-26
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public void register(User user) {
        String cardNum = user.getCardNum();
        String password = user.getPsw();

        if (StringUtils.isEmpty(cardNum) || StringUtils.isEmpty(password)) {
            throw new MyException(20001, "注册失败");
        }

        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("card_num",cardNum);
        Integer count = baseMapper.selectCount(wrapper);
        if(count > 0) {
            throw new MyException(20001, "用户名已存在");
        }

        User endUser = new User();
        BeanUtils.copyProperties(user, endUser);
        endUser.setPsw(MD5.encrypt(user.getPsw()));
        endUser.setAvatar("https://env.oss-cn-beijing.aliyuncs.com/2021/05/10/default_avatar.png");
        baseMapper.insert(endUser);
    }

    @Override
    public User login(User user) {
        //获取帐号和密码
        String cardNum = user.getCardNum();
        String password = user.getPsw();

        //帐号和密码非空判断
        if(StringUtils.isEmpty(cardNum) || StringUtils.isEmpty(password)) {
            throw new MyException(20001, "帐号或密码为空");
        }

        //判断帐号是否正确
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("card_num", cardNum);
        User endUser = baseMapper.selectOne(wrapper);

        //判断查询对象是否为空
        if(endUser == null) {
            throw new MyException(20001,"登录失败");
        }
        //判断密码
        if(!MD5.encrypt(password).equals(endUser.getPsw())) {
            throw new MyException(20001, "帐号密码不匹配");
        }
        endUser.setPsw("");
        return endUser.setToken(JwtUtils.getJwtToken(cardNum, endUser.getType()));
    }

    @Override
    public String getIdByCardNum(String cardNum) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("card_num", cardNum);
        User user = baseMapper.selectOne(wrapper);

        if (StringUtils.isEmpty(user)) {
            throw new MyException(20001, "未获取到课程");
        }

        return user.getId();
    }

    @Override
    public User getUserById(String id) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("id", id);
        User user = baseMapper.selectOne(wrapper);

        if (StringUtils.isEmpty(user)) {
            throw new MyException(20001, "未获取到用户");
        }

        return user;
    }
}
