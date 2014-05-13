package com.easysoft.member.backend.manager.impl;

import com.easysoft.core.common.service.impl.GenericService;
import com.easysoft.member.backend.manager.IRoleAuthManager;
import com.easysoft.member.backend.model.RoleAuth;
import org.springframework.stereotype.Service;

/**
 * User: andy
 * Date: 14-5-13
 * Time: 下午12:32
 *
 * @since:
 */
@Service("roleAuthManager")
public class RoleAuthManager extends GenericService<RoleAuth> implements IRoleAuthManager {
}
