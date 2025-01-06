package controller;

import service.VideoService;

public class VideoManager {

    private final VideoService videoService;

    public VideoManager(VideoService videoService) {
        this.videoService = videoService;
    }
}
