package model;

public enum Categoria {
    Documentario("Documentário"),
    Filmes("Filme"),
    Serie("Serie");

    Categoria(String descricao) {
    }

    public static Categoria categoriaDesejada(String string){
        try {
            return Categoria.valueOf(string.toUpperCase());
        }catch (IllegalArgumentException e){
            System.out.println("Esta Categoria é inválida!");
            System.out.println("Tipos de Categoria: Documentário, Filme, Série");
        }
        return null;
    }
}
