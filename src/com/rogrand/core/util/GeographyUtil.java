package com.rogrand.core.util;










public class GeographyUtil
{
  private static double EARTH_RADIUS = 6317.137D;







  private static double rad(double lngOrLat) {
    return lngOrLat * Math.PI / 180.0D;
  }










  public static double getDistance(double latitude1, double longitude1, double latitude2, double longitude2) {
    double radLat1 = rad(latitude1);
    double radLat2 = rad(latitude2);
    double a = radLat1 - radLat2;
    double b = rad(longitude1) - rad(longitude2);
    double s = 2.0D * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2.0D), 2.0D) + Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(b / 2.0D), 2.0D)));

    s *= EARTH_RADIUS;
    s = Math.round(s * 100.0D) / 100.0D;
    return s;
  }










  public static double[] getLat(double latitude, double longitude, double distance) {
    double lowerLat = getLowLat(longitude, latitude, distance);

    double higherLat = getHigherLat(longitude, latitude, distance);
    return new double[] { lowerLat, higherLat };
  }










  public static double[] getLng(double latitude, double longitude, double distance) {
    double lowerLng = getLowLng(longitude, latitude, distance);

    double higherLng = getHigherLng(longitude, latitude, distance);
    return new double[] { lowerLng, higherLng };
  }









  private static double getHigherLng(double longitude, double latitude, double distance) {
    double d = EARTH_RADIUS * Math.cos(latitude);
    double radLng = distance / Math.abs(d);
    return longitude + radLng * 180.0D / Math.PI;
  }









  private static double getLowLng(double longitude, double latitude, double distance) {
    double d = EARTH_RADIUS * Math.cos(latitude);
    double radLng = distance / Math.abs(d);
    return longitude - radLng * 180.0D / Math.PI;
  }









  private static double getHigherLat(double longitude, double latitude, double distance) {
    double hLat = (Math.sin(Math.abs(latitude) * Math.PI / 180.0D) * EARTH_RADIUS + distance) / EARTH_RADIUS;
    double radHlat = Math.asin(hLat);
    double higherLat = radHlat * 180.0D / Math.PI;
    return higherLat;
  }









  private static double getLowLat(double longitude, double latitude, double distance) {
    double lLat = (Math.sin(Math.abs(latitude) * Math.PI / 180.0D) * EARTH_RADIUS - distance) / EARTH_RADIUS;
    double radLlat = Math.asin(lLat);
    return radLlat * 180.0D / Math.PI;
  }
}


/* Location:              D:\file\project\tuozhanbao-manage\WEB-INF\classes\!\com.rogrand\cor\\util\GeographyUtil.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */