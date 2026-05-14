package org.example.models;

public class Produto {

    private int id;
    private String nome;
    private String descricao;
    private double preco;
    private String categoria;
    private int id_restaurante;
    private boolean disponivel = true;

    public Produto() {
    }

    public Produto(
            int id,
            String nome,
            String descricao,
            double preco,
            String categoria,
            int id_restaurante
    ) {

        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.categoria = categoria;
        this.id_restaurante = id_restaurante;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(
            String descricao
    ) {
        this.descricao = descricao;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(
            String categoria
    ) {
        this.categoria = categoria;
    }

    public int getId_restaurante() {
        return id_restaurante;
    }

    public void setId_restaurante(
            int id_restaurante
    ) {
        this.id_restaurante = id_restaurante;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void setDisponivel(
            boolean disponivel
    ) {
        this.disponivel = disponivel;
    }

    public void setIdRestaurante(int id) {
        this.id_restaurante = id;
    }


}