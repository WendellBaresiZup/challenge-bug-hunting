package strategy;

import model.Categoria;
import model.Video;

import java.util.List;
import java.util.stream.Collectors;

public class SearchStrategyImpl implements SearchStrategy {
    @Override
    public List<Video> searchByTitle(List<Video> videos, String query) {
        if (videos == null || query == null)
            throw new IllegalArgumentException("Vídeos e Query(Consulta) não podem ser nulos");
        return videos.stream()
                .filter(video -> video.getTitulo().toLowerCase().trim().contains(query.toLowerCase().trim()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Video> searchByCategory(List<Video> videos, Categoria query) {
        if (videos == null || query == null)
            throw new IllegalArgumentException("Vídeos e Query(Consulta) não podem ser nulos");
        return videos.stream()
                .filter(video -> video.getCategoria().equals(query))
                .collect(Collectors.toList());
    }
}