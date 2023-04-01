import java.io.*;

class Entrada implements Serializable{
    private int idEvento;
    private String artista;
    private String fecha;
    private String lugar;
    private String ciudad;
    private int numEntradas;

    public Entrada(){}
    
    
    public void setId(int id){
        this.idEvento = id;
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

    public int getId(){
        return idEvento;
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

    public String getEntradas(){
        return numEntradas;
    }
}