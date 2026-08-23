package org.example.repo2cloudbuild_server.service;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;

@Service
public class DeployService {

    public String cloneRepo(String url) throws Exception {
        String folderPath = "repos/project"+System.currentTimeMillis();

        ProcessBuilder pb = new ProcessBuilder("git","clone",url,folderPath);
        Process process = pb.start();

        BufferedReader resultStream = new BufferedReader(
                new InputStreamReader(process.getInputStream())
        );

        BufferedReader errorStream = new BufferedReader(
                new InputStreamReader(process.getErrorStream())
        );

        StringBuilder result = new StringBuilder();
        StringBuilder error = new StringBuilder();
        String line;

        while ((line = resultStream.readLine()) != null){
            System.out.println(line);
            result.append(line).append("\n");
        }
        while((line = errorStream.readLine()) != null){
            System.out.println(line);
            error.append(line).append("\n");
        }

        int exitCode = process.waitFor();

        if (exitCode != 0){
            return error.toString();
        }
        return result.toString();
    }

}
