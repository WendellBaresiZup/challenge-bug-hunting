package service;

import model.Categoria;
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
        return searchStrategy.searchByTitle(repository.findAll(),titulo);
    }

    public void updateVideo(Video videoOriginal, Video videoComNovosDados){
        validarVideo(videoComNovosDados);
        repository.update(videoOriginal, videoComNovosDados);
    }

    public void deleteByTitulo(String titulo){
        repository.deleteByTitulo(titulo);
    }

    public void searchVideoByCategory(Categoria query){
        List<Video> videos = repository.findAll();
        List<Video> videosFilter = searchStrategy.searchByCategory(videos, query);
        videosFilter.forEach(System.out::println);
    }

    @Override
    public void relatorioEstatisticas() {
        List<Video> videos = repository.findAll();
        int totaldosVídeos = videos.stream().mapToInt(Video::getDuracao).sum();
        System.out.println("Relatório de Estatísticas");
        System.out.println("Quantidade de Vídeos: " + videos.size());
        System.out.println("Duração total dos Vídeos: " + totaldosVídeos + " minutos");
        System.out.println("Quantidade de Vídeos pela Categoria");
        long count = 0L;
        for (Video video1 : videos) {
            if (video1.getCategoria() == Categoria.Documentario) {
                count++;
            }
        }
        System.out.println("Documentário: " + count);
        long result = 0L;
        for (Video video1 : videos) {
            if (video1.getCategoria() == Categoria.Filme) {
                result++;
            }
        }
        System.out.println("Filme: " + result);
        long count1 = 0L;
        for (Video video : videos) {
            if (video.getCategoria() == Categoria.Serie) {
                count1++;
            }
        }
        System.out.println("Serie: " + count1);
    }



}