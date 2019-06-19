package pkg11ideal.clases;

public class TElementoAB<T> implements IElementoAB<T> {

    private Comparable etiqueta;
    private TElementoAB<T> hijoIzq;
    private TElementoAB<T> hijoDer;
    private T datos;

    /**
     * @param unaEtiqueta
     * @param unosDatos 
     */
    @SuppressWarnings("unchecked")
    public TElementoAB(Comparable unaEtiqueta, T unosDatos) {
        etiqueta = unaEtiqueta;
        datos = unosDatos;
    }

    public TElementoAB<T> getHijoIzq() {
        return hijoIzq;
    }

    public TElementoAB<T> getHijoDer() {
        return hijoDer;
    }

    /**
     * @param unElemento
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public boolean insertar(TElementoAB<T> unElemento) {
        if (unElemento.getEtiqueta().compareTo(etiqueta) < 0) {
            if (hijoIzq != null) {
                return getHijoIzq().insertar(unElemento);
            } else {
                hijoIzq = unElemento;
                return true;
            }
        } else if (unElemento.getEtiqueta().compareTo(etiqueta) > 0) {
            if (hijoDer != null) {
                return getHijoDer().insertar(unElemento);
            } else {
                hijoDer = unElemento;
                return true;
            }
        } else {
            // ya existe un elemento con la misma etiqueta.-
            return false;
        }
    }

    /**
     * @param unaEtiqueta
     * @return
     */
    @Override
    public TElementoAB<T> buscar(Comparable unaEtiqueta) {

        if (unaEtiqueta.equals(etiqueta)) {
            return this;
        } else if (unaEtiqueta.compareTo(etiqueta) < 0) {
            if (hijoIzq != null) {
                return getHijoIzq().buscar(unaEtiqueta);
            } else {
                return null;
            }
        } else if (hijoDer != null) {
            return getHijoDer().buscar(unaEtiqueta);
        } else {
            return null;
        }
    }
    
    public String postOrden() {
        String resultado ="";
        if (this.hijoIzq!=null)
        {
            resultado+=this.hijoIzq.postOrden();
        }
        if (this.hijoDer!=null)
        {
            resultado+=this.hijoDer.postOrden();
        }
        resultado += etiqueta.toString()+"; ";
        return resultado;
    }
    public String preOrden() {
        String x="";
        x += etiqueta.toString()+"; ";
        if(hijoIzq!=null){
                x+=hijoIzq.preOrden();
            
        }
        if(hijoDer!=null){
                x+= hijoDer.preOrden();
        }
        return x;
    }
    /**
     * @return recorrida en inorden del subArbol que cuelga del elemento actual
     */
    @Override
    public String inOrden() {
        String x ="";
        if (hijoIzq!=null){
            
            x+= hijoIzq.inOrden();
        }
        
        x+= etiqueta.toString()+"; ";
        
        if (hijoDer!=null){
            
            x+= hijoDer.inOrden();
        }
        return x;
    }

    @Override
    public void inOrden(Lista<T> unaLista) {
        if (hijoIzq!=null){
            hijoIzq.inOrden(unaLista);
        }
        Nodo<T> nodoElem = new Nodo(this.etiqueta,this.datos);
        unaLista.insertar(nodoElem);
        if (hijoDer!=null){
            
            hijoDer.inOrden(unaLista);
        }
    }

    @Override
    public Comparable getEtiqueta() {
        return etiqueta;
    }

    /**
     * @return
     */
    public String imprimir() {
        return (etiqueta.toString());
    }
    
    @Override
    public T getDatos() {
        return datos;
    }

    @Override
    public void setHijoIzq(TElementoAB<T> elemento) {
        this.hijoIzq = elemento;

    }

    @Override
    public void setHijoDer(TElementoAB<T> elemento) {
        this.hijoDer = elemento;
    }

    @Override
    public int obtenerAltura() {
        int a = -1;
        int b = -1;
         
        if (hijoIzq != null)
            a = getHijoIzq().obtenerAltura();            
        if (hijoDer != null)
            b = getHijoDer().obtenerAltura();
         
        return Math.max(a,b)+1;
    }

    @Override
    public int obtenerTamanio() {
        int x=1;
        if(hijoIzq!=null){
            x+= hijoIzq.obtenerTamanio();
        }
        if(hijoDer!=null){
            x += hijoDer.obtenerTamanio();
        }
        return x;
    }

    @Override
    public int obtenerNivel(Comparable unaEtiqueta) {
        int contador = 0;
        if (this.buscar(unaEtiqueta) == null) {
                return -1;
        }

        if (this.getHijoIzq() == null && this.getHijoDer() == null && this.getEtiqueta().compareTo(unaEtiqueta) == 0) {
                return 0;
        }
        if (this.getEtiqueta().compareTo(unaEtiqueta) < 0) {
                contador++;
                contador += this.getHijoDer().obtenerNivel(unaEtiqueta);
        } else if (this.getEtiqueta().compareTo(unaEtiqueta) > 0) {
                contador++;
                contador += this.getHijoIzq().obtenerNivel(unaEtiqueta);
        } else {
                return contador;
        }

        return contador;
    }

    @Override
    public int obtenerCantidadHojas() {
        int count = 0;
        if (this.hijoIzq==null && this.hijoDer==null)
        {
            return 1;
        }
        if (this.hijoIzq!=null)
        {
            count += this.hijoIzq.obtenerCantidadHojas();
        }
        if (this.hijoDer!=null)
        {
            count += this.hijoDer.obtenerCantidadHojas();
        }
        return count;
    }
    
    @Override
    public TElementoAB eliminar(Comparable unaEtiqueta) {
        if (unaEtiqueta.compareTo(etiqueta)<0)
        {
            if (hijoIzq != null)
            {
                hijoIzq = hijoIzq.eliminar(unaEtiqueta);
            }
            return this;
        }
        if (unaEtiqueta.compareTo(etiqueta)>0)
        {
            if (hijoDer != null)
            {
                hijoDer = hijoDer.eliminar(unaEtiqueta);
            }
            return this;
        }
        return this.quitaElNodo();
    }
    
 private TElementoAB quitaElNodo() {
    if (hijoIzq==null)
        {
            return hijoDer;
        }
        if (hijoDer==null)
        {
           return hijoIzq;
        }
        TElementoAB elHijo = hijoIzq;
        TElementoAB elPadre = this;
        while (elHijo.hijoDer!=null)
        {
            elPadre = elHijo;
            elHijo = elHijo.hijoDer;
        }
        if (elPadre != this)
        {
            elPadre.hijoDer = elHijo.hijoIzq;
            elHijo.hijoIzq = hijoIzq;
        }
        elHijo.hijoDer = hijoDer;
        return elHijo;
    }
}
