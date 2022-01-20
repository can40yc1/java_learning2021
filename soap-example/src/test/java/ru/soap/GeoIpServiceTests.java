package ru.soap;

import com.lavasoft.GeoIPService;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class GeoIpServiceTests {

    @Test
    public void testMyIp() {
        String ipLocation = new GeoIPService().getGeoIPServiceSoap12().getIpLocation("178.71.129.42");
        assertEquals(ipLocation, "<GeoIP><Country>RU</Country><State>66</State></GeoIP>");
    }

    @Test
    public void testInvalidIp() {
        String ipLocation = new GeoIPService().getGeoIPServiceSoap12().getIpLocation("178.71.129.xxx");
        assertEquals(ipLocation, "<GeoIP><Country>US</Country><State>CA</State></GeoIP>");
    }
}
