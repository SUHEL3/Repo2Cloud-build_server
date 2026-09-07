package org.example.repo2cloudbuild_server.dto.responseDTO;

import lombok.*;
import org.example.repo2cloudbuild_server.entity.model.Status;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BuildResponse {
    private Status status;
    private String imageName;
}
