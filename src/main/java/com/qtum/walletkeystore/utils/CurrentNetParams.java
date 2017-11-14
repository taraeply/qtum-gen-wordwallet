package com.qtum.walletkeystore.utils;

import org.bitcoinj.core.NetworkParameters;

public class CurrentNetParams {

    public  CurrentNetParams(){}

    public static NetworkParameters getNetParams(){
        return QtumMainNetParams.get();
    }

}
