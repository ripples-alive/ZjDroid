# ZjDroid

Android app dynamic reverse tool based on Xposed framework.

## 一、ZjDroid工具介绍

ZjDroid 是基于 Xposed Framewrok 的动态逆向分析模块，逆向分析者可以通过 ZjDroid 完成以下工作：

1. DEX 文件的内存 dump
1. 基于 Dalvik 关键指针的内存 BackSmali，有效破解主流加固方案
1. 敏感 API 的动态监控
1. 指定内存区域数据 dump
1. 获取应用加载 DEX 信息
1. 获取指定 DEX 文件加载类信息
1. dump Dalvik java 堆信息
1. 在目标进程动态运行 lua 脚本

## 二、ZjDroid相关命令

1. 获取APK当前加载DEX文件信息：
`adb shell am broadcast -a com.zjdroid.invoke --ei target pid --es cmd '{"action":"dump_dexinfo"}'`

1. 获取指定 DEX 文件包含可加载类名：
`adb shell am broadcast -a com.zjdroid.invoke --ei target pid --es cmd '{"action":"dump_class","dexpath":"*****"}'`

1. 根据 Dalvik 相关内存指针动态反编译指定 DEX，并以文件形式保存。
`adb shell am broadcast -a com.zjdroid.invoke --ei target pid --es cmd '{"action":"backsmali","dexpath":"*****"}'`

    该方式可以脱壳目前大部分流行的加固防护。(由于手机性能问题，运行较忙)

    例外情况：

    由于 ApkProtect 特定防修改检测，需要做如下修改即可解固该保护：

    1. 在设备上创建特定目录（如 /data/local）并 chmod 为 777
    1. 复制 zjdroid.apk 到该目录，并修改文件名为 zjdroid.jar
    1. 修改 /data/data/de.robv.android.xposed.installer/conf/modules.list 模块代码文件修改为 "zjdroid.jar" 重启设备即可

1. Dump 指定 DEX 内存中的数据并保存到文件（数据为 odex 格式，可在 pc 上反编译）。
`adb shell am broadcast -a com.zjdroid.invoke --ei target pid --es cmd '{"action":"dump_dex","dexpath":"*****"}'`

1. Dump 指定内存空间区域数据到文件
`adb shell am broadcast -a com.zjdroid.invoke --ei target pid --es cmd '{"action":"dump_mem","start":1234567,"length":123}'`

1. Dump Dalvik 堆栈信息到文件，文件可以通过 java heap 分析工具分析处理。
`adb shell am broadcast -a com.zjdroid.invoke --ei target pid --es cmd '{"action":"dump_heap"}'`

1. 运行时动态调用Lua脚本，该功能可以通过Lua脚本动态调用java代码。

    使用场景：

    * 可以动态调用解密函数，完成解密。

    * 可以动态触发特定逻辑。

    `adb shell am broadcast -a com.zjdroid.invoke --ei target pid --es cmd '{"action":"invoke","filepath":"****"}'`

    luajava相关使用方法：
http://www.keplerproject.org/luajava/

1. 敏感 API 调用监控


## 三、相关命令执行结果查看：

1. 命令执行结果：
`adb shell logcat -s zjdroid-shell-{package name}`

1. 敏感 API 调用监控输出结果：
`adb shell logcat -s zjdroid-apimonitor-{package name}`
