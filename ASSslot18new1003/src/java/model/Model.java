package model;

public class Model {

    private int ModelID;
    private String ModelName;

    public Model() {
    }

    public Model(int ModelID, String ModelName) {
        this.ModelID = ModelID;
        this.ModelName = ModelName;
    }

    public Model(String ModelName) {
        this.ModelName = ModelName;
    }

    public int getModelID() {
        return ModelID;
    }

    public void setModelID(int ModelID) {
        this.ModelID = ModelID;
    }

    public String getModelName() {
        return ModelName;
    }

    public void setModelName(String ModelName) {
        this.ModelName = ModelName;
    }

    @Override
    public String toString() {
        return "Model{" + "ModelID=" + ModelID + ", ModelName=" + ModelName + '}';
    }

}
