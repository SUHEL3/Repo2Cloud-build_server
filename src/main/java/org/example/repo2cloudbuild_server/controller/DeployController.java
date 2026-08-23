package org.example.repo2cloudbuild_server.controller;

import lombok.RequiredArgsConstructor;
import org.example.repo2cloudbuild_server.dto.requestDTO.DeploymentRequest;
import org.example.repo2cloudbuild_server.service.DeployService;
import org.example.repo2cloudbuild_server.wrapper.ApiResponse;
import org.example.repo2cloudbuild_server.wrapper.ApiRoute;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ApiRoute.DEPLOYMENT)
@RequiredArgsConstructor
public class DeployController {

    private final DeployService deployService;

    @PostMapping("/clone")
    public ApiResponse<String> cloneRepository(
            @RequestBody DeploymentRequest request
            ) throws Exception {
        return new ApiResponse<>("Cloning result",
                deployService.cloneRepo(request.getUrl()));
    }

}
