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

    private void validarVideo(Video video){
        if (video == null){
            throw new IllegalArgumentException("Vídeo Não Pode Ser Nulo!");
        }
        if (video.getTitulo() == null || video.getTitulo().isEmpty()){
            throw new IllegalArgumentException("O Título do Vídeo Não Pode Ser Nulo!!");
        }
        if (video.getDescricao() == null || video.getDescricao().isEmpty()){
            throw new IllegalArgumentException("A Descrição do Vídeo Não Pode Ser Vazia!!");
        }
        if (video.getDuracao() <= 0){
            throw new IllegalArgumentException("A Duração do Vídeo tem que ser um número positivo e maior que zero!!");
        }
    }

    @Override
    public void addVideo(Video video) {
        validarVideo(video);
        repository.save(video);
    }

    @Override
    public List<Video> listVideos() {
        return repository.findAll();
    }
}