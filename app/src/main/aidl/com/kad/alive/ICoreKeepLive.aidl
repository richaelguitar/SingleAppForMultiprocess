// ICoreKeepLive.aidl
package com.kad.alive;

// Declare any non-default types here with import statements
import com.kad.alive.entity.UserInfo;

interface ICoreKeepLive {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    UserInfo getUserInfo();
}
