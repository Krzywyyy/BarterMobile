package pl.krzywyyy.barter.utils;

import android.content.Context;

import com.auth0.android.jwt.JWT;

import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

public class TokenExplorator {

    public static String getUserNameFromToken(Context context) {
        JWT jwt = getJWT(context);
        String userName = Objects.requireNonNull(jwt.getSubject()).split("\\.")[0];
        return StringUtils.capitalize(userName);
    }

    public static String getUserEmailFromToken(Context context){
        JWT jwt = getJWT(context);
        return Objects.requireNonNull(jwt.getSubject());
    }

    public static boolean isTokenExpired(Context context) {
        return getJWT(context).isExpired(10);
    }

    private static JWT getJWT(Context context) {
        String token = SharedPreferencesManager.getToken(context);
        return new JWT(token.split(" ")[1]);
    }
}
