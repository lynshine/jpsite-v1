package com.mty.jpsite.server.serviceImpl.user;

import com.mty.jpsite.server.entity.user.User;
import com.mty.jpsite.server.dao.user.UserDao;
import com.mty.jpsite.server.service.user.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author jiangpeng
 * @since 2018-12-06
 */
@Service
@Transactional
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements UserService {

}
