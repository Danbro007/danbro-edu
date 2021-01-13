package com.danbro.acl.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.danbro.acl.entity.AclUser;
import com.danbro.acl.mapper.AclUserMapper;
import com.danbro.acl.service.AclUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户表(AclUser)表服务实现类
 *
 * @author makejava
 * @since 2021-01-13 14:18:15
 */
@Service("aclUserService")
public class AclUserServiceImpl extends ServiceImpl<AclUserMapper, AclUser> implements AclUserService {
}