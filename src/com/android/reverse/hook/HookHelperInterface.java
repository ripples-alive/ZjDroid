package com.android.reverse.hook;

import de.robv.android.xposed.XC_MethodHook;

import java.lang.reflect.Member;


public interface HookHelperInterface {
	
	public abstract void hookMethod(Member method, XC_MethodHook callback);

}
