GestureLock
===========

an Android Style GestureLock that auto locks activity.
<p><p>
Based on 7heaven's <a href="https://github.com/7heaven/GestureLock">GestureLock</a>, 
GestureLockUtils was added to store gesture in sharedpreferences, and it also can help config GestureView not draw gesture track while unlocking like Alipay Wallet.<p> 
If your activity extends AutoLockActionBarActivity, it will automatically lock itself up while it is covered or minimized.<p>


基于7heaven的<a href="https://github.com/7heaven/GestureLock">GestureLock</a>项目，加入了一个 GestureLockUtils工具用来存储手势，同事这个工具也可以用来设置隐藏解锁手势。<p>
如果你的Activity 继承于AutoLockActionBarActivity，则可以实现支付宝钱包的那种自动上锁。