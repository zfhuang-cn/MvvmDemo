package com.ant.user;

import com.ant.common.IModuleInit;
import com.ant.core.BaseApplication;

/**
 * 应用模块:
 * <p>
 * 类描述:
 * <p>
 *
 * @author: zfhuang
 * @date: 2021/5/28
 */
public class UserModuleInit  implements IModuleInit {
    @Override
    public boolean onInitAhead(BaseApplication application) {
        return false;
    }

    @Override
    public boolean onInitLow(BaseApplication application) {
        return false;
    }
}