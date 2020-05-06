package tpo.services;

import com.wrapper.spotify.model_objects.specification.Track;
import com.wrapper.spotify.model_objects.specification.TrackSimplified;

public interface TrackService {
    Track[] getPersonalTopTracks(String token);

    TrackSimplified[] getPersonalTracks(String token);
}
