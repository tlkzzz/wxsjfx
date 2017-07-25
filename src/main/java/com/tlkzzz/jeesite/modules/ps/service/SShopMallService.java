package com.tlkzzz.jeesite.modules.ps.service;

import com.tlkzzz.jeesite.common.service.BaseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;

/**
 * Created by WT on 2017-07-25.
 */
@Service
@Transactional(readOnly = true)
public class SShopMallService  extends BaseService {

    public String random(){
        Random r = new Random();
        StringBuffer s = new StringBuffer();
        for(int i=0;i<6;i++) {
            int num = r.nextInt(10);
            s.append(num);
        }
        return s.toString();
    }

}
