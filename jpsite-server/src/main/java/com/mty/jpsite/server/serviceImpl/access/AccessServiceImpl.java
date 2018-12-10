package com.mty.jpsite.server.serviceImpl.access;

import com.mty.jpsite.server.entity.access.Access;
import com.mty.jpsite.server.dao.access.AccessDao;
import com.mty.jpsite.server.service.access.AccessService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 权限表  服务实现类
 * </p>
 *
 * @author jiangpeng
 * @since 2018-12-10
 */
@Service
public class AccessServiceImpl extends ServiceImpl<AccessDao, Access> implements AccessService {

}
