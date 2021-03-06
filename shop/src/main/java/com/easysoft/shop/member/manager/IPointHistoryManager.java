package com.easysoft.shop.member.manager;

import com.easysoft.framework.db.PageOption;
import com.easysoft.shop.model.PointHistory;

import java.util.List;

/**
 * 会员积分历史管理
 * 
 * @author andy<br/>
 *         2010-3-22 上午11:23:00<br/>
 *         version 1.0<br/>
 */
public interface IPointHistoryManager {

	/**
	 * 积分历史列表(当前会员）
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public PageOption pagePointHistory(int pageNo, int pageSize, int pointType);
    public PageOption pagePointHistory(int pageNo, int pageSize);
	/**
	 * 列表指定会员 的积分日志
	 * @param member_id
	 * @return
	 */
	public List listPointHistory(int member_id);

	/**
	 * 累计消费积分
	 * 
	 * @return
	 */
	public Long getConsumePoint(int pointType);

	/**
	 * 累计获得积分
	 * 
	 * @return
	 */
	public Long getGainedPoint(int pointType);

	/**
	 * 新增日志
	 * 
	 * @param pointHistory
	 */
	public void addPointHistory(PointHistory pointHistory);
    public List<PointHistory> listPointHistory(int member_id, int pointtype);
    public PageOption pagePointFreeze(int pageNo, int pageSize);
}
