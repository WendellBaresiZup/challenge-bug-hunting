package repository;

import model.Video;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileVideoRepositoryImpl implements VideoRepository {
    private final File file;

    public FileVideoRepositoryImpl(String filePath) {
        this.file = new File(filePath);
    }

    @Override
    public void save(Video video) {
        if (video == null) {
            throw new IllegalArgumentException("Vídeo não pode ser nulo");
        }
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file, true))) {
            bw.write(video.toString());
            bw.newLine();
        } catch (IOException e) {
            System.out.println("Erro ao salvar o vídeo!");
        }
    }

    @Override
    public List<Video> findAll() {
        List<Video> videos = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                Video video = Video.fromString(line);
                if (video != null) {
                    videos.add(video);
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao buscar a lista de vídeos!");
        }
        return videos;
    }

    @Override
    public void update(Video videoOriginal, Video videoComNovosDados) {
        if (videoComNovosDados == null || videoComNovosDados.getTitulo() == null || videoComNovosDados.getTitulo().isEmpty()) {
            throw new IllegalArgumentException("Vídeo com Novos Dados não pode ser nulo e também não pode ter o título vazio");
        }
        List<Video> videos = findAll();
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            for (Video video : videos) {
                if (video.getTitulo().toLowerCase().trim().equals(videoOriginal.getTitulo().toLowerCase().trim())) {
                    bw.write(videoComNovosDados.toString());
                    bw.newLine();
                } else {
                    bw.write(video.toString());
                    bw.newLine();
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao atualizar o vídeo!");
        }
    }

    @Override
    public void deleteByTitulo(String titulo) {
        if (titulo == null) {
            throw new IllegalArgumentException("O título do vídeo não pode ser nulo");
        }
        List<Video> videos = findAll();
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            for (Video video : videos) {
                if (!video.getTitulo().toLowerCase().trim().equals(titulo.toLowerCase().trim())) {
                    bw.write(video.toString());
                    bw.newLine();
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao deletar vídeo");
        }
    }

}