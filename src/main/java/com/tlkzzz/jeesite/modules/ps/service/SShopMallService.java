package com.tlkzzz.jeesite.modules.ps.service;

import com.tlkzzz.jeesite.common.service.BaseService;
import com.tlkzzz.jeesite.common.utils.StringUtils;
import com.tlkzzz.jeesite.modules.ps.dao.SOrderDao;
import com.tlkzzz.jeesite.modules.ps.dao.SShopDao;
import com.tlkzzz.jeesite.modules.ps.dao.SSpecClassDao;
import com.tlkzzz.jeesite.modules.ps.dao.SSpecDao;
import com.tlkzzz.jeesite.modules.ps.entity.*;
import com.tlkzzz.jeesite.modules.sys.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by WT on 2017-07-25.
 */
@Service
@Transactional(readOnly = true)
public class SShopMallService  extends BaseService {
    @Autowired
    private SShopDao sShopDao;
    @Autowired
    private SOrderDao sOrderDao;
    @Autowired
    private SSpecDao sSpecDao;
    @Autowired
    private SSpecClassDao sSpecClassDao;
    @Autowired
    private SAddressService sAddressService;
    /**
     * 获取指定位数的随机数
     * @return
     */
    public String random(int bit){
        if(bit<=0)return null;
        Random r = new Random();
        StringBuffer s = new StringBuffer();
        for(int i=0;i<bit;i++) {
            int num = r.nextInt(10);
            s.append(num);
        }
        return s.toString();
    }

    /**
     * 确认订单保存购物车选择信息
     * @param ids
     * @param specIds
     * @param nums
     * @return
     */
    @Transactional(readOnly = false)
    public List<SShop> confirmOrder(String ids, String specIds, String nums){
        if(StringUtils.isBlank(ids)||StringUtils.isBlank(specIds)||StringUtils.isBlank(nums))return null;
        String[] idList = ids.split(",");
        String[] specIdList = specIds.split("|");
        String[] numList = nums.split(",");
        List<SShop> shopList = new ArrayList<SShop>();
        sShopDao.updateOrderFlag(UserUtils.getUser().getId());
        for(int i=0;i<idList.length;i++){
            SShop shop = sShopDao.get(idList[i]);
            if(shop==null)continue;
            int num = Integer.parseInt(numList[i]);
            shop.setSpecIds(specIdList[i]);
            shop.setNum(String.valueOf((num<1)?1:num));
            shop.setOrderFlag("1");
            sShopDao.updateInfo(shop);
        }
        return shopList;
    }

    /**
     * 计算订单的总金额
     * @param list
     * @return
     */
    public double countOrderTotal(List<SShop> list){
        double total = 0;
        for (SShop s: list){
            if(s==null)continue;
            if(StringUtils.isBlank(s.getNum()))continue;
            if(StringUtils.isBlank(s.getPrice()))continue;
            total += Integer.parseInt(s.getNum())*Double.parseDouble(s.getPrice());
        }
        return total;
    }

    /**
     * 通过当前登录会员ID查询会员所有送货地址
     * 默认送货地址排序在第一个
     * @return
     */
    public List<SAddress> findAddressListByM(){
        SAddress address = new SAddress();
        address.setMember(UserUtils.getUser().getMember());
        return sAddressService.findList(address);
    }

    /**
     * 通过商品和规格ids查询规格列表
     * @param goods
     * @param specIds
     * @return
     */
    public SGoods findSpecInfo(SGoods goods,String specIds){
        if(goods==null||StringUtils.isBlank(specIds))return goods;
        if(goods.getGener()==null)goods.setGener(new SGenre());
        List<SSpec> specList = sSpecDao.findListByIds(new SSpec(specIds));
        if(specList.size()>0){
            List<SSpecClass> specClassList = sSpecClassDao.findList(new SSpecClass());
            for(SSpecClass sc:specClassList){
                sc.setsSpecList(new ArrayList<SSpec>());
                for(SSpec s:specList){
                    if(s.getSpecClassId().equals(sc.getId())){
                        sc.getsSpecList().add(s);
                    }
                }
            }
            for(int i=0;i<specClassList.size();i++){
                if(specClassList.get(i).getsSpecList().size()<=0)specClassList.remove(i);
            }
            goods.getGener().setSpecClassList(specClassList);
        }
        return goods;
    }

    @Transactional(readOnly = false)
    public SOrder savaOrderByShop(SShop sShop,SReceipt receipt,String addressId){
        if(sShop==null||receipt==null||StringUtils.isBlank(addressId))return null;
        SOrder order = new SOrder();
        order.preInsert();
        order.setDdbs("1");
        order.setReceipt(receipt);
        order.setAddress(new SAddress(addressId));
        order.setOrderNo(sShop.getOrderNo());
        order.setGoods(sShop.getGoods());
        order.setNum(sShop.getNum());
        order.setCostPrice(sShop.getGoods().getCostPrice());
        order.setPrice(sShop.getPrice());
        order.setSpecIds(sShop.getSpecIds());
        sOrderDao.insert(order);
        return order;
    }

}
