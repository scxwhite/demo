package com.thrcat.conf;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;



/**
 * @author scx
 */
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        long millis = System.currentTimeMillis();

        this.setInsertFieldValByName("gmtCreate", millis, metaObject);
        this.setUpdateFieldValByName("gmtModified", millis, metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.setUpdateFieldValByName("gmtModified", System.currentTimeMillis(), metaObject);
    }
}
