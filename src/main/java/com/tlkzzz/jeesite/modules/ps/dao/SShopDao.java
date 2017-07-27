/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/tlkzzz/jeesite">JeeSite</a> All rights reserved.
 */
package com.tlkzzz.jeesite.modules.ps.dao;

import com.tlkzzz.jeesite.common.persistence.CrudDao;
import com.tlkzzz.jeesite.common.persistence.annotation.MyBatisDao;
import com.tlkzzz.jeesite.modules.ps.entity.SShop;

import java.util.List;

/**
 * 购物车DAO接口
 * @author xlc
 * @version 2017-07-21
 */
@MyBatisDao
public interface SShopDao extends CrudDao<SShop> {

   public SShop getlist(String id,String name);


    /**
     * 通过ID修改规格ids和数量
     * @param sShop
     */
    void updateInfo(SShop sShop);

    /**
     * 通过用户ID将当前用户的选定订单清除
     * @param userId
     */
    void updateOrderFlag(String userId);

    /**
     * 查询当前用户下所有确定订单
     * @param sShop
     * @return
     */
    List<SShop> findConfirmOrderList(SShop sShop);


}