package controller;

import model.Categoria;
import model.Video;
import service.VideoService;

import java.util.List;

public class VideoManager {

    private final VideoService videoService;

    public VideoManager(VideoService videoService) {
        this.videoService = videoService;
    }

    public void adicionarVideo(String titulo, String descricao, int duracao, Categoria categoria, String dataPublicacao){
        videoService.addVideo(new Video(titulo, descricao, duracao, categoria, dataPublicacao));
    }

    public List<Video> listarVideo(){
        return videoService.listVideos();
    }

    public List<Video> pesquisarVideoPeloTitulo(String query){
        return videoService.searchVideo(query);
    }

    public void editarVideo(Video videoOriginal, Video videoNovosDados){
        videoService.updateVideo(videoOriginal, videoNovosDados);
        System.out.println("Vídeo atualizado com sucesso: " + videoNovosDados);
    }

    public void excluirVideo(String titulo){
        videoService.deleteByTitulo(titulo);
        System.out.println("Vídeo excluído com sucesso");
    }

    public List<Video> pesquisarVideoPelaCategoria(Categoria query){
        return videoService.searchVideo(String.valueOf(query));
    }

}
