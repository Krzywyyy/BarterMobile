package pl.krzywyyy.barter.utils;

import android.content.Context;

import com.auth0.android.jwt.JWT;

import java.util.Objects;

public class TokenExplorator {

    public static String getNameFromToken(Context context) {
        JWT jwt = getJWT(context);
        return Objects.requireNonNull(jwt.getSubject()).split("\\.")[0];
    }

    public static boolean isTokenExpired(Context context) {
        return getJWT(context).isExpired(10);
    }

    private static JWT getJWT(Context context) {
        String token = SharedPreferencesManager.getToken(context);
        return new JWT(token.split(" ")[1]);
    }
}
