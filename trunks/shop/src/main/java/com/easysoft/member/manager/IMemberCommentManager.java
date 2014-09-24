package com.easysoft.member.manager;

import com.easysoft.framework.db.PageOption;
import com.easysoft.member.model.MemberComment;

/**
 * User: andy
 * Date: 13-8-12
 * Time: 下午1:03
 *
 * @since:
 */
public interface IMemberCommentManager {
    public  void add(MemberComment paramMemberComment);

    public PageOption getGoodsComments(int paramInt1, int paramInt2, int paramInt3, int paramInt4);

    public  int getGoodsGrade(int paramInt);

    public PageOption getAllComments(int paramInt1, int paramInt2, int paramInt3);

    public PageOption getCommentsByStatus(int paramInt1, int paramInt2, int paramInt3, int paramInt4);

    public  MemberComment get(int paramInt);

    public  void update(MemberComment paramMemberComment);

    public  int getGoodsCommentsCount(int paramInt);

    public  void delete(int paramInt);

    public PageOption getMemberComments(int paramInt1, int paramInt2, int paramInt3, int paramInt4);

    public  int getMemberCommentTotal(int paramInt1, int paramInt2);
}
