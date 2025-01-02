package main;

import controller.VideoManager;
import model.Categoria;
import model.Video;
import repository.FileVideoRepositoryImpl;
import service.VideoService;
import service.VideoServiceImpl;
import strategy.SearchStrategy;
import strategy.SearchStrategyImpl;

import java.util.Scanner;

public class Main {

    // classe scanner criada fora e estatica para ser usada por todas a funções
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        FileVideoRepositoryImpl fileVideoRepository = new FileVideoRepositoryImpl("videos.txt");
        SearchStrategy searchStrategy = new SearchStrategyImpl();
        VideoService videoService = new VideoServiceImpl(fileVideoRepository,searchStrategy);
        VideoManager videoManager = new VideoManager(videoService);

        int menu = 0;


        while (menu != 9) {
            System.out.println("\n=== Sistema de Gerenciamento de Vídeos ===");
            System.out.println("Escolha uma opção: ");
            System.out.println("1. Adicionar Vídeo");
            System.out.println("2. Listar Vídeos");
            System.out.println("3. Pesquisar Vídeo por Título");
            System.out.println("4. Editar Vídeo");
            System.out.println("5. Excluir Vídeo por Título");
            System.out.println("6. Filtrar Vídeos por Categoria");
            System.out.println("7. Ordenar Vídeos por Data de Públicação");
            System.out.println("8. Exibir Relatório de Estatísticas");
            System.out.println("9. Sair");

            menu = scanner.nextInt();
            scanner.nextLine(); // Consumir a quebra de linha

            switch (menu){
                case 1:
                    adicionarVideo(videoManager);
                    break;
                case 2:
                    listarVideos(videoManager);
                    break;
                case 3:
                    buscarVideo(videoManager);
                    break;
                case 4:
                    editarVideo(videoManager);
                    break;
                case 5:
                    excluirVideo(videoManager);
                    break;
                case 6:
                    filtrarVideo(videoManager);
                    break;
                case 7:
                    ordenarVideo();
                    break;
                case 8:
                    exibirRelatorio();
                    break;
                case 9:
                    System.out.println("Saindo do Sistema!");
                    break;
                default:
                    System.out.println("opção não existe!");
                    break;
            }
        }

        scanner.close();
    }

    public static void adicionarVideo(VideoManager videoManager){

        try {
            System.out.println("=== Dicas para adicionar o video ===");
            System.out.println("!!O título do vídeo não pode ser nulo!!");
            System.out.println("!!A descrição do vídeo não pode ser nulo!!");
            System.out.println("!!A duração do vídeo tem que ser número positivo e maior que zero!!");

            System.out.print("Digite o título do vídeo: ");
            String titulo = scanner.nextLine();
            System.out.print("Digite a descrição do vídeo: ");
            String descricao = scanner.nextLine();
            System.out.print("Digite a duração do vídeo (em minutos): ");
            String duracao = scanner.nextLine();
            System.out.print("Digite a categoria do vídeo: ");
            Categoria categoria = Categoria.valueOf(scanner.nextLine());
            System.out.print("Digite a data de publicação (dd/MM/yyyy): ");
            String dataPublicacao = scanner.nextLine();

            videoManager.adicionarVideo(titulo, descricao, Integer.parseInt(duracao), categoria, dataPublicacao);
        } catch (IllegalArgumentException e){
            System.out.println("Erro ao salvar o vídeo " + e.getMessage());
        }


    }

    public static void listarVideos(VideoManager videoManager) {
       var videos = videoManager.listarVideo();
       videos.forEach(System.out::println);
    }

    public static void buscarVideo(VideoManager videoManager) {
       System.out.print("Digite o título para busca: ");
       String titulo = scanner.nextLine();

       var videos = videoManager.pesquisarVideoPeloTitulo(titulo);
       videos.forEach(System.out::println);

    }

    public static Video solicitaNovosDadosDoVideo(){
        System.out.print("Digite o Novo Título do Vídeo: ");
        String titulo = scanner.nextLine();
        System.out.print("Digite a Nova Descrição do Vídeo: ");
        String descricao = scanner.nextLine();
        System.out.print("Digite a Nova Duração do Vídeo (em minutos): ");
        String duracao = scanner.nextLine();
        System.out.print("Digite a Nova Categoria do Vídeo(EX: Documentário, Filme, Série): ");
        Categoria categoria = Categoria.valueOf(scanner.nextLine());
        System.out.print("Digite a Nova Data de Publicação (dd/MM/yyyy)");
        String dataPublicacao = scanner.nextLine();

        return new Video(titulo, descricao, Integer.parseInt(duracao), categoria, dataPublicacao);
    }

    public static void editarVideo(VideoManager videoManager){
        System.out.println("=== Dicas para Editar o Video ===");
        System.out.println("!!O título do vídeo não pode ser nulo!!");
        System.out.println("!!A descrição do vídeo não pode ser nulo!!");
        System.out.println("!!A duração do vídeo tem que ser número positivo e maior que zero!!");

        System.out.println("Digite o título do vídeo: ");
        String titulo = scanner.nextLine();

        var videos = videoManager.pesquisarVideoPeloTitulo(titulo);
        if (videos.isEmpty()) {
            System.out.println("Título de vídeo não encontrado!");
        } else if (videos.size() == 1){
            var video = videos.get(0);
            System.out.println("Vídeo encontrado: " + video);

            System.out.print("Deseja editar este vídeo? (S/N): ");
            var simNao = scanner.nextLine();
            if (simNao.equalsIgnoreCase("S")){
                var novosDadosDoVideo = solicitaNovosDadosDoVideo();
                videoManager.editarVideo(video, novosDadosDoVideo);
            } else {
                System.out.println("Edição do Vídeo foi Cancelada!");
            }
        } else {
            System.out.println("Mais de um vídeo foi encontrado pelo título informado, por favor indicar o index do título desejado");
            for (int i = 0; i < videos.size(); i++){
                System.out.println("Index: " + i + " - " + videos.get(i));
            }
            int index = scanner.nextInt();
            scanner.nextLine(); // quebra da linha
            if (index < 0 || index >= videos.size()) {
                System.out.println("Index inválido!!");
                return;
            }
            var video = videos.get(index);
            if (video == null){
                System.out.println("Vídeo não encontrado na lista!!");
            } else {
                var novosDadosDoVideo = solicitaNovosDadosDoVideo();
                videoManager.editarVideo(video, novosDadosDoVideo);
            }
        }

    }

    public static void excluirVideo(VideoManager videoManager){
        System.out.println("=== Dicas Excluir o Video ===");
        System.out.println("!!O título do vídeo não pode ser nulo!!");

        System.out.println("Digite o título do vídeo: ");
        String titulo = scanner.nextLine();

        var videos = videoManager.pesquisarVideoPeloTitulo(titulo);
        if (videos.isEmpty()) {
            System.out.println("Título de vídeo não encontrado!");
        } else if (videos.size() == 1){
            var video = videos.get(0);
            System.out.println("Vídeo encontrado: " + video);

            System.out.print("Deseja excluir este vídeo? (S/N): ");
            var simNao = scanner.nextLine();
            if (simNao.equalsIgnoreCase("S")){
                videoManager.excluirVideo(titulo);
            } else {
                System.out.println("Vídeo excluido com sucesso!");
            }
        } else {
            System.out.println("Mais de um vídeo foi encontrado pelo título informado, por favor indicar o index do título desejado");
            for (int i = 0; i < videos.size(); i++){
                System.out.println("Index: " + i + " - " + videos.get(i));
            }
            int index = scanner.nextInt();
            scanner.nextLine(); // quebra da linha
            if (index < 0 || index >= videos.size()) {
                System.out.println("Index inválido!!");
                return;
            }
            var video = videos.get(index);
            if (video == null){
                System.out.println("Vídeo não encontrado na lista!!");
            } else {
                videoManager.excluirVideo(video.getTitulo());
            }
        }
    }

    public static void filtrarVideo(VideoManager videoManager){
            System.out.println("Digite a categoria que deseja filtrar: ");

            //videoManager.pesquisarVideoPelaCategoria(query);
            var videos = videoManager.pesquisarVideoPelaCategoria(query);
            videos.forEach(System.out::println);

    }

    public static void ordenarVideo(){

    }

    public static void exibirRelatorio(){

    }

}