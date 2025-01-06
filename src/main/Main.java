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
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        VideoService videoService = new VideoServiceImpl(new FileVideoRepository("videos.txt"));
        SearchStrategy searchStrategy = new TitleSearchStrategy();

        int menu = 0;

        while (menu != 9) {
            System.out.println("\n=== Sistema de Gerenciamento de Vídeos ===");
            System.out.print("Escolha uma opção: ");
            System.out.println("1. Adicionar vídeo");
            System.out.println("2. Listar vídeos");
            System.out.println("3. Pesquisar vídeo por título");
            System.out.println("4. Pesquisar vídeo por título");
            System.out.println("5. Pesquisar vídeo por título");
            System.out.println("6. Pesquisar vídeo por título");
            System.out.println("7. Pesquisar vídeo por título");
            System.out.println("8. Pesquisar vídeo por título");
            System.out.println("9. Sair");

            menu = scanner.nextInt();
            scanner.nextLine(); // Consumir a quebra de linha

            switch (menu){
                case 1:
                    adicionarVideo();
                    break;
                case 2:
                    listarVideo();
                    break;
                case 3:
                    buscarVideo();
                    break;
                case 4:
                    editarVideo();
                    break;
                case 5:
                    excluirVideo();
                    break;
                case 6:
                    filtrarVideo();
                    break;
                case 7:
                    ordenarVideo();
                    break;
                case 8:
                    exibirRelatorio();
                    break;
                case 9:
                    System.out.println("Saindo do sistema...");
                    break;
                default:
                    System.out.println("Opção não existe!");
                    break;
            }
        }

        scanner.close();
    }

    public static void adicionarVideo(){
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

    public static void listarVideo(){
        List<Video> videos = videoService.listVideos();
        for (Video video : videos) {
            System.out.println(video);
        }
    }

    public static void buscarVideo(){
        System.out.print("Digite o título para busca: ");
        String query = scanner.nextLine();
        List<Video> resultados = searchStrategy.search(videoService.listVideos(), query);
        for (Video video : resultados) {
            System.out.println(video);
        }
    }

    public static void editarVideo(){

    }

    public static void excluirVideo(){

    }

    public static void filtrarVideo(){

    }

    public static void ordenarVideo(){

    }

    public static void exibirRelatorio(){

    }
}