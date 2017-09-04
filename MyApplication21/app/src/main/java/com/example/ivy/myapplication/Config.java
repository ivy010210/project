package com.example.ivy.myapplication;

/**
 * Created by user on 2017/7/17.
 *
 * 設定string
 */

public class Config {

    //各種功能的網址
    public static final String URL_ADD="http://140.117.71.114/employee/addEmp.php";
    public static final String URL_GET_ALL = "http://140.117.71.114/beacon/getAllGroup.php?found_uEmail=";
    public static final String URL_GET_EMP = "http://140.117.71.114/beacon/try.php?gId=";
    public static final String URL_UPDATE_EMP = "http://140.117.71.114/employee/updateEmp.php";
    public static final String URL_DELETE_EMP = "http://140.117.71.114/employee/deleteEmp.php?id=";



    //employee id to pass with intent
    public static final String gId = "gId";
}
