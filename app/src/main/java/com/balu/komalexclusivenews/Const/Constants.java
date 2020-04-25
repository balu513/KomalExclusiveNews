package com.balu.komalexclusivenews.Const;
//public static String API_KEY_NEWS = "http://api.weatherstack.com/current?access_key=d292c9d0d539f743f7ccbc10e496cbd3&query=New%20York"

public class Constants {

    // ballibalakrishna2334 & Afbb@513
    //https://newsapi.org/docs/endpoints/top-headlines
    public static class News {
        public static String API_KEY = "be0a5563a77c48d28f85e45ddf8a8a8e";
        public static String URL_PREFIX = "https://newsapi.org/v2/";
    }

    // ballibalakrishna2334 & Afbb@513
    //https://weatherstack.com/documentation
    //http://api.weatherstack.com/current?access_key=d292c9d0d539f743f7ccbc10e496cbd3&query=Hyderabad
    public static class Whether {
        public static String URL_PREFIX_NEWS = "http://api.weatherstack.com/";
        public static String API_KEY_NEWS = "d292c9d0d539f743f7ccbc10e496cbd3";

    }

    // komal mail id
    //https://www.cricapi.com/member-test.aspx
    public static class Cricket{
        public static String URL_PREFIX_CRICKET = "https://cricapi.com/api/";
        public static String API_KEY_CRICKET = "xtHqJY7jB6PeJzpRycNnMmPqxfp1";

    }

    public static class Covid{
        public static String URL_PREFIX_COVID_WORLD = "https://api.covid19api.com/";
        public static String URL_PREFIX_COVID_IND = "https://www.mohfw.gov.in/dashboard/data/";
    }
}
