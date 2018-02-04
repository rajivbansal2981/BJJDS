package com.jmk.bjjd.web.facade;

import java.util.List;

import com.jmk.bjjd.models.MemberModel;
import com.jmk.bjjd.models.RoleModel;

public interface MemberMgmtServiceFacade extends BaseMgmtServiceFacade<MemberModel>{
	List<RoleModel> findRolesByTenantId(Long tenantId);
	List<MemberModel> findTeamLeadersByTenantId(Long tenantId);
}
