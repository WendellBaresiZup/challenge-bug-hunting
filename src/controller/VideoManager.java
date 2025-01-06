package controller;

import model.Video;
import service.VideoService;

public class VideoManager {

    private final VideoService videoService;

    public VideoManager(VideoService videoService) {
        this.videoService = videoService;
    }

    public void adicionarVideo(String titulo, String descricao, int duracao, String categoria, String dataPublicacao){
        videoService.addVideo(new Video(titulo, descricao, duracao, categoria, dataPublicacao));
    }
}
