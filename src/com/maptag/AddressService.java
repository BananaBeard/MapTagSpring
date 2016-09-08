package com.maptag;

import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CityResponse;
import com.maxmind.geoip2.record.Location;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AddressService {
    private final static File database = new File("GeoLite2-City.mmdb");
    private static DatabaseReader reader;

    static {
        try {
            reader = new DatabaseReader.Builder(database).build();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ServerCoordinates getCoordinatesOfIP(String adress) throws IOException, GeoIp2Exception {
        InetAddress ipAddress = InetAddress.getByName(adress);
        CityResponse response = reader.city(ipAddress);
        Location location = response.getLocation();

        return new ServerCoordinates(location.getLatitude(), location.getLongitude());
    }

    /*public static ArrayList<ServerCoordinates> getCoordinatesFromFolder(String addressesFolder) {
        ArrayList<ServerCoordinates> resultCoordinates = new ArrayList<>();
        List<Path> a = new ArrayList<>();

        try {
            List<File> adddressesFiles = Files.walk(Paths.get(addressesFolder))
                    .filter(Files::isRegularFile)
                    .map(Path::toFile)
                    .collect(Collectors.toList());

            for (File file : adddressesFiles) {

                List<String> lines = Files.readAllLines(file.toPath(), Charset.forName("UTF-8"));

                for (String line : lines) {
                    resultCoordinates.add(MapTagUtil.getCoordinatesOfIP(line));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (GeoIp2Exception e) {
            e.printStackTrace();
        }
        return resultCoordinates;
    }*/
}
