package tpo.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tpo.services.SearchService;

@RestController
@RequestMapping(value = "/search")
@CrossOrigin(allowCredentials = "true")
public class SearchController {

    private final SearchService searchService;

    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    @GetMapping("")
    public ResponseEntity<Object[]> search(@RequestHeader("Authorization") String token,
                                          @RequestParam("query") String query,
                                          @RequestParam("offset") Integer offset,
                                          @RequestParam("limit") Integer limit,
                                          @RequestParam("type") String type){
        return ResponseEntity.ok(searchService.search(token, query, offset, limit, type));
    }
}
