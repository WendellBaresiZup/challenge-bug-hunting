package service;

import model.Video;
import repository.VideoRepository;
import strategy.SearchStrategy;

import java.util.List;

public class VideoServiceImpl implements VideoService {
    private final VideoRepository repository;
    private final SearchStrategy searchStrategy;

    public VideoServiceImpl(VideoRepository repository, SearchStrategy searchStrategy) {
        this.repository = repository;
        this.searchStrategy = searchStrategy;
    }

    @Override
    public void addVideo(Video video) {
        repository.save(video);
    }

    @Override
    public List<Video> listVideos() {
        return repository.findAll();
    }

    @Override
    public List<Video> searchVideo(String titulo){
        return searchStrategy.search(repository.findAll(),titulo);
    }


}