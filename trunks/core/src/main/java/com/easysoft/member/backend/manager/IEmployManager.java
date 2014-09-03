package com.easysoft.member.backend.manager;

import com.easysoft.member.backend.model.Employ;

import java.util.List;

/**
 * @author : andy.huang
 * @since :
 */
public interface IEmployManager {
    public List<Employ> queryForList();
    public void saveEmploy(Employ employ);
}
