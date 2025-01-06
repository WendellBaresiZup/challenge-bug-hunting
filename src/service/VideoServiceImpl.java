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

    public void addVideo(Video video) {
        validarVideo(video);
        repository.save(video);
    }

    public List<Video> listVideos() {
        return repository.findAll();
    }

    public List<Video> searchVideo(String titulo){
        return searchStrategy.search(repository.findAll(), titulo);
    }

    public void updateVideo(Video videoOriginal, Video videoComNovosDados){
        validarVideo(videoComNovosDados);
        repository.update(videoOriginal, videoComNovosDados);
    }

    public void deleteByTitulo(String titulo){
        repository.deleteByTitulo(titulo);
    }


    public void relatorioEstatisticas(){
        List<Video> videos = repository.findAll();
        int totalDosVideos = videos.stream().mapToInt(Video::getDuracao).sum();
        System.out.println("== Relatório de Estatísticas ==");
        System.out.println("Quantidade de Vídeos: " + videos.size());
        System.out.println("Duração total dos Vídeos: " + totalDosVideos + " minutos");
    }
}