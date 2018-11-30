package com.mty.jpsite.server.dao.user;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mty.jpsite.server.entity.user.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author jiangpeng
 * @since 2018-11-30
 */
public interface UserDao extends BaseMapper<User> {
    IPage<User> selectPageVo(Page page, @Param("name") String name);
}
