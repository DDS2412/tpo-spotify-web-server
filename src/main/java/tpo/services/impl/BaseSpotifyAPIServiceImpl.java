package tpo.services.impl;

import com.wrapper.spotify.SpotifyApi;

public class BaseSpotifyAPIServiceImpl {
    protected SpotifyApi getSpotifyApi(String accessToken){
        return new SpotifyApi.Builder()
                .setAccessToken(accessToken)
                .build();
    }
}
