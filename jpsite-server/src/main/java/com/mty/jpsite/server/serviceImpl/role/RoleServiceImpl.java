package com.mty.jpsite.server.serviceImpl.role;

import com.mty.jpsite.server.entity.role.Role;
import com.mty.jpsite.server.dao.role.RoleDao;
import com.mty.jpsite.server.service.role.RoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author jiangpeng
 * @since 2018-11-30
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleDao, Role> implements RoleService {

}
