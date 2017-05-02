package com.example.georgi.shop.Helpers;

import com.squareup.otto.Bus;

/**
 * Created by Georgi on 02-May-17.
 */

public class GlobalBus {

    private static Bus bus;

    public static Bus getBus(){
        if(bus == null)
            bus = new Bus();
        return bus;
    }
}
