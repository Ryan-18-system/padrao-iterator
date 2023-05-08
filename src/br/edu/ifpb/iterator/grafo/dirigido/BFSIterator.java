package br.edu.ifpb.iterator.grafo.dirigido;

import java.util.*;

public class BFSIterator<T> implements Iterator<Vertice<T>> {
    private Queue<Vertice<T>> fila;

    public BFSIterator(Grafo<T> grafo) {
        fila = new LinkedList();
        Vertice<T> vertice = grafo.getVertice(grafo.getVertices().get(0).getCarga());
        fila.add(vertice);
        vertice.setStatus(VertexState.Visited);
    }

    @Override
    public boolean hasNext() {
        return !fila.isEmpty();
    }

    @Override
    public Vertice<T> next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        Vertice<T> vertice = fila.remove();
        for (Aresta<T> arco : vertice.getAdj()) {
            @SuppressWarnings("unchecked")
            Vertice<T> v = (Vertice<T>) arco.getDestino();
            if (!fila.contains(v)) {
                v.setStatus(VertexState.Visited);
                fila.add(v);
            }
        }
        return vertice;
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }
}