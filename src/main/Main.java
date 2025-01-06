package main;

import controller.VideoManager;
import model.Video;
import repository.VideoRepositoryImpl;
import service.VideoService;
import service.VideoServiceImpl;
import strategy.SearchStrategy;
import strategy.SearchStrategyImpl;

import java.util.Scanner;

public class Main {
        static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        VideoRepositoryImpl fileVideoRepository = new VideoRepositoryImpl("videos.txt");
        SearchStrategy searchStrategy = new SearchStrategyImpl();
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
                    buscarVideo(videoManager);
                    break;
                case 4:
                    editarVideo(videoManager);
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

    public static void buscarVideo(VideoManager videoManager){
        System.out.print("Digite o Título do Vídeo para busca: ");
        String titulo = scanner.nextLine();

        var videos = videoManager.pesquisarVideoTitulo(titulo);
        videos.forEach(System.out::println);
    }

    public static void editarVideo(VideoManager videoManager){
        System.out.println("-- Dicas de Como Editar o Vídeo --");
        System.out.println("O Título e a Descrição do Vídeo não podem ser vazio!!");
        System.out.println("A Duração do Vídeo tem que ser número positivo ou maior que zero!!");

        System.out.print("Digite o título do vídeo: ");
        String titulo = scanner.nextLine();

        var videos = videoManager.pesquisarVideoTitulo(titulo);
        if (videos.isEmpty()){
            System.out.println("Vídeo não encontrado!");
        } else if (videos.size() == 1) {
            var video = videos.get(0);
            System.out.println("Vídeo encontrado: " + video);

            System.out.println("Deseja editar este vídeo? (S/N): ");
            var simNao = scanner.nextLine();
            if (simNao.equalsIgnoreCase("S")){
                var novosDadosDoVideo = solicitaNovosDadosDoVideo();
                videoManager.editarVideo(video, novosDadosDoVideo);
            } else {
                System.out.println("Edição Cancelada!");
            }
        } else {
            System.out.println("Mais de um vídeo encontrado pelo título informado, por favor indicar o index do item a ser editado: ");
            for (int i = 0; i < videos.size(); i++){
                System.out.println("Index: " + i + " - " + videos.get(i));
            }
            int index = scanner.nextInt();
            scanner.nextLine(); // Consumir quebra de linha
            if (index < 0 || index >= videos.size()){
                System.out.println("Index Inválido!");
                return;
            }
            var video = videos.get(index);
            if (video == null){
                System.out.println("Vídeo não encontrado na lista!");
            } else {
                var novosDadosDoVideo = solicitaNovosDadosDoVideo();
                videoManager.editarVideo(video, novosDadosDoVideo);
            }
        }
    }

    public static Video solicitaNovosDadosDoVideo(){
        System.out.print("Digite o Novo Título do Vídeo: ");
        String titulo = scanner.nextLine();
        System.out.print("Digite a Nova Descrição do Vídeo: ");
        String descricao = scanner.nextLine();
        System.out.print("Digite a Nova Duração do Vídeo (em minutos): ");
        String duracao = scanner.nextLine();
        System.out.print("Digite a Nova Categoria do Vídeo: ");
        String categoria = scanner.nextLine();
        System.out.print("Digite a Nova Data de Publicação (dd/MM/yyyy): ");
        String dataPublicacao = scanner.nextLine();

        return new Video(titulo, descricao, Integer.parseInt(duracao), categoria, dataPublicacao);
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