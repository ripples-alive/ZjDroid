package com.android.reverse.hook;

import java.lang.reflect.Member;
import java.util.Arrays;

import com.android.reverse.util.Logger;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;

public class XposeHookHelperImpl implements HookHelperInterface {

    @Override
    public void hookMethod(Member method, XC_MethodHook callback) {
        // TODO Auto-generated method stub
        if (method != null) {
            XposedBridge.hookMethod(method, callback);
        } else {
            Logger.log("method try to hook not found");
            try {
                throw new Exception();
            } catch (Exception e) {
                Logger.log(Arrays.toString(e.getStackTrace()));
            }
        }
    }

}
