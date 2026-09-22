package org.example.repo2cloudbuild_server.dto.requestDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoleBasedChatBotRequest {
    private String role;
    private String msg;
}
