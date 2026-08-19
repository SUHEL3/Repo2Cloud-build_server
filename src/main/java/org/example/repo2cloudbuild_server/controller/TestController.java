package org.example.repo2cloudbuild_server.controller;

import lombok.RequiredArgsConstructor;
import org.example.repo2cloudbuild_server.wrapper.ApiResponse;
import org.example.repo2cloudbuild_server.wrapper.ApiRoute;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ApiRoute.TEST)
@RequiredArgsConstructor
public class TestController {

    @GetMapping("/test")
    public ApiResponse<String> checkHealth(){
        return new ApiResponse<>("Server Health",
                "Running ....");
    }

}
