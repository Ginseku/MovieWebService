package com.example.streamingservice.service;

import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

@Service
public class MovieService {

    public String getFilm(String slug) throws IOException, InterruptedException {
        File f = new File("/Users/mykytabondarchuk/VSProjects/movie-website-build2/public/videos/" + slug + "/master.m3u8");

        if (f.exists()){
            return "http://localhost:8080/stream/" + slug + "/master.m3u8";
        }
        else{
            var builder = new ProcessBuilder("ffmpeg",
                    "-i", "/Users/mykytabondarchuk/VSProjects/movie-website-build2/public/videos/" + slug + "/" + slug + ".mp4",
                    "-f", "hls",
                    "-hls_time", "5",
                    "-hls_list_size", "0",
                    "/Users/mykytabondarchuk/VSProjects/movie-website-build2/public/videos/" + slug + "/" + "master.m3u8"
            );
            builder.redirectErrorStream(true);
            builder.redirectOutput(ProcessBuilder.Redirect.INHERIT);
            builder.start().waitFor();
            return "http://localhost:8080/stream/" + slug + "/master.m3u8";
        }
    }

}
