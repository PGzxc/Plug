# 插件化框架集成-基于360插件框架DroidPlug


## 使用方法：

#### 集成

在host中集成Droid Plugin项目非常简单：

我们只需要将Droid Plugin当作一个lib工程应用到主项目中，然后：

在AndroidManifest.xml中使用插件的com.morgoo.droidplugin.PluginApplication：

 <application android:name="com.morgoo.droidplugin.PluginApplication" 
 			 android:label="@string/app_name"
 			 android:icon="@drawable/ic_launcher" 
如果你使用自定义的Application，那么你需要在自定义的Application class onCreate和attachBaseContext方法中添加如下代码：

 @Override
 public void onCreate() {
     super.onCreate();
     //这里必须在super.onCreate方法之后，顺序不能变
     PluginHelper.getInstance().applicationOnCreate(getBaseContext());
 }
   
 @Override
 protected void attachBaseContext(Context base) {
     PluginHelper.getInstance().applicationAttachBaseContext(base);
     super.attachBaseContext(base);
 }
将插件中Libraries\DroidPlugin\AndroidManifest.xml中所有的provider对应的authorities修改成自己的，默认为com.morgoo.droidplugin_stub_P00，如下：

<provider
    android:name="com.morgoo.droidplugin.stub.ContentProviderStub$StubP00"
    android:authorities="com.morgoo.droidplugin_stub_P00"
    android:exported="false"
    android:label="@string/stub_name_povider" />
可以修改为自己的包名，如: com.example.droidplugin_stub_P00 防止跟其它本插件使用者冲突：

<provider
    android:name="com.morgoo.droidplugin.stub.ContentProviderStub$StubP00"
    android:authorities="com.example.droidplugin_stub_P00"
    android:exported="false"
    android:label="@string/stub_name_povider" />
并且修改PluginManager.STUB_AUTHORITY_NAME 为你的值:

PluginManager.STUB_AUTHORITY_NAME="com.example.droidplugin_stub"
集成完成。

####  安装、卸载插件：

安装、更新插件,使用如下方法：

 int PluginManager.getInstance().installPackage(String filepath, int flags)
说明：安装插件到插件系统中，filepath为插件apk路径，flags可以设置为0，如果要更新插件，则设置为PackageManagerCompat.INSTALL_REPLACE_EXISTING返回值及其含义请参见PackageManagerCompat类中的相关字段。

卸载插件，使用如下方法：

 int PluginManager.getInstance().deletePackage(String packageName,int flags);
说明：从插件系统中卸载某个插件，packageName传插件包名即可，flags传0。

启动插件：启动插件的Activity、Service等都和你启动一个以及安装在系统中的app一样，使用系统提供的相关API即可。组件间通讯也是如此。
