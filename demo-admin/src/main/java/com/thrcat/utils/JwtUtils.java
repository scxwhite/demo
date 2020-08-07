package com.thrcat.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.thrcat.Constants;
import com.thrcat.logs.ErrorLog;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class JwtUtils {

    private final static String secret = "www.three.cat.com";

    private static Algorithm algorithm = null;

    static {
        try {
            algorithm = Algorithm.HMAC256(secret);
        } catch (UnsupportedEncodingException e) {
            ErrorLog.error("不支持的编码方式", e);
        }
    }

    /**
     *
     * @param username
     * @param userId
     * @param ssoId
     * @param ssoName
     * @return
     */
    public static String createToken(String username, String userId, String ssoId, String ssoName) {
        Map<String, Object> header = new HashMap<>(2);
        header.put("alg", "HS256");
        header.put("typ", "JWT");
        Calendar calendar = Calendar.getInstance();
        Date now = calendar.getTime();
        calendar.add(Calendar.DAY_OF_MONTH, 3);
        Date expireDate = calendar.getTime();
        return JWT.create().withHeader(header)
                .withClaim("iss", "user")
                .withClaim("aud", "three_cat")
                .withClaim(Constants.SESSION_USERNAME, username)
                .withClaim(Constants.SESSION_USER_ID, userId)
                .withClaim(Constants.SESSION_SSO_ID, ssoId)
                .withClaim(Constants.SESSION_SSO_NAME, ssoName)
                .withIssuedAt(now)
                .withExpiresAt(expireDate)
                .sign(algorithm);
    }

    public static boolean verifyToken(String token) {
        return getClaims(token) != null;
    }


    private static Map<String, Claim> getClaims(String token) {
        DecodedJWT jwt;
        JWTVerifier verifier = JWT.require(algorithm).build();
        try {
            jwt = verifier.verify(token);
        } catch (Exception e) {
            return null;
        }
        return jwt.getClaims();
    }

    public static String getObjectFromToken(String token, String key) {
        Map<String, Claim> claimMap = getClaims(token);
        if (claimMap != null && claimMap.get(key) != null) {
            return claimMap.get(key).asString();
        }
        return null;
    }

    public static String getObjectFromToken(String tokenName, HttpServletRequest request, String key) {
        String token = getValFromCookies(tokenName, request);
        return getObjectFromToken(token, key);
    }

    public static String getValFromCookies(String tokenName, HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(tokenName)) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }
}
