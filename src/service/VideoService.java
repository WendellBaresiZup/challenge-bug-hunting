package service;

import model.Video;

import java.util.List;

public interface VideoService {
    void addVideo(Video video);
    List<Video> listVideos();
    List<Video> searchVideo(String query);
    void updateVideo(Video videoOriginal, Video videoComNovosDados);
}