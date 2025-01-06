package main;

import controller.VideoManager;
import model.Video;
import repository.FileVideoRepositoryImpl;
import service.VideoService;
import service.VideoServiceImpl;
import strategy.SearchStrategy;
import strategy.TitleSearchStrategyImpl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Main {
        static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        FileVideoRepositoryImpl fileVideoRepository = new FileVideoRepositoryImpl("videos.txt");
        SearchStrategy searchStrategy = new TitleSearchStrategyImpl();
        VideoService videoService = new VideoServiceImpl(fileVideoRepository, searchStrategy);
        VideoManager videoManager = new VideoManager(videoService);

        int menu = 0;

        while (menu != 9) {
            System.out.println("\n=== Sistema de Gerenciamento de Vídeos ===");
            System.out.println("Escolha uma opção: ");
            System.out.println("1. Adicionar vídeo");
            System.out.println("2. Listar vídeos");
            System.out.println("3. Pesquisar vídeo por título");
            System.out.println("4. Editar vídeo");
            System.out.println("5. Excluir vídeo");
            System.out.println("6. Filtrar vídeo");
            System.out.println("7. Ordenar vídeo");
            System.out.println("8. Exibir Relatório de Estatísticas");
            System.out.println("9. Sair");

            menu = scanner.nextInt();
            scanner.nextLine(); // Consumir a quebra de linha

            switch (menu){
                case 1:
                    adicionarVideo(videoManager);
                    break;
                case 2:
                    listarVideo(videoManager);
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

    public static void adicionarVideo(VideoManager videoManager){
        try {
            System.out.println("-- Dicas de Como Adicionar o Vídeo --");
            System.out.println("O Título e a Descrição do Vídeo não podem ser vazio!!");
            System.out.println("A Duração do Vídeo tem que ser número positivo ou maior que zero!!");
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
            String dataPublicacao = scanner.nextLine();

            videoManager.adicionarVideo(titulo, descricao, duracao, categoria, dataPublicacao);
            System.out.println("Vídeo adicionado com sucesso!");
        } catch (IllegalArgumentException e) {
            System.out.println("Erro ao adicionar vídeo." + e.getMessage());
        }
    }

    public static void listarVideo(VideoManager videoManager){
        var videos = videoManager.listarVideo();
        videos.forEach(System.out::println);
    }

    public static void buscarVideo(){

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