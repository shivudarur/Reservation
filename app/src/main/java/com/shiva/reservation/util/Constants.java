package com.shiva.reservation.util;

/**
 * Created by shivananda.darura on 23/08/17.
 */

public class Constants {

    //Dagger Dependency unique names
    public static final String BASE_URL_DEPENDENCY = "baseUrl";

    //Network constants
    public static final int TIMEOUT_CONNECT = 30;   //In seconds
    public static final int TIMEOUT_READ = 30;   //In seconds
    public static final int GROUP_200 = 2;
    public static final int VALUE_100 = 100;
    public static final int SUCCESS_RESPONSE = GROUP_200 * VALUE_100;

    //Errors
    public static final int ERROR_UNDEFINED = -1;
    public static final int ERROR_DB = -1;
}
