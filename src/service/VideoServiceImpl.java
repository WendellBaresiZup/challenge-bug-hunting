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
            throw new IllegalArgumentException("Vídeo não pode ser nulo");
        }
        if (video.getTitulo() == null || video.getTitulo().isEmpty()){
            throw new IllegalArgumentException("O Título do Vídeo não pode ser nulo e não pode ser vazio");
        }
        if (video.getDescricao() == null || video.getDescricao().isEmpty()){
            throw new IllegalArgumentException("A Descrição do Vídeo não pode ser nula e não pode ser vazia");
        }
        if (video.getDuracao() <= 0){
            throw new IllegalArgumentException("A Duração do Vídeo não pode ser um número igual ou menor que zero");
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

    @Override
    public List<Video> searchVideo(String titulo){
        return searchStrategy.search(repository.findAll(),titulo);
    }

    public void updateVideo(Video videoOriginal, Video videoComNovosDados){
        validarVideo(videoComNovosDados);
        repository.update(videoOriginal, videoComNovosDados);
    }

    public void deleteByTitulo(String titulo){
        repository.deleteByTitulo(titulo);
    }


}