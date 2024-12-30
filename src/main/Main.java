package main;

import model.Video;
import repository.FileVideoRepository;
import service.VideoService;
import service.VideoServiceImpl;
import strategy.SearchStrategy;
import strategy.TitleSearchStrategy;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Main {

    // classe scanner criada fora e estatica para ser usada por todas a funções
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        VideoService videoService = new VideoServiceImpl(new FileVideoRepository("videos.txt"));
        SearchStrategy searchStrategy = new TitleSearchStrategy();

        int menu = 0;
        scanner.nextLine(); // Consumir a quebra de linha

        while (menu != 9) {
            System.out.println("\n=== Sistema de Gerenciamento de Vídeos ===");
            System.out.print("Escolha uma opção: ");
            System.out.println("1. Adicionar Vídeo");
            System.out.println("2. Listar Vídeos");
            System.out.println("3. Pesquisar Vídeo por Título");
            System.out.println("4. Editar Vídeo");
            System.out.println("5. Excluir Vídeo por Título");
            System.out.println("6. Filtrar Vídeos por Categoria");
            System.out.println("7. Ordenar Vídeos por Data de Públicação");
            System.out.println("8. Exibir Relatório de Estatísticas");
            System.out.println("9. Sair");
        }

        scanner.close();
    }

    public void adicionarVideo(){
        System.out.print("Digite o título do vídeo: ");
        String titulo = scanner.nextLine();
        System.out.print("Digite a descrição do vídeo: ");
        String descricao = scanner.nextLine();
        System.out.print("Digite a duração do vídeo (em minutos): ");
        int duracao = scanner.nextInt();
        scanner.nextLine(); // Consumir a quebra de linha
        System.out.print("Digite a categoria do vídeo: ");
        String categoria = scanner.nextLine();
        System.out.print("Digite a data de publicação (dd/MM/yyyy): ");
        String dataStr = scanner.nextLine();
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date dataPublicacao = sdf.parse(dataStr);
            Video video = new Video(titulo, descricao, duracao, categoria, dataPublicacao);
            videoService.addVideo(video);
            System.out.println("Vídeo adicionado com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao adicionar vídeo.");
        }
    }

    public void listarVideos() {
        List<Video> videos = videoService.listVideos();
        for (Video video : videos) {
            System.out.println(video);
        }
    }

    public void buscarVideo() {
        System.out.print("Digite o título para busca: ");
        String query = scanner.nextLine();
        List<Video> resultados = searchStrategy.search(videoService.listVideos(), query);
        for (Video video : resultados) {
            System.out.println(video);
        }
    }

}