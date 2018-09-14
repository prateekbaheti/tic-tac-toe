public enum CellState {
    X("X"),
    O("O"),
    EMPTY(".");

    private String representation;

    CellState(String representation) {
        this.representation = representation;
    }

    public String getRepresentation() {
        return representation;
    }
}
