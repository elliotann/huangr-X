package com.easysoft.member.backend.manager.impl;

import com.easysoft.core.common.service.impl.GenericService;
import com.easysoft.member.backend.manager.IFunAndOperManager;
import com.easysoft.member.backend.model.FunAndOper;
import org.springframework.stereotype.Service;

/**
 * User: andy
 * Date: 14-5-12
 * Time: 下午4:18
 *
 * @since:
 */
@Service("funAndOperManager")
public class FunAndOperManager extends GenericService<FunAndOper> implements IFunAndOperManager {
}
