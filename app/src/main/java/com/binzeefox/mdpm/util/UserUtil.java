package com.binzeefox.mdpm.util;

import android.support.design.widget.TextInputLayout;
import android.text.format.Time;
import android.util.Log;
import com.binzeefox.mdpm.db.User;
import org.litepal.crud.DataSupport;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * Created by tong.xiwen on 2017/6/13.
 */
public class UserUtil {

    /**
     * 注册验证与注册
     * 以下全部为TextInputLayout实例
     * @param userName 用户名
     * @param eMail 注册邮箱
     * @param psd 密码
     * @param psd_c 确认密码
     * @return 返回是否注册成功
     */
    public static boolean checkRegister(TextInputLayout userName, TextInputLayout eMail, TextInputLayout phone, TextInputLayout psd, TextInputLayout psd_c){

        String mUserName;
        String mEmail;
        String mPsd;

        if (userName.getEditText().getText() == null){
            userName.setError("用户名不能为空");
            return false;
        } else {
            mUserName = userName.getEditText().getText().toString();
        }
        List<User> users = DataSupport.where("userName = ?", mUserName).find(User.class);
        if (users != null){
            userName.setError("该用户名已存在");
        }


        if (eMail.getEditText().getText() == null){
            mEmail = "";
        } else {
            mEmail = eMail.getEditText().getText().toString();
        }

        if (psd.getEditText().getText() == null){
            psd.setError("请输入密码");
            return false;
        } else {
            mPsd = psd.getEditText().getText().toString();
        }

        if (psd_c.getEditText().getText() == null){
            psd_c.setError("二次输入密码不一致");
            return false;
        } else if (psd.getEditText().getText() != psd_c.getEditText().getText()){
            psd_c.setError("二次输入密码不一致");
        }

        String mPhone = getString(phone);
        mPhone = CommonUtil.md5(mPhone);
        mEmail = CommonUtil.md5(mEmail);
        DateFormat df = new SimpleDateFormat("HH:mm:ss");
        String currentTime =df.format(new Date());
        mPsd = encryptPsd(mPsd, currentTime); //加密并处理密码
        User user = new User();
        user.setPhone(mPhone);
        user.setUserName(mUserName);
        user.setEmail(mEmail);
        user.setMd5Psd(mPsd);
        user.setTime(currentTime);
        user.save();  //注册成功，存入数据库
        return true;
    }

    /**
     * 验证登陆
     * @param userName userName的TextInputLayout实例
     * @param psd psd的TextInputLayout实例
     * @return -1：用户名或密码错误； 0：验证失败并报错； 1：验证成功
     */

    public static final int LOGIN_VALID = -1;
    public static final int LOGIN_SUCCESS = 1;
    public static final int LOGIN_FAILED = 0;
    public static int checkLogin(TextInputLayout userName, TextInputLayout psd){
        String mUserName;
        String mPsd;

        if (userName.getEditText().getText().toString() != null || userName.getEditText().getText().toString() == ""){
            mUserName = userName.getEditText().getText().toString();
        } else {
            userName.setError("用户名不能为空");
            return LOGIN_FAILED;
        }

        List<User> users = DataSupport.where("userName = ?", mUserName).find(User.class);
        if (users.isEmpty()){
            // 用户名不存在
            return LOGIN_VALID;
        }

        User user = users.get(0);
        if (user.getTime() == null){
            CommonUtil.showToast(userName.getContext(), "未知错误");
            Log.e("LoginException:","key value not find");
        }
        if (psd.getEditText().getText().toString() != null){
            mPsd = psd.getEditText().getText().toString();
            mPsd = encryptPsd(mPsd, user.getTime()); // 获取加密密码
        } else {
            psd.setError("请输入密码");
            return LOGIN_FAILED;
        }

        if (!Objects.equals(user.getMd5Psd(), mPsd)){ // 验证数据库的密码MD5码与输入的密码获取的MD5码是否一致
            // 密码错误
            return LOGIN_VALID;
        }

        // 验证成功
        return LOGIN_SUCCESS;
    }


    /**
     * 获取用户ID
     * @param userName 用户名
     * @return 返回用户ID
     */
    public static int getUserId(String userName){

        List<User> users = DataSupport.where("userName = ?", userName).find(User.class);
        User user = users.get(0);
        return user.getId();
    }

    /**
     * 获取String
     * @param inputLayout 文本框
     * @return 字符串
     */
    public static String getString(TextInputLayout inputLayout) {
        String text = "";
        if (inputLayout.getEditText().getText() != null) {
            text = inputLayout.getEditText().getText().toString();
        }
        return text;
    }

    /**
     * 密码加密
     * @param psd 密码
     * @param time 系统时间
     * @return 加密密码
     */
    private static String encryptPsd(CharSequence psd, CharSequence time){

        StringBuilder context = new StringBuilder();
        String result = CommonUtil.md5(context.append(psd).append(time).toString());
        return result;
    }

    public static final String PASSWORD_RESET = "user_password_reset";
    public static final String PASSWORD_CHANGE = "user_password_change";
    public static final String EMAIL_CHANGE = "user_email_change";
    public static final String PHONE_CHANGE = "user_phone_change";
}
