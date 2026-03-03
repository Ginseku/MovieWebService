package com.example.streamingservice.controller;

import com.example.streamingservice.service.MovieService;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;

@RestController
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/movie/getTitle/{slug}/watch")
    public Map<String,String> getMovieId(@PathVariable String slug) throws IOException, InterruptedException {
        String URL = movieService.getFilm(slug);
        return Map.of("streamUrl",URL);//сделав Map мы сможем вернуть Json формат файл

    }


@GetMapping("/stream/{slug}/{segment}")
public ResponseEntity<Resource> sendMovie(
        @PathVariable String slug,
        @PathVariable String segment
) {

    FileSystemResource resource = new FileSystemResource(
            "/Users/mykytabondarchuk/VSProjects/movie-website-build2/public/videos/"
                    + slug + "/" + segment
    );

    MediaType mediaType;

    if (segment.endsWith(".m3u8")) {
        mediaType = MediaType.valueOf("application/vnd.apple.mpegurl");
    } else if (segment.endsWith(".ts")) {
        mediaType = MediaType.valueOf("video/MP2T");
    } else {
        mediaType = MediaType.APPLICATION_OCTET_STREAM;
    }

    return ResponseEntity.ok()
            .contentType(mediaType)
            .body(resource);
}

}
