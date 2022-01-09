package jkw.utils;

/* Prosta klasa implementująca parę elementów dowolnego typu */
public class Pair<TypeA, TypeB> {

    private TypeA elementA;
    private TypeB elementB;

    public Pair () {

    }

    public static <TypeA, TypeB> Pair<TypeA, TypeB> createPair(TypeA elementA, TypeB elementB) {
        return new Pair<>(elementA, elementB);
    }

    public Pair(TypeA elementA, TypeB elementB) {
        this.elementA = elementA;
        this.elementB = elementB;
    }

    public TypeA getElementA() {
        return elementA;
    }

    public TypeB getElementB() {
        return elementB;
    }

    public void setElementA (TypeA elementA){
        this.elementA = elementA;
    }

    public void setElementB (TypeB elementB) {
        this.elementB = elementB;
    }
}