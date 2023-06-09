import java.io.*;

class Entrada implements Serializable{
    private int idCompra;
    private String artista;
    private String fecha;
    private String lugar;
    private String ciudad;
    private int numEntradas;
    private int numGrada;


    public Entrada(){}
    
    
    public void setId(int id){
        this.idCompra = id;
    }

    public void setArtista(String artista){
        this.artista = artista;
    }

    public void setFecha(String fecha){
        this.fecha = fecha;
    }

    public void setLugar(String lugar){
        this.lugar = lugar;
    }

    public void setCiudad(String ciudad){
        this.ciudad = ciudad;
    }

    public void setEntradas(int entradas){
        this.numEntradas = entradas;
    }

    public void setGrada(int numGrada){
        this.numGrada = numGrada;
    }


    public int getId(){
        return idCompra;
    }

    public String getArtista(){
        return artista;
    }

    public String getFecha(){
        return fecha;
    }

    public String getLugar(){
        return lugar;
    }

    public String getCiudad(){
        return ciudad;
    }

    public int getEntradas(){
        return numEntradas;
    }

    public int getGrada(){
        return numGrada;
    }


}