package tech.michaelx.networkwatcher.lib;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

import static tech.michaelx.networkwatcher.annotation.constant.NetworkTypeEnum.NETWORK_2G;
import static tech.michaelx.networkwatcher.annotation.constant.NetworkTypeEnum.NETWORK_3G;
import static tech.michaelx.networkwatcher.annotation.constant.NetworkTypeEnum.NETWORK_4G;
import static tech.michaelx.networkwatcher.annotation.constant.NetworkTypeEnum.NETWORK_NO;
import static tech.michaelx.networkwatcher.annotation.constant.NetworkTypeEnum.NETWORK_UNKNOWN;
import static tech.michaelx.networkwatcher.annotation.constant.NetworkTypeEnum.NETWORK_WIFI;

/**
 * @author MichaelX
 * @version 1.0
 * @since 2019/5/22
 */
public class NetworkUtils {
    private static final int NETWORK_TYPE_GSM = 16;
    private static final int NETWORK_TYPE_TD_SCDMA = 17;
    private static final int NETWORK_TYPE_IWLAN = 18;

    /**
     * 运营商类型:未知
     */
    private static final int OPERATOR_UNKNOWN = 0;
    /**
     * 运营商类型:中国移动
     */
    private static final int OPERATOR_CHINA_MOBILE = 1;
    /**
     * 运营商类型:中国电信
     */
    private static final int OPERATOR_CHINA_TELECOM = 2;
    /**
     * 运营商类型:中国联通
     */
    private static final int OPERATOR_CHINA_UNICOM = 3;
    /**
     * 运营商类型:其他
     */
    private static final int OPERATOR_OTHER = 99;

    /**
     * 打开网络设置界面
     * <p>3.0以下打开设置界面</p>
     *
     * @param context 上下文
     */
    public static void openWirelessSettings(Context context) {
        if (android.os.Build.VERSION.SDK_INT > 10) {
            context.startActivity(new Intent(android.provider.Settings.ACTION_SETTINGS));
        } else {
            context.startActivity(new Intent(android.provider.Settings.ACTION_WIRELESS_SETTINGS));
        }
    }

    /**
     * 获取活动网络信息
     *
     * @param context 上下文
     * @return NetworkInfo
     */
    private static NetworkInfo getActiveNetworkInfo(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm == null ? null : cm.getActiveNetworkInfo();
    }

    /**
     * 判断网络是否可用
     * <p>需添加权限 {@code <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>}</p>
     *
     * @param context 上下文
     * @return {@code true}: 可用<br>{@code false}: 不可用
     */
    public static boolean isAvailable(Context context) {
        NetworkInfo info = getActiveNetworkInfo(context);
        return info != null && info.isAvailable();
    }

    /**
     * 判断网络是否连接
     * <p>需添加权限 {@code <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>}</p>
     *
     * @param context 上下文
     * @return {@code true}: 是<br>{@code false}: 否
     */
    public static boolean isConnected(Context context) {
        NetworkInfo info = getActiveNetworkInfo(context);
        return info != null && info.isConnected();
    }

    /**
     * 判断网络是否是4G
     * <p>需添加权限 {@code <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>}</p>
     *
     * @param context 上下文
     * @return {@code true}: 是<br>{@code false}: 不是
     */
    public static boolean is4G(Context context) {
        NetworkInfo info = getActiveNetworkInfo(context);
        return info != null && info.isAvailable() && info.getSubtype() == TelephonyManager.NETWORK_TYPE_LTE;
    }

    /**
     * 判断wifi是否连接状态
     * <p>需添加权限 {@code <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>}</p>
     *
     * @param context 上下文
     * @return {@code true}: 连接<br>{@code false}: 未连接
     */
    public static boolean isWifiConnected(Context context) {
        NetworkInfo info = getActiveNetworkInfo(context);
        return info != null && info.getType() == ConnectivityManager.TYPE_WIFI;
    }

    /**
     * 获取移动网络运营商名称
     * <p>如中国联通、中国移动、中国电信</p>
     *
     * @param context 上下文
     * @return 移动网络运营商名称
     */
    public static String getNetworkOperatorName(Context context) {
        TelephonyManager tm = (TelephonyManager) context
                .getSystemService(Context.TELEPHONY_SERVICE);
        return tm != null ? tm.getNetworkOperatorName() : null;
    }

    /**
     * 获取当前运营商类型
     */
    public static int getSimOperatorInfo(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);

        if (telephonyManager == null) {
            return OPERATOR_UNKNOWN;
        }
        String operatorString = telephonyManager.getSimOperator();

        if (TextUtils.isEmpty(operatorString)) {
            return OPERATOR_UNKNOWN;
        }

        if (operatorString.equals("46000") || operatorString.equals("46002")
                || operatorString.equals("46007") || operatorString.equals("46008")) {
            // 中国移动
            return OPERATOR_CHINA_MOBILE;
        } else if (operatorString.equals("46001") || operatorString.equals("46006")
                || operatorString.equals("46009")) {
            // 中国联通
            return OPERATOR_CHINA_UNICOM;
        } else if (operatorString.equals("46003") || operatorString.equals("46005")
                || operatorString.equals("46011")) {
            // 中国电信
            return OPERATOR_CHINA_TELECOM;
        }

        return OPERATOR_OTHER;
    }

    /**
     * 获取移动终端类型
     *
     * @param context 上下文
     * @return 手机制式
     * <ul>
     * <li>{@link TelephonyManager#PHONE_TYPE_NONE } : 0 手机制式未知</li>
     * <li>{@link TelephonyManager#PHONE_TYPE_GSM  } : 1 手机制式为GSM，移动和联通</li>
     * <li>{@link TelephonyManager#PHONE_TYPE_CDMA } : 2 手机制式为CDMA，电信</li>
     * <li>{@link TelephonyManager#PHONE_TYPE_SIP  } : 3</li>
     * </ul>
     */
    public static int getPhoneType(Context context) {
        TelephonyManager tm = (TelephonyManager) context
                .getSystemService(Context.TELEPHONY_SERVICE);
        return tm != null ? tm.getPhoneType() : -1;
    }

    /**
     * 获取当前的网络类型(WIFI,2G,3G,4G)
     * <p>需添加权限 {@code <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>}</p>
     *
     * @param context 上下文
     * @return 网络类型
     * <ul>
     * <li>{@link NETWORK_WIFI   } = 1;</li>
     * <li>{@link NETWORK_4G     } = 4;</li>
     * <li>{@link NETWORK_3G     } = 3;</li>
     * <li>{@link NETWORK_2G     } = 2;</li>
     * <li>{@link NETWORK_UNKNOWN} = 5;</li>
     * <li>{@link NETWORK_NO     } = -1;</li>
     * </ul>
     */
    public static int getNetworkType(Context context) {
        int netType = NETWORK_NO;
        NetworkInfo info = getActiveNetworkInfo(context);
        if (info != null && info.isAvailable()) {

            if (info.getType() == ConnectivityManager.TYPE_WIFI) {
                netType = NETWORK_WIFI;
            } else if (info.getType() == ConnectivityManager.TYPE_MOBILE) {
                switch (info.getSubtype()) {

                    case NETWORK_TYPE_GSM:
                    case TelephonyManager.NETWORK_TYPE_GPRS:
                    case TelephonyManager.NETWORK_TYPE_CDMA:
                    case TelephonyManager.NETWORK_TYPE_EDGE:
                    case TelephonyManager.NETWORK_TYPE_1xRTT:
                    case TelephonyManager.NETWORK_TYPE_IDEN:
                        netType = NETWORK_2G;
                        break;

                    case NETWORK_TYPE_TD_SCDMA:
                    case TelephonyManager.NETWORK_TYPE_EVDO_A:
                    case TelephonyManager.NETWORK_TYPE_UMTS:
                    case TelephonyManager.NETWORK_TYPE_EVDO_0:
                    case TelephonyManager.NETWORK_TYPE_HSDPA:
                    case TelephonyManager.NETWORK_TYPE_HSUPA:
                    case TelephonyManager.NETWORK_TYPE_HSPA:
                    case TelephonyManager.NETWORK_TYPE_EVDO_B:
                    case TelephonyManager.NETWORK_TYPE_EHRPD:
                    case TelephonyManager.NETWORK_TYPE_HSPAP:
                        netType = NETWORK_3G;
                        break;

                    case NETWORK_TYPE_IWLAN:
                    case TelephonyManager.NETWORK_TYPE_LTE:
                        netType = NETWORK_4G;
                        break;
                    default:

                        String subtypeName = info.getSubtypeName();
                        if (subtypeName.equalsIgnoreCase("TD-SCDMA")
                                || subtypeName.equalsIgnoreCase("WCDMA")
                                || subtypeName.equalsIgnoreCase("CDMA2000")) {
                            netType = NETWORK_3G;
                        } else {
                            netType = NETWORK_UNKNOWN;
                        }
                        break;
                }
            } else {
                netType = NETWORK_UNKNOWN;
            }
        }
        return netType;
    }

    /**
     * 是否连接的手机移动网络
     *
     * @param context Android上下文对象
     * @return 是否手机移动网络
     */
    public static boolean isMobile(Context context) {
        if (!isAvailable(context)) {
            return false;
        }
        int type = getNetworkType(context);
        if (type == NETWORK_2G || type == NETWORK_3G || type == NETWORK_4G) {
            return true;
        }
        return false;
    }
}
