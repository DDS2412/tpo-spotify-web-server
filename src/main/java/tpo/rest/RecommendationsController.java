package tpo.rest;

import com.wrapper.spotify.model_objects.specification.TrackSimplified;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tpo.services.RecommendationsService;

@RestController
@RequestMapping(value = "/recommendations")
@CrossOrigin(allowCredentials = "true")
public class RecommendationsController {
    private final RecommendationsService recommendationsService;

    public RecommendationsController(RecommendationsService recommendationsService) {
        this.recommendationsService = recommendationsService;
    }

    @GetMapping("")
    public ResponseEntity<TrackSimplified[]> search(@RequestHeader("Authorization") String token,
                                                    @RequestParam("limit") Integer limit,
                                                    @RequestParam("type") String type){
        return ResponseEntity.ok(recommendationsService.getMusicRecommendationsBy(token, limit, type));
    }
}
