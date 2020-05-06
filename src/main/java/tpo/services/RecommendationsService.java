package tpo.services;

import com.wrapper.spotify.model_objects.specification.TrackSimplified;

public interface RecommendationsService {
    TrackSimplified[] getMusicRecommendationsByArtists(String token, Integer limit);

    TrackSimplified[] getMusicRecommendationsByTrack(String token, Integer limit);

    TrackSimplified[] getMusicRecommendationsBy(String token, Integer limit, String type);
}
