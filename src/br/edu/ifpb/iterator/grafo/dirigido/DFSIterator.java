package br.edu.ifpb.iterator.grafo.dirigido;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;


public class DFSIterator<T> implements Iterator<Vertice<T>> {
    private Stack<Vertice<T>> pilha;

    public DFSIterator(Grafo<T> grafo) {
        pilha = new Stack();
        Vertice<T> vertice = grafo.getVertice(grafo.getVertices().get(0).getCarga());
        pilha.push(vertice);
        vertice.setStatus(VertexState.Visited);
    }

    @Override
    public boolean hasNext() {
        return !pilha.isEmpty();
    }

    @Override
    public Vertice<T> next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        Vertice<T> vertice = pilha.pop();
        for (Aresta<T> arco : vertice.getAdj()) {
            @SuppressWarnings("unchecked")
            Vertice<T> v = (Vertice<T>) arco.getDestino();
            if (!pilha.contains(v)) {
                v.setStatus(VertexState.Visited);
                pilha.push(v);
            }
        }
        return vertice;
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }
}