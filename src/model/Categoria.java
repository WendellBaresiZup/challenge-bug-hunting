package model;

public enum Categoria {
    Documentario("Documentário"),
    Filme("Filme"),
    Serie("Série");

    Categoria(String descricao) {
    }


    public static Categoria categoriaDesejada(String string){
        //try {
        //    return Categoria.valueOf(string.toUpperCase());
        //}catch (IllegalArgumentException e){
        //    System.out.println("Esta Categoria é inválida!");
        //}
        //return null;
        return null;
    }

}
