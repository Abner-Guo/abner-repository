package pers.guo.jvm.thread;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author: guochao.bj@fang.com
 * @createDate: 2023/5/17 18:29
 * DCL 双重检查 实际应用
 * 缓存用户信息，用一个hashmap做用户信息的缓存，key是userId
 */
public class DoubleCheckLockDemo {

    private volatile Map<Long,UserDO> map =new ConcurrentHashMap<Long,UserDO>();

    private Object mutex=new Object();


    public UserDO getUserDO(Long userid){

        UserDO userDO=map.get(userid);

        if (userDO==null){
            synchronized (mutex){

                if(map.get(userid)==null){
                    userDO=getUserFromDB(userid);
                    map.put(userid,userDO);
                }
            }

        }

        return userDO;
    }

    private UserDO getUserFromDB(Long userid){
        return null;
    }



}


class UserDO{
      private Long userId;
}
