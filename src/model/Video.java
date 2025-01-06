package model;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Video {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private String titulo;
    private String descricao;
    private int duracao; // em minutos
    private String categoria;
    private LocalDate dataPublicacao;

    public Video(String titulo, String descricao, int duracao, String categoria, String dataPublicacao) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.duracao = duracao;
        this.categoria = categoria;
        this.dataPublicacao = LocalDate.parse(dataPublicacao, DATE_TIME_FORMATTER);
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getDuracao() {
        return duracao;
    }

    public void setDuracao(int duracao) {
        this.duracao = duracao;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public LocalDate getDataPublicacao() {
        return dataPublicacao;
    }

    public void setDataPublicacao(LocalDate dataPublicacao) {
        this.dataPublicacao = dataPublicacao;
    }

    @Override
    public String toString() {
        return titulo + ";" + descricao + ";" + duracao + ";" + categoria + ";" + dataPublicacao.format(DATE_TIME_FORMATTER);
    }

    public static Video fromString(String linha) {
        try {
            String[] partes = linha.split(";");
            return new Video(partes[0], partes[1], Integer.parseInt(partes[2]), partes[3], partes[4]);
        } catch (Exception e) {
            return null; // Ignora erros de parsing
        }
    }
}