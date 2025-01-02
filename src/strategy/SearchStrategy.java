package strategy;

import model.Categoria;
import model.Video;

import java.util.List;

public interface SearchStrategy {
    List<Video> searchByTitle(List<Video> videos, String query);
    List<Video> searchByCategory(List<Video> videos, Categoria query);
}