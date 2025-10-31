package br.ufscar.pooa.cinema_api.utils;

public class DistanceCalculator {

    private static final double EARTH_RADIUS_KM = 6371.01;

    private DistanceCalculator() {}

    public static double equirectangularDistance(double lat1, double lon1,
        double lat2, double lon2) {
        lat1 = Math.toRadians(lat1);
        lon1 = Math.toRadians(lon1);
        lat2 = Math.toRadians(lat2);
        lon2 = Math.toRadians(lon2);

        double x = (lon2 - lon1) * Math.cos((lat1 + lat2) / 2);
        double y = lat2 - lat1;

        return Math.sqrt(x * x + y * y) * EARTH_RADIUS_KM;
    }
}

