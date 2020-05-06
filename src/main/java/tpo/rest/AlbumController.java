package tpo.rest;

import com.wrapper.spotify.model_objects.specification.Album;
import com.wrapper.spotify.model_objects.specification.AlbumSimplified;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tpo.services.AlbumService;

@RestController
@RequestMapping(value = "/albums")
@CrossOrigin(allowCredentials = "true")
public class AlbumController {
    private final AlbumService albumService;

    public AlbumController(AlbumService albumService) {
        this.albumService = albumService;
    }

    @GetMapping("")
    public ResponseEntity<AlbumSimplified[]> getAlbums(@RequestHeader("Authorization") String token,
                                                       @RequestParam("artist_id") String artistId){
        return ResponseEntity.ok(albumService.getAlbums(token, artistId));
    }

    @GetMapping("/one")
    public ResponseEntity<Album> getAlbum(@RequestHeader("Authorization") String token,
                                          @RequestParam("id") String id){
        return ResponseEntity.ok(albumService.getAlbum(token, id));
    }
}
