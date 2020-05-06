package tpo.rest;

import com.wrapper.spotify.model_objects.specification.Artist;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tpo.services.ArtistService;

@RestController
@RequestMapping(value = "/artists")
@CrossOrigin(allowCredentials = "true")
public class ArtistController {
    private final ArtistService artistService;

    public ArtistController(ArtistService artistService) {
        this.artistService = artistService;
    }

    @GetMapping("")
    public ResponseEntity<Artist> getArtist(@RequestHeader("Authorization") String token,
                                            @RequestParam("id") String id){
        return ResponseEntity.ok(artistService.getArtist(token, id));
    }
}
