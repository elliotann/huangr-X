package com.easysoft.component.dform.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.easysoft.component.dform.service.CgFormFieldServiceI;
import com.easysoft.core.common.service.impl.GenericService;

@Service("cgFormFieldService")
@Transactional
public class CgFormFieldServiceImpl extends GenericService implements
		CgFormFieldServiceI {
	

}