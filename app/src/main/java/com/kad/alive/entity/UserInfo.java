package com.kad.alive.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by xiaowenwu on 2017/12/19.
 */

public class UserInfo implements Parcelable {
    private int userId;
    private String userName;
    private String token;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.userId);
        dest.writeString(this.userName);
        dest.writeString(this.token);
    }

    public UserInfo() {
    }

    public UserInfo(int userId, String userName, String token) {
        this.userId = userId;
        this.userName = userName;
        this.token = token;
    }

    protected UserInfo(Parcel in) {
        this.userId = in.readInt();
        this.userName = in.readString();
        this.token = in.readString();
    }

    public static final Creator<UserInfo> CREATOR = new Creator<UserInfo>() {
        @Override
        public UserInfo createFromParcel(Parcel source) {
            return new UserInfo(source);
        }

        @Override
        public UserInfo[] newArray(int size) {
            return new UserInfo[size];
        }
    };


    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", token='" + token + '\'' +
                '}';
    }
}


