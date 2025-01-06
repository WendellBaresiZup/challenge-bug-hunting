package controller;

import model.Video;
import service.VideoService;

import java.util.List;

public class VideoManager {

    private final VideoService videoService;

    public VideoManager(VideoService videoService) {
        this.videoService = videoService;
    }

    public void adicionarVideo(String titulo, String descricao, int duracao, String categoria, String dataPublicacao){
        videoService.addVideo(new Video(titulo, descricao, duracao, categoria, dataPublicacao));
    }

    public List<Video> listarVideo(){
        return videoService.listVideos();
    }

    public List<Video> pesquisarVideoTitulo(String query){
        return videoService.searchVideo(query);
    }

    public void editarVideo(Video videoOriginal, Video videoNovosDados){
        videoService.updateVideo(videoOriginal, videoNovosDados);
        System.out.println("Vídeo atualizado com sucesso: " + videoNovosDados);
    }

    public void excluirVideo(String titulo){
        videoService.deleteByTitulo(titulo);
        System.out.println("Vídeo excluído com sucesso!");
    }
}
