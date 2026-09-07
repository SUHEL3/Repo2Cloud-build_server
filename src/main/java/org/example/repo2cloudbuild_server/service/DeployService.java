package org.example.repo2cloudbuild_server.service;

import org.example.repo2cloudbuild_server.dto.responseDTO.BuildResponse;
import org.example.repo2cloudbuild_server.entity.model.Status;
import org.springframework.stereotype.Service;

import javax.swing.plaf.basic.BasicIconFactory;
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
        return folderPath;
    }

    public BuildResponse buildImageUsingDockerfile(String folderPath) throws Exception{
        System.out.println("Build request hit");
        String imageName = "image"+System.currentTimeMillis();
        String dockerFilePath = folderPath+"/Dockerfile";
        ProcessBuilder pb = new ProcessBuilder("docker","build","-f",dockerFilePath,"-t",imageName,folderPath);

        pb.redirectErrorStream(true);
        Process process = pb.start();

        BufferedReader bf = new BufferedReader(
                new InputStreamReader(process.getInputStream())
        );

        StringBuilder output  = new StringBuilder();
        String line;

        while ((line = bf.readLine()) != null){
            output.append(line).append("\n");
            System.out.println(line);
        }

        int exitCode = process.waitFor();

        if(exitCode != 0){
            return new BuildResponse(Status.FAILED,"");
        }
        return new BuildResponse(Status.STARTING,imageName);
    }


}
