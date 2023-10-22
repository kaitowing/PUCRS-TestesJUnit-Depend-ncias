package com.bcopstein;

public class EstatisticasDTO {
    private double media;
    private double mediana;
    private double desvioPadrao;

    public EstatisticasDTO(double media, double mediana, double desvioPadrao) {
        this.media = media;
        this.mediana = mediana;
        this.desvioPadrao = desvioPadrao;
    }

    public double getMedia() {
        String formattedMedia = String.format("%.2f", media);
        formattedMedia = formattedMedia.replace(',', '.'); // Substitui a vírgula por ponto
        return Double.parseDouble(formattedMedia);
    }
    
    public double getMediana() {
        String formattedMediana = String.format("%.2f", mediana);
        formattedMediana = formattedMediana.replace(',', '.'); // Substitui a vírgula por ponto
        return Double.parseDouble(formattedMediana);
    }
    
    public double getDesvioPadrao() {
        String formattedDesvioPadrao = String.format("%.2f", desvioPadrao);
        formattedDesvioPadrao = formattedDesvioPadrao.replace(',', '.'); // Substitui a vírgula por ponto
        return Double.parseDouble(formattedDesvioPadrao);
    }
    
}
