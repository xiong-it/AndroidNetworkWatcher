package tech.michaelx.networkwatcher.annotation;

import android.support.annotation.IntDef;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static tech.michaelx.networkwatcher.annotation.constant.NetworkTypeEnum.NETWORK_NO;
import static tech.michaelx.networkwatcher.annotation.constant.NetworkTypeEnum.NETWORK_WIFI;
import static tech.michaelx.networkwatcher.annotation.constant.NetworkTypeEnum.NETWORK_2G;
import static tech.michaelx.networkwatcher.annotation.constant.NetworkTypeEnum.NETWORK_3G;
import static tech.michaelx.networkwatcher.annotation.constant.NetworkTypeEnum.NETWORK_4G;
import static tech.michaelx.networkwatcher.annotation.constant.NetworkTypeEnum.NETWORK_MOBILE;
import static tech.michaelx.networkwatcher.annotation.constant.NetworkTypeEnum.NETWORK_UNKNOWN;

/**
 * @since 2019-10-16
 */
@IntDef({NETWORK_NO, NETWORK_WIFI, NETWORK_2G, NETWORK_3G, NETWORK_4G, NETWORK_MOBILE, NETWORK_UNKNOWN})
@Retention(RetentionPolicy.SOURCE)
@Target({ElementType.METHOD, ElementType.PARAMETER})
public @interface NetworkType {
}
