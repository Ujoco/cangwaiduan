package com.shuangtu.prison.common.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.text.TextUtils;

import com.shuangtu.prison.common.constant.Global;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import java.util.Locale;

public class NetworkUtil {

    public static final int IPADDRESS = 1;
    public static final int MACADDRESS = 2;

    private static final int NETWORK_WIFI = 101;
    private static final int NETWORK_ETHERNET = 102;
    private static final int NETWORK_MOBILE = 103;
    private static final int NETWORK_UNKNOWN = 108;
    private static final int NETWORK_NONETWORK = 109;

    public static String getNetwrokInfo(int tag) {

        ConnectivityManager manager = (ConnectivityManager) Global.mContext.getSystemService(Context.CONNECTIVITY_SERVICE);

        int type = getType(manager);

        return getInfo(tag, type);
    }

    public static int getType(ConnectivityManager manager) {
        int type = NETWORK_NONETWORK;
        if (manager != null) {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                List<Network> networkList = Arrays.asList(manager.getAllNetworks());
                if (networkList != null && networkList.size() != 0) {
                    for (Network network : networkList) {
                        NetworkInfo info = manager.getNetworkInfo(network);
                        if (info.getState().equals(NetworkInfo.State.CONNECTED)) {
                            switch (info.getType()) {
                                case ConnectivityManager.TYPE_WIFI:
                                    type = NETWORK_WIFI;
                                    break;
                                case ConnectivityManager.TYPE_ETHERNET:
                                    type = NETWORK_ETHERNET;
                                    break;
                                default:
                                    type = NETWORK_UNKNOWN;
                                    break;
                            }
                        }
                    }
                }
            } else {
                NetworkInfo[] info = manager.getAllNetworkInfo();
                if (info != null) {
                    for (int i = 0; i < info.length; i++) {
                        if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                            NetworkInfo netWorkInfo = info[i];
                            if (netWorkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
                                type = NETWORK_WIFI;
                            } else if (netWorkInfo.getType() == ConnectivityManager.TYPE_ETHERNET) {
                                type = NETWORK_ETHERNET;
                            } else {
                                type = NETWORK_UNKNOWN;
                            }
                        }
                    }
                } else {
                    type = NETWORK_NONETWORK;
                }
            }
        }
        return type;
    }

    private static String getInfo(int tag, int type) {
        String info = "";
        switch (type) {
            case NETWORK_WIFI:
                info = obtainWifi(tag);
                break;
            case NETWORK_ETHERNET:
                info = obtainEthernet(tag);
                break;
            case NETWORK_UNKNOWN:
                info = "unknown network";
                break;
            case NETWORK_NONETWORK:
                info = "no network";
                break;
        }
        return info;
    }

    private static String obtainEthernet(int tag) {

        switch (tag) {
            case IPADDRESS:
                return getEthernetIpAddress();
            case MACADDRESS:
                String mac = getEthernetMacaddress();
                if (TextUtils.isEmpty(mac)) {
                    mac = obtainWifi(tag);
                }
                return mac;
        }
        return "unknown";
    }

    private static String obtainWifi(int tag) {
        WifiManager wifi = (WifiManager) Global.mContext.getSystemService(Context.WIFI_SERVICE);
        WifiInfo info = wifi.getConnectionInfo();
        switch (tag) {
            case IPADDRESS:
                return info.getIpAddress() + "";
            case MACADDRESS:
                return info.getMacAddress() + "";
        }
        return "unknown";
    }


    public static String getEthernetIpAddress() {
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface
                    .getNetworkInterfaces(); en.hasMoreElements(); ) {
                NetworkInterface intf = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = intf
                        .getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress()) {
                        return inetAddress.getHostAddress().toString();
                    }
                }
            }
        } catch (SocketException ex) {
            System.out.println("WifiPreference IpAddress" + ex.toString());
        }
        return null;
    }


    /**
     * 获取Ethernet的MAC地址
     *
     * @return
     */
    private static String getEthernetMacaddress() {
        try {
            return loadFileAsString("/sys/class/net/eth0/address").toUpperCase(Locale.ENGLISH).substring(0, 17);
        } catch (IOException e) {
            return null;
        }
    }

    private static String loadFileAsString(String filePath) throws java.io.IOException {
        StringBuffer fileData = new StringBuffer(1000);
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        char[] buf = new char[1024];
        int numRead = 0;
        while ((numRead = reader.read(buf)) != -1) {
            String readData = String.valueOf(buf, 0, numRead);
            fileData.append(readData);
        }
        reader.close();
        return fileData.toString();
    }




}
