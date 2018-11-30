package com.mty.jpsite.server.service.user;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mty.jpsite.server.entity.user.User;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author jiangpeng
 * @since 2018-11-30
 */
public interface UserService extends IService<User> {
    IPage<User> selectPageVo(Page page, @Param("name") String name);
}
