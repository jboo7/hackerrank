package datastorage;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Map;

public class DataStorage {
    // first
    // second
    Map<DataCenter, List<Credentials>> dataCenterToCredentialsMap;

    @RequiredArgsConstructor
    @EqualsAndHashCode
    @Getter
    public static class DataCenter {
        private final String name;
    }

    @RequiredArgsConstructor
    @EqualsAndHashCode
    @Getter
    public static class Credentials {
        private final String username;
        private final String encryptedPassword;
    }

    @Data
    public static class DataCenterCredentials {
        private final String name;
        private final String userName;
        private final String encryptedPassword;
    }
}
