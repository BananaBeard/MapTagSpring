package main.java.com.maptag;

import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CityResponse;
import com.maxmind.geoip2.record.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class AddressService {
    //private static File database = new File(String.valueOf(this.getClass().getResource("GeoLite2-City.mmdb"));
    private static DatabaseReader reader;

    static {
        try {
            File dataBasePath = new File("GeoLite2-City.mmdb");
            if (!dataBasePath.exists()) {
                throw new FileNotFoundException("Failed to find file: " +
                        dataBasePath.getAbsolutePath());
            }
            reader = new DatabaseReader.Builder(dataBasePath).build();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public Location findCoordinatesOfIP(String address) {
        InetAddress ipAddress = null;
        Location location = null;
        try {
            ipAddress = InetAddress.getByName(address);
            CityResponse response = reader.city(ipAddress);
            location = response.getLocation();
        } catch (GeoIp2Exception e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return location;
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
