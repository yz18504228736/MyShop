package com.yz.entity;

import com.yz.dao.AddaddressDao;
import com.yz.dao.Impl.AddaddressDaoImpl;

import java.util.ArrayList;
import java.util.List;

public class AddressManger {
    private static List<Address> queryAddress;
    // Manager内部自动地获取数据库当中的内容，并填充到addressTypes的静态成员内部
    static {
        AddaddressDao dao = new AddaddressDaoImpl();
        queryAddress = dao.queryAddress();
    }
//    根据uid查询
public static List<Address> queryAll(String uid) {
    List<Address> result = new ArrayList<>();
    for (Address address : queryAddress) {
        if (uid.equals(address.getUid())) {
            result.add(address);
        }
    }
    return result;
}
//插入数据
    public static boolean addAddress(String uid, String username, String phone, String detail) {
        AddaddressDao dao = new AddaddressDaoImpl();
        int result= dao.addAddress(uid, username, phone, detail);

        queryAddress = dao.queryAddress();
        return result==1?true:false;
    }
public static List<Address>addressAll(){
        return queryAddress;
}

}
