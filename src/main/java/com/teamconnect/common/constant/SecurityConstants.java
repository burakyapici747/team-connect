package com.teamconnect.common.constant;

import java.util.List;
import java.util.Map;

public final class SecurityConstants {
    private SecurityConstants() {
    }

    public static final class Endpoints {
        private Endpoints() {
        }

        public static final class Public {
            private Public() {
            }

            public static final Map<String, List<String>> ALLOWED_ENDPOINTS = Map.of(
                    "POST", List.of(
                            "http://192.168.3.62/v1/api/auth",
                            "http://192.168.3.62/v1/api/auth/refresh-token"),
                    "GET", List.of(
                            "http://192.168.3.62/v1/api/users/verify-email",
                            "http://192.168.3.62/v1/api/auth/password-reset"),
                    "OPTIONS", List.of("/**"));

            public static boolean matches(String method, String uri) {
                List<String> endpoints = ALLOWED_ENDPOINTS.get(method);
                return endpoints != null && endpoints.stream()
                        .anyMatch(uri::startsWith);
            }
        }
    }
}
