package Model.Materials;

public abstract class Material {
    public abstract boolean isSameType();
    protected abstract String GetTypeUnique();
    public abstract void DrilledThroughSunClose();
}
